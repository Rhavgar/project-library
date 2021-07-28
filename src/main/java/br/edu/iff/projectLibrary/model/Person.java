package br.edu.iff.projectLibrary.model;

import br.edu.iff.projectLibrary.annotation.EmailValidation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 30, updatable = true)
    @NotBlank(message = "Nome é obrigatório.")
    @Length(max = 30, message = "Nome deve ter no máximo 30 caracteres.")
    private String personName;
    
    @Column(nullable = false, length = 30, unique = true, updatable = true)
    @NotBlank(message = "Email é obrigatório.")
    @EmailValidation
    private String email;
    
    @Column(nullable = false, length = 14, unique = true, updatable = false)
    @NotBlank(message = "CPF é obrigatório.")
    @CPF(message = "CPF inválido.")
    private String cpf;
    
    @Column(nullable = false, length = 16, updatable = true)
    @NotBlank(message = "Senha é obrigatória.")
    @Length(max = 16, min = 8, message = "Senha deve ter no mínimo 8 caracteres e no máximo 16 caracteres.")
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "Esse cadastro deve ter no mínimo uma permissão.")
    private List<Permission> permissions = new ArrayList<>();

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getPersonName() 
    {
        return personName;
    }

    public void setPersonName(String personName) 
    {
        this.personName = personName;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getCpf() 
    {
        return cpf;
    }

    public void setCpf(String cpf) 
    {
        this.cpf = cpf;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public List<Permission> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions)
    {
        this.permissions = permissions;
    }
    
    

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.id, other.id)) 
        {
            return false;
        }
        return true;
    }
    
}
