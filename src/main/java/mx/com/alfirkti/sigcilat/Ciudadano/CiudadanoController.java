/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alfirkti.sigcilat.Ciudadano;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author WOPS
 */
@Controller
@RequestMapping("/ciudadanos")
public class CiudadanoController {

    @Autowired
    private CiudadanoRepository ciudadanoRepository;

    // LISTA ACTIVOS (con búsqueda opcional)
    @GetMapping
    public String lista(Model model, @RequestParam(required = false) String q) {

        var ciudadanos = (q != null && !q.trim().isEmpty())
                ? ciudadanoRepository.buscarActivos(q.trim())
                : ciudadanoRepository.findByActivoTrueOrderByClaveConsecutivaDesc();

        model.addAttribute("ciudadanos", ciudadanos);
        model.addAttribute("q", q);
        return "ciudadanos/lista";
    }

    // FORM NUEVO
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("ciudadano", new Ciudadano());
        return "ciudadanos/nuevo";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Ciudadano ciudadano,
            BindingResult result,
            Model model) {

        String curp = ciudadano.getCurp() != null ? ciudadano.getCurp().trim().toUpperCase() : null;
        ciudadano.setCurp(curp);

        if (curp != null && ciudadanoRepository.existsByCurp(curp)) {
            result.rejectValue("curp", "curp.duplicada", "Ya existe un ciudadano con esa CURP.");
        }

        if (result.hasErrors()) {
            model.addAttribute("ciudadano", ciudadano);
            return "ciudadanos/nuevo";
        }
        ciudadano.setCorreo(ciudadano.getCorreo() != null ? ciudadano.getCorreo().trim().toLowerCase() : null);

        ciudadanoRepository.save(ciudadano);
        return "redirect:/ciudadanos";
    }

    @PostMapping("/{id}/desactivar")
    public String desactivar(@PathVariable Long id) {

        Ciudadano ciudadano = ciudadanoRepository.findByIdAndActivoTrue(id).orElseThrow(()
                -> new IllegalArgumentException("Ciudadano no encontrado o ya está dado de baja"));
        ciudadano.setActivo(false);
        ciudadanoRepository.save(ciudadano);
        return "redirect:/ciudadanos";
    }

    // LISTA INACTIVOS
    @GetMapping("/inactivos")
    public String listaInactivos(Model model, @RequestParam(required = false) String q) {

        var ciudadanos = (q != null && !q.trim().isEmpty())
                ? ciudadanoRepository.buscarInactivos(q.trim())
                : ciudadanoRepository.findByActivoFalseOrderByIdDesc();

        model.addAttribute("ciudadanos", ciudadanos);
        model.addAttribute("q", q);
        return "ciudadanos/lista_inactivos";
    }

// REACTIVAR
    @PostMapping("/{id}/reactivar")
    public String reactivar(@PathVariable Long id) {
        Ciudadano c = ciudadanoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado: " + id));

        c.setActivo(true);
        ciudadanoRepository.save(c);

        return "redirect:/ciudadanos/inactivos";
    }

    // FORM EDITAR
    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model) {

        Ciudadano ciudadano = ciudadanoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new IllegalArgumentException("No se puede editar: ciudadano no existe o está inactivo."));

        model.addAttribute("ciudadano", ciudadano);
        return "ciudadanos/editar";
    }

    //// ACTUALIZAR
    @PostMapping("/{id}/actualizar")
    public String actualizar(
            @PathVariable Long id,
            @ModelAttribute("ciudadano") Ciudadano ciudadanoForm,
            BindingResult result,
            Model model) {

        // 1) SOLO ACTIVOS se editan
        Ciudadano ciudadano = ciudadanoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new IllegalArgumentException("No se puede editar: ciudadano no existe o está inactivo."));

        String curp = ciudadanoForm.getCurp() != null ? ciudadanoForm.getCurp().trim().toUpperCase() : null;

        // ✅ importante para que el form se repinte con el valor normalizado
        ciudadanoForm.setCurp(curp);

        if (curp != null && ciudadanoRepository.existsByCurpAndIdNot(curp, id)) {
            result.rejectValue("curp", "curp.duplicada", "Ya existe otro ciudadano con esa CURP.");
        }

        if (result.hasErrors()) {
            ciudadanoForm.setId(id);
            model.addAttribute("ciudadano", ciudadanoForm);
            return "ciudadanos/editar";
        }

        ciudadano.setNombre(ciudadanoForm.getNombre());
        ciudadano.setApellidoPaterno(ciudadanoForm.getApellidoPaterno());
        ciudadano.setApellidoMaterno(ciudadanoForm.getApellidoMaterno());
        ciudadano.setCurp(curp);
        ciudadano.setFechaNacimiento(ciudadanoForm.getFechaNacimiento());
        ciudadano.setTelefono(ciudadanoForm.getTelefono());
        ciudadano.setCorreo(ciudadanoForm.getCorreo() != null ? ciudadanoForm.getCorreo().trim().toLowerCase() : null);
        ciudadano.setDomicilio(ciudadanoForm.getDomicilio());

        ciudadanoRepository.save(ciudadano);
        return "redirect:/ciudadanos";
    }

}
