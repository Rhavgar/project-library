package br.edu.iff.projectLibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@JsonIgnoreProperties(value = "password", allowGetters = false, allowSetters = true)
public class LibMember extends Person
{
    @Column(nullable = false, length = 50, updatable = true)
    @NotBlank(message = "Endereço é obrigatório.")
    @Length(max = 50, message = "Endereço deve ter no máximo 50 caracteres.")
    private String address;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Data de Nascimento é obrigatório.")
    @Past(message = "Data de Nascimento deve ser no passado.")
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private Calendar birthday;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "libMember")
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

    public List<Alert> getAlerts()
    {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts)
    {
        this.alerts = alerts;
    }

}
