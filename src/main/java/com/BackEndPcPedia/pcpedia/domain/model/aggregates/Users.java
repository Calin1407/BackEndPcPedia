package com.BackEndPcPedia.pcpedia.domain.model.aggregates;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="Users")
@EntityListeners(AuditingEntityListener.class)
public class Users {

    /**
     * Attributes of Users
     * You can read all information in Diagram Data Base
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsers")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Embedded
    @AttributeOverride(name="value", column=@Column(
                                        name="email",
                                        nullable=false,
                                        unique=true,
                                        length=100))
    private Email email;

    @Embedded
    @AttributeOverride(name="value", column=@Column(
                                        name="password",
                                        nullable=false,
                                        length=100))
    private PasswordHash passwordHash;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 200)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    protected Users() {}

    private Users(String _name,
                  Email _email,
                  PasswordHash _password,
                  String _phoneNumber,
                  String _address,
                  Role _role){
        this.name = _name;
        this.email = _email;
        this.passwordHash = _password;
        this.phoneNumber = _phoneNumber;
        this.address = _address;
        this.role = (_role==null?Role.CLIENT:_role);
    }

    /**
     *  When start a new User, their initial role is null
     *  'cause that, we must assign them a role
     *  In this case, a new User is associated with CLIENT
     */
    @PrePersist
    private void prePersist(){
        if (role == null) role = Role.CLIENT;
    }

    public static Users register(String _name,
                                 String _email,
                                 PasswordHash _password,
                                 String _phoneNumber,
                                 String _address,
                                 Role _role){
        return new Users(
                _name,
                new Email(_email),
                _password,
                _phoneNumber,
                _address,
                _role
        );
    }


    public void changePassword(PasswordHash _new){
        if (_new==null){
            throw new IllegalArgumentException("New password is required");
        }
        this.passwordHash = _new;
    }


    public void assignRole(Role _role){
        if (_role!=null){
            this.role = _role;
        }
    }


    public void updateProfile(String _newNumberPhone, String _newAddress){
        if (_newAddress==null||_newAddress.isBlank()){
            throw new IllegalArgumentException("New Address is required");
        }
        if (_newNumberPhone==null||_newNumberPhone.isBlank()){
            throw new IllegalArgumentException("New Phone number is required");
        }

        this.phoneNumber = _newNumberPhone;
        this.address = _newAddress;
    }

    //Get values
    public Long getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email.value;}
    public PasswordHash getPasswordHash() {return passwordHash;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getAddress() {return address;}
    public Role getRole() {return role;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public LocalDateTime getUpdatedAt() {return updatedAt;}


    @Embeddable
    public static class Email{
        @Column(name="email")
        String value;

        protected Email(){}

        public Email(String value){
            if(value==null||!value.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"))
                throw new IllegalArgumentException("Email error");
            this.value=value;
        }
    }


    @Embeddable
    public static class PasswordHash {
        @Column(name = "password")
        private String value;

        protected PasswordHash() {
        }

        public PasswordHash(String value) {
            if (value == null || value.length() < 20)
                throw new IllegalArgumentException("Password error");
            this.value = value;
        }

        public static PasswordHash of(String value) {
            return new PasswordHash(value);
        }
        public String getValue() {
            return value;
        }
    }

    public enum Role{
        CLIENT,
        ADMIN
    }
}