package ma.enset.tp1_jpa.security.repositories;

import ma.enset.tp1_jpa.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> { }
