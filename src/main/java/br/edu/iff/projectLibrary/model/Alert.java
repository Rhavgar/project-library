package br.edu.iff.projectLibrary.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Alert implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Date issueDate;
    private long idBook;
    private long idMember;
    private Date loanTime;
    private Date returnDate;

    public Date getIssueDate() 
    {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) 
    {
        this.issueDate = issueDate;
    }

    public long getIdBook() 
    {
        return idBook;
    }

    public void setIdBook(long idBook) 
    {
        this.idBook = idBook;
    }

    public long getIdMember() 
    {
        return idMember;
    }

    public void setIdMember(long idMember) 
    {
        this.idMember = idMember;
    }

    public Date getLoanTime() 
    {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) 
    {
        this.loanTime = loanTime;
    }

    public Date getReturnDate() 
    {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) 
    {
        this.returnDate = returnDate;
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.issueDate);
        hash = 31 * hash + (int) (this.idBook ^ (this.idBook >>> 32));
        hash = 31 * hash + (int) (this.idMember ^ (this.idMember >>> 32));
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
        if (this.idBook != other.idBook) 
        {
            return false;
        }
        if (this.idMember != other.idMember) 
        {
            return false;
        }
        if (!Objects.equals(this.issueDate, other.issueDate)) 
        {
            return false;
        }
        return true;
    }
    
    
}
