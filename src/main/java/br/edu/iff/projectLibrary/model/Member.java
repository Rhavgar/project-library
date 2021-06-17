package br.edu.iff.projectLibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Member extends Person
{
    @Column(nullable = false, length = 50, updatable = true)
    private String address;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar birthday;
    
    @JsonBackReference
    @OneToMany(mappedBy = "member")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Alert> alerts = new ArrayList<>();
    
    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public Calendar getBirthday() 
    {
        return birthday;
    }

    public void setBirthday(Calendar birthday) 
    {
        this.birthday = birthday;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

}
