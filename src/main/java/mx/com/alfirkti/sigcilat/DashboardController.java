package mx.com.alfirkti.sigcilat;

import mx.com.alfirkti.sigcilat.Ciudadano.CiudadanoRepository;
import mx.com.alfirkti.sigcilat.security.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final CiudadanoRepository ciudadanoRepository;
    private final UsuarioRepository usuarioRepository;

    public DashboardController(CiudadanoRepository ciudadanoRepository,
                               UsuarioRepository usuarioRepository) {
        this.ciudadanoRepository = ciudadanoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        long totalCiudadanos = ciudadanoRepository.count();
        long activos = ciudadanoRepository.countByActivoTrue();
        long inactivos = ciudadanoRepository.countByActivoFalse();

        model.addAttribute("totalCiudadanos", totalCiudadanos);
        model.addAttribute("ciudadanosActivos", activos);
        model.addAttribute("ciudadanosInactivos", inactivos);
        model.addAttribute("totalUsuarios", usuarioRepository.count());

        return "dashboard/index";
    }
}
