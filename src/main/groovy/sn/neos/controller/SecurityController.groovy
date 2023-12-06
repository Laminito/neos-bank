package sn.neos.controller

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sn.neos.dto.security.LoginDto
import sn.neos.dto.security.SignUpDto
import sn.neos.model.Role
import sn.neos.model.Utilisateur
import sn.neos.repository.RoleRepository
import sn.neos.repository.UtilisateurRepository
import sn.neos.service.UserService

@RestController
@RequestMapping("/security")
@Slf4j
class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UtilisateurRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            def token = userService.authenticateUser(loginDto);
            if (token != null) {
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User login failed!...");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User login failed!...");
    }



    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        // checking for username exists in a database
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already exist!", HttpStatus.BAD_REQUEST);
        }
        // checking for email exists in a database
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already exist!", HttpStatus.BAD_REQUEST);
        }
        // creating user object
        Utilisateur user = new Utilisateur();
        user.setNom(signUpDto.getNom());
        user.setPrenom(signUpDto.getPrenom());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setIntitule(signUpDto.getIntitule());
        user.setAdresse(signUpDto.getAdresse());
        user.setSexe(signUpDto.getSexe());
        user.setDateDeNaissance(signUpDto.getDateDeNaissance());
        user.setLieuNaissance(signUpDto.getLieuNaissance());
        user.setNationalite(signUpDto.getNationalite());
        user.setPaysNaissance(signUpDto.getPaysNaissance());
        user.setPaysResidence(signUpDto.getPaysResidence());
        user.setProfessionPersonne(signUpDto.getProfessionPersonne());
        user.setDescription(signUpDto.getDescription());
        user.setVille(signUpDto.getVille());
        user.setTelMobile(signUpDto.getTelMobile());
        user.setTelBureau(signUpDto.getTelBureau());
        user.setTelDomicile(signUpDto.getTelDomicile());
        user.setFax(signUpDto.getFax());
        user.setPhoto(signUpDto.getPhoto());
        user.setPhotoType(signUpDto.getPhotoType());
        Role roles = roleRepository.findByIntitule("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<>("User is registered successfully!", HttpStatus.OK);        }
}
