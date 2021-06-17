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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Book implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(nullable = false, length = 30, updatable = false)
    private String bookName;
    @Column(nullable = false, length = 30, updatable = false)
    private String bookAuthor;
    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Language lang;
    @Column(nullable = false, updatable = true)
    private int bookAmount;

    public Long getBookId() 
    {
        return bookId;
    }

    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
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
        hash = 37 * hash + Objects.hashCode(this.bookId);
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
        if (!Objects.equals(this.bookId, other.bookId)) 
        {
            return false;
        }
        return true;
    }
    
}
