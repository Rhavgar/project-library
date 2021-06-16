package br.edu.iff.projectLibrary.model;

import java.io.Serializable;
import java.util.Objects;


public abstract class Person implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String email;
    private String cpf;

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
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
