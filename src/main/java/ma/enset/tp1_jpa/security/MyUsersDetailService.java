package ma.enset.tp1_jpa.security;

import ma.enset.tp1_jpa.security.entities.User;
import ma.enset.tp1_jpa.security.entities.UsersRoles;
import ma.enset.tp1_jpa.security.repositories.UserRepository;
import ma.enset.tp1_jpa.security.repositories.UsersRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MyUsersDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UsersRolesRepository usersRolesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findById(username);
        if(!user.isPresent())
            throw new UsernameNotFoundException(username);
        Collection<UsersRoles> listRoles = usersRolesRepository.findAllByUsername(
                user.get().getUsername()
        );
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(UsersRoles role : listRoles){
            System.out.println(role);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
        }
        return new org.springframework.security.core.userdetails
                .User(user.get().getUsername(),user.get().getPassword(),authorities);
    }
}
