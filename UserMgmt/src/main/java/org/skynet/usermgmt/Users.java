package org.skynet.usermgmt;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "`Users`")
public class Users {

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Nationality", nullable = false)
    private String nationality;

    @Column(name = "MobileNumber", unique = true, nullable = false)
    private String mobileNumber;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "SSN", unique = true, nullable = false)
    private String SSN;

    @Column(name = "DateOfBirth", nullable = false)
    private Date dateOfBirth;

    @Id
    @Column(name = "MemberID", unique = true, nullable = false)
    private String memberID;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID)
    {
        this.memberID = memberID;
    }
}
