package sn.neos.controller

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sn.neos.model.Utilisateur
import sn.neos.repository.UtilisateurRepository
import sn.neos.service.UserService

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/user")
@Slf4j
class UtilisateurController {
    @Autowired
    private UtilisateurRepository utilisateurRepository

    @Autowired
    private UserService utilisateurService;

    // Endpoint pour récupérer tous les users
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/list")
    public List<Utilisateur> getAllUsers() {
        return utilisateurService.getAllUsers();
    }

    // Endpoint pour récupérer un user par son ID
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public Utilisateur getUserById(@PathVariable("id") String userId) {
        log.info("String id : {} ",userId)
        return utilisateurService.getUserById(userId);
    }

    // Endpoint pour ajouter un nouveau user
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping("/add")
    public Utilisateur addUtilisateur(@RequestBody Utilisateur user) {
        return utilisateurService.addUtilisateur(user);
    }

    // Endpoint pour mettre à jour un user existant
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping("/update/{id}")
    public Utilisateur updateUtilisateur(@PathVariable("id") String id, @RequestBody Utilisateur updateUser) {
        return utilisateurService.updateUser(id, updateUser);
    }

    // Endpoint pour supprimer un user par son ID
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable("id") String id) {
        if(id != null) {
            return utilisateurService.deleteUser(id);
        }
        return HttpStatus.NOT_FOUND("l'id saisie est null")
    }
}
