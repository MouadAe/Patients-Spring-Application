package ma.enset.tp1_jpa.security.repositories;

import ma.enset.tp1_jpa.security.entities.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UsersRolesRepository extends JpaRepository<UsersRoles,Long> {
    Collection<UsersRoles> findAllByUsername(String username);
}
