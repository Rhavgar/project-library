package br.edu.iff.projectLibrary.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
public class Book implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 30, updatable = false)
    @NotBlank(message = "Nome do livro é obrigatório.")
    @Length(max = 30, message = "Nome do livro deve ter no máximo 30 caracteres.")
    private String bookName;
    
    @Column(nullable = false, length = 30, updatable = false)
    @NotBlank(message = "Autor do livro é obrigatório.")
    @Length(max = 30, message = "Autor do livro deve ter no máximo 30 caracteres.")
    private String bookAuthor;
    
    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Idioma é obrigatório.")
    private Language lang;
    
    @Column(nullable = false, updatable = true)
    @NotBlank(message = "Quantidade de exemplares é obrigatório.")
    @Digits(integer = 4, fraction = 0, message = "Quantidade de exemplares deve ser inteiro e ter até 4 digitos.")
    private int bookAmount;

    public Long getId() 
    {
        return id;
    }

    public void seId(Long id) 
    {
        this.id = id;
    }

    public String getBookName() 
    {
        return bookName;
    }

    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public String getBookAuthor() 
    {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) 
    {
        this.bookAuthor = bookAuthor;
    }

    public Language getLang() 
    {
        return lang;
    }

    public void setLang(Language lang) 
    {
        this.lang = lang;
    }

    public int getBookAmount() 
    {
        return bookAmount;
    }

    public void setBookAmount(int bookAmount) 
    {
        this.bookAmount = bookAmount;
    }

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Book other = (Book) obj;
        if (!Objects.equals(this.id, other.id)) 
        {
            return false;
        }
        return true;
    }
    
}
