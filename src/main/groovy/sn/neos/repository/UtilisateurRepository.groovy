package sn.neos.repository

import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import sn.neos.model.Utilisateur

@Repository
interface UtilisateurRepository extends CrudRepository<Utilisateur,String >{
    Utilisateur findByUsernameOrEmail(String username, String email);
    Utilisateur findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
