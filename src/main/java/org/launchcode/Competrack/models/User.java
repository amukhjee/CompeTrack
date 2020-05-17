package org.launchcode.Competrack.models;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "auth_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auth_user_id")
    private int id;

    @Column(name = "username")


    private String username;

    @Column(name = "last_name")

    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")

    private String passwordHash;



    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public User( String username, String lastName, String email, String passwordHash,  Set<org.launchcode.Competrack.models.Role> roles) {
            this.username = username;
            this.lastName = lastName;
            this.email = email;
            this.passwordHash = encoder.encode(passwordHash);

            this.roles = roles;
        }

    public User() {
        }

        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
        private Set<org.launchcode.Competrack.models.Role> roles;

    public User(String username, String passwordHash) {

    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String name) {
            this.username = username;
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

        public boolean isMatchingPassword(String password) {
            return encoder.matches(password, passwordHash);}

            public void setEmail(String email) {
                this.email = email;
            }








                    public Set<org.launchcode.Competrack.models.Role> getRoles () {
                        return roles;
                    }

                    public void setRoles (Set <Role> roles) {
                        this.roles = roles;
                    }


                    public String getPasswordHash () {
                        return passwordHash;
                    }

                    public void setPasswordHash (String password){
                        this.passwordHash = passwordHash;
                    }


                }