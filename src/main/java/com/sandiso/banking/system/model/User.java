package com.sandiso.banking.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "User")
@Scope("session")
public class User implements UserDetails {

    public static enum Role { USER }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    /**
     * Description of the property email.
     */
    @Column(unique = true)
    private String username ;
    /**
     * Description of the property password.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password ;

    private String  role;
    /**
     * Description of the property full name.
     */
    private String fullName;
    private String surname;
    private String cellphone;

    @OneToOne
    private FixedAccount fixedAccount;
    @OneToOne
    private SavingsAccount savingsAccount;

    public User() {
    }

    public User(String username, String password, String fullName, String surname,
                String cellphone, FixedAccount fixedAccount, SavingsAccount savingsAccount) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.surname = surname;
        this.cellphone = cellphone;
        this.fixedAccount = fixedAccount;
        this.savingsAccount = savingsAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", fullName='" + fullName + '\'' +
                ", surname='" + surname + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", fixedAccount=" + fixedAccount +
                ", savingsAccount=" + savingsAccount +
                '}';
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public FixedAccount getFixedAccount() {
        return fixedAccount;
    }

    public void setFixedAccount(FixedAccount fixedAccount) {
        this.fixedAccount = fixedAccount;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    public Long getId() {
        return id;
    }
}
