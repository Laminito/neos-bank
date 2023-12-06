package sn.neos.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import sn.neos.model.Role

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    Optional<Role> findByIntitule(String intitule);
}