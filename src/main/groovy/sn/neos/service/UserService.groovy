package sn.neos.service

import groovy.util.logging.Slf4j
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

import org.springframework.web.bind.annotation.RequestBody
import sn.neos.config.JwtUtil
import sn.neos.dto.security.LoginDto
import sn.neos.model.Role
import sn.neos.model.Utilisateur
import sn.neos.repository.UtilisateurRepository

@Service
@Slf4j
class UserService {
    @Autowired
    private UtilisateurRepository utilisateurRepository

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUserById(String id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
        return utilisateurOptional.orElse(null);
    }

    public Utilisateur addUtilisateur(Utilisateur user) {
        // TODO Ajouter ici la logique de validation ou de traitement avant de sauvegarder
        return utilisateurRepository.save(user);
    }

    public Utilisateur updateUser(UUID id, Utilisateur updateUser) {
        Optional<Utilisateur> existingUserOptional = utilisateurRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            Utilisateur existingUser = existingUserOptional.get();
            existingUser.setNom(updateUser.getNom());
            existingUser.setPrenom(updateUser.getPrenom());
            existingUser.setUsername(updateUser.getUsername());
            // Continue avec d'autres champs à mettre à jour

            return utilisateurRepository.save(existingUser);
        }
        return null;
    }

    public ResponseEntity<String> deleteUser(String id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return new ResponseEntity<>("Utilisateur avec l'ID " + id + " a été supprimé.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Utilisateur avec l'ID " + id + " n'a pas été trouvé.", HttpStatus.NOT_FOUND);
        }
    }

    public void affecterRolesToUser(String userId, Set<Role> roles) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé")); // Gérer cette exception selon votre logique

        utilisateur.setRoles(roles);
        utilisateurRepository.save(utilisateur);
    }

    def getRolesByUsername(String username) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        if (utilisateur != null) {
            return utilisateur.getRoles();
        }
        return Collections.emptySet();
    }

    def authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Utilisateur user = utilisateurRepository.findByUsername(userDetails.getUsername());

            if (user != null && user.getId() != null) {
                String token = jwtUtil.generateToken(user.getId(),user.getUsername());
                // Construire la réponse au format attendu
               def response =[
                       "access_token": token,
                       "expires_in": 3600
               ]
                return response;
            }
        }

        return null
    }
}
