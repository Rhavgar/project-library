package br.edu.iff.projectLibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Librarian extends Person
{
    @Column(nullable = false, length = 5, updatable = true)
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
