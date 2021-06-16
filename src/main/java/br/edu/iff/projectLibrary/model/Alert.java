package br.edu.iff.projectLibrary.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Alert implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Long alertId;
    private Long idBook;
    private Long idMember;
    private Date issueDate;
    private Date loanTime;
    private Date returnDate;

    public Long getAlertID() 
    {
        return alertId;
    }

    public void setAlertID(Long alertID) 
    {
        this.alertId = alertID;
    }

    public Long getIdBook() 
    {
        return idBook;
    }

    public void setIdBook(Long idBook) 
    {
        this.idBook = idBook;
    }

    public Long getIdMember() 
    {
        return idMember;
    }

    public void setIdMember(Long idMember) 
    {
        this.idMember = idMember;
    }
    
    public Date getIssueDate() 
    {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) 
    {
        this.issueDate = issueDate;
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
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.alertId);
        hash = 83 * hash + Objects.hashCode(this.idBook);
        hash = 83 * hash + Objects.hashCode(this.idMember);
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
        if (!Objects.equals(this.alertId, other.alertId)) 
        {
            return false;
        }
        if (!Objects.equals(this.idBook, other.idBook)) 
        {
            return false;
        }
        if (!Objects.equals(this.idMember, other.idMember)) 
        {
            return false;
        }
        return true;
    }

       
}
