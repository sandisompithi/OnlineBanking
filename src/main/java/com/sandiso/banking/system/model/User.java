package com.sandiso.banking.system.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String surname;
    private String email;
    private String cellphone;

    @OneToOne
    private FixedAccount fixedAccount;

    @OneToOne
    private SavingsAccount savingsAccount;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {}

    public User(String firstName, String surname, String email, String cellphone,
                FixedAccount fixedAccount, SavingsAccount savingsAccount, String password) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.cellphone = cellphone;
        this.fixedAccount = fixedAccount;
        this.savingsAccount = savingsAccount;
        this.password = password;
    }

    public User(String firstName, String surname, String email, String cellphone,
                FixedAccount fixedAccount, SavingsAccount savingsAccount, String password,
                Collection<Role> roles) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.cellphone = cellphone;
        this.fixedAccount = fixedAccount;
        this.savingsAccount = savingsAccount;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", fixedAccount=" + fixedAccount +
                ", savingsAccount=" + savingsAccount +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
