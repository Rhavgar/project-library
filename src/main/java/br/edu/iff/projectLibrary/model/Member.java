package br.edu.iff.projectLibrary.model;

import java.util.Date;


public class Member extends Person 
{
    private String mPassword;
    private String address;
    private Date birthday;

    public String getmPassword() 
    {
        return mPassword;
    }

    public void setmPassword(String mPassword) 
    {
        this.mPassword = mPassword;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public Date getBirthday() 
    {
        return birthday;
    }

    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }
    
}
