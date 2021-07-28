package br.edu.iff.projectLibrary.security;

import br.edu.iff.projectLibrary.model.Person;
import br.edu.iff.projectLibrary.model.Permission;
import br.edu.iff.projectLibrary.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailsService implements UserDetailsService
{
    @Autowired
    private PersonRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Person person = repo.findByEmail(email);
        
        if(person == null)
        {
            throw new UsernameNotFoundException("Cadastro não encontrado com esse email: " + email);
        }
        
        return new User(person.getEmail(), person.getPassword(), getAuthorities(person.getPermissions()));
    }
    
    private List<GrantedAuthority> getAuthorities(List<Permission> list)
    {
        List<GrantedAuthority> l = new ArrayList<>();
        
        for(Permission p : list)
        {
            l.add(new SimpleGrantedAuthority("ROLE_" + p.getName()));
        }
        
        return l;
    }
}
