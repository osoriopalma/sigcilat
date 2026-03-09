/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alfirkti.sigcilat.security;

import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author WOPS
 */

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final PasswordEncoder encoder;

    public AdminSeeder(UsuarioRepository usuarioRepo, RolRepository rolRepo, PasswordEncoder encoder) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        String user = "admin";
        if (usuarioRepo.existsByUsername(user)) {
            return;
        }

        Rol adminRol = rolRepo.findByNombre("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("No existe ROLE_ADMIN en tabla rol"));

        Usuario u = new Usuario();
        u.setUsername(user);
        u.setPassword(encoder.encode("Admin123*")); // cámbiala luego
        u.setEnabled(true);
        u.setRoles(Set.of(adminRol));

        usuarioRepo.save(u);

        System.out.println(">>> Usuario inicial creado: admin / Admin123* (cámbialo después)");
    }
}
