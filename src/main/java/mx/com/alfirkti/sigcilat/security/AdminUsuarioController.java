/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alfirkti.sigcilat.security;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author WOPS
 */
@Controller
@RequestMapping("/admin/usuarios")
public class AdminUsuarioController {

    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final PasswordEncoder encoder;

    public AdminUsuarioController(UsuarioRepository usuarioRepo, RolRepository rolRepo, PasswordEncoder encoder) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.encoder = encoder;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("usuarios", usuarioRepo.findAll());
        return "admin/usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("form", new UsuarioForm());
        model.addAttribute("roles", rolRepo.findAll());
        return "admin/usuarios/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("form") UsuarioForm form, BindingResult result, Model model) {

        String username = form.getUsername() != null ? form.getUsername().trim() : null;
        if (username == null || username.isBlank()) {
            result.rejectValue("username", "username.vacio", "El usuario es obligatorio.");
        } else if (usuarioRepo.existsByUsername(username)) {
            result.rejectValue("username", "username.duplicado", "Ya existe ese username.");
        }

        if (form.getPassword() == null || form.getPassword().isBlank()) {
            result.rejectValue("password", "password.vacio", "La contraseña es obligatoria.");
        }

        if (form.getRoleIds() == null || form.getRoleIds().isEmpty()) {
            result.rejectValue("roleIds", "roles.vacio", "Selecciona al menos 1 rol.");
        }

        if (result.hasErrors()) {
            model.addAttribute("roles", rolRepo.findAll());
            return "admin/usuarios/form";
        }

        Usuario u = new Usuario();
        u.setUsername(username);
        u.setEnabled(form.isEnabled());
        u.setPassword(encoder.encode(form.getPassword()));

        Set<Rol> roles = rolRepo.findAllById(form.getRoleIds()).stream().collect(Collectors.toSet());
        u.setRoles(roles);

        usuarioRepo.save(u);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Usuario u = usuarioRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));

        UsuarioForm form = new UsuarioForm();
        form.setId(u.getId());
        form.setUsername(u.getUsername());
        form.setEnabled(u.isEnabled());
        form.setRoleIds(u.getRoles().stream().map(Rol::getId).collect(Collectors.toSet()));

        model.addAttribute("form", form);
        model.addAttribute("roles", rolRepo.findAll());
        return "admin/usuarios/form";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Long id, @ModelAttribute("form") UsuarioForm form, BindingResult result, Model model) {

        Usuario u = usuarioRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));

        String username = form.getUsername() != null ? form.getUsername().trim() : null;
        if (username == null || username.isBlank()) {
            result.rejectValue("username", "username.vacio", "El usuario es obligatorio.");
        } else if (!username.equals(u.getUsername()) && usuarioRepo.existsByUsername(username)) {
            result.rejectValue("username", "username.duplicado", "Ya existe ese username.");
        }

        if (form.getRoleIds() == null || form.getRoleIds().isEmpty()) {
            result.rejectValue("roleIds", "roles.vacio", "Selecciona al menos 1 rol.");
        }

        if (result.hasErrors()) {
            model.addAttribute("roles", rolRepo.findAll());
            return "admin/usuarios/form";
        }

        u.setUsername(username);
        u.setEnabled(form.isEnabled());
        u.setRoles(rolRepo.findAllById(form.getRoleIds()).stream().collect(Collectors.toSet()));

        // password opcional en edición (solo si escriben algo)
        if (form.getPassword() != null && !form.getPassword().isBlank()) {
            u.setPassword(encoder.encode(form.getPassword()));
        }

        usuarioRepo.save(u);
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id) {
        Usuario u = usuarioRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
        u.setEnabled(!u.isEnabled());
        usuarioRepo.save(u);
        return "redirect:/admin/usuarios";
    }
}
