package br.edu.iff.projectLibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
public class Librarian extends Person
{
    @Column(nullable = false, length = 7, updatable = true)
    @NotBlank(message = "Turno é obrigatório.")
    @Length(message = "Turno deve ter no máximo 7 caracteres.")
    private String shift;

    public String getShift() 
    {
        return shift;
    }

    public void setShift(String shift) 
    {
        this.shift = shift;
    }
    
}
