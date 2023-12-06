package sn.neos.controller

import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sn.neos.model.Role
import sn.neos.model.Utilisateur
import sn.neos.repository.RoleRepository
import sn.neos.repository.UtilisateurRepository
import sn.neos.service.UserService

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UserService utilisateurService;

    // Endpoint pour récupérer tous les rôles
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/list")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Endpoint pour créer un nouveau rôle
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    // Endpoint pour attribuer des rôles à un utilisateur
    @PreAuthorize("hasAuthority('ROLE_ADMIN}')")
    @PostMapping("/assign/{userId}")
    public ResponseEntity<String> assignRolesToUser(@PathVariable("userId") String userId, @RequestBody Set<Role> roles) {
        try {
            utilisateurService.affecterRolesToUser(userId, roles);
            return ResponseEntity.ok("Rôles attribués avec succès à l'utilisateur !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'attribution des rôles : " + e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN}')")
    @GetMapping("/retirer/{userId}/{roleId}")
    public void retirerRoleAUtilisateur(@PathVariable("userId") String userId, @PathVariable("roleId")String roleId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);

        if (utilisateur != null && role != null) {
            utilisateur.getRoles().remove(role);
            utilisateurRepository.save(utilisateur);
        } else {
            // TODO Gérer le cas où l'utilisateur ou le rôle n'existe pas
            throw new EntityNotFoundException("L'utilisateur ou le rôle n'existe pas");
        }
    }
}
