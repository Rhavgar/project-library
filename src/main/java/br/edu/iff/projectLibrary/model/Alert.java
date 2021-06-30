package br.edu.iff.projectLibrary.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Alert implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertid;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Data de Emissão é obrigatória.")
    @FutureOrPresent(message = "Data de Emissão deve ser atual ou no futuro.")
    @DateTimeFormat(pattern = "yyyy.MM.dd, HH:mm:ss z")
    private Calendar issueDate;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Data de Retorno é obrigatória.")
    @Future(message = "Data de Retorno deve ser no futuro.")
    @DateTimeFormat(pattern = "yyyy.MM.dd, HH:mm:ss z")
    private Calendar returnDate;
    
    
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Membro é obrigatório.")
    private LibMember libMember;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Livro é obrigatório.")
    private Book book;

    public Long getAlertid() 
    {
        return alertid;
    }

    public void setAlertid(Long alertid) 
    {
        this.alertid = alertid;
    }

    public Calendar getIssueDate() 
    {
        return issueDate;
    }

    public void setIssueDate(Calendar issueDate) 
    {
        this.issueDate = issueDate;
    }

    public Calendar getReturnDate() 
    {
        return returnDate;
    }

    public void setReturnDate(Calendar returnDate) 
    {
        this.returnDate = returnDate;
    }

    public LibMember getLibMember() 
    {
        return libMember;
    }

    public void setLibMember(LibMember libMember) 
    {
        this.libMember = libMember;
    }

    public Book getBook() 
    {
        return book;
    }

    public void setBook(Book book) 
    {
        this.book = book;
    }
    
    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.alertid);
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
        final Alert other = (Alert) obj;
        if (!Objects.equals(this.alertid, other.alertid)) 
        {
            return false;
        }
        return true;
    }
    
}
