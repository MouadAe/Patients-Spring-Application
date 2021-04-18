package ma.enset.tp1_jpa.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "users")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Size(min = 5)
    private String username;
    @NotNull
    private String 	password;
    private boolean active = true;
}
