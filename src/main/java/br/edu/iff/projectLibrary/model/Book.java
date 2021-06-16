package br.edu.iff.projectLibrary.model;

import java.io.Serializable;
import java.util.Objects;


public class Book implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Long bookId;
    private String bookName;
    private String bookAuthor;
    private Language lang;
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
