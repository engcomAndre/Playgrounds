package com.backend.entity.users;

import com.backend.entity.address.Address;
import com.backend.entity.contacts.Contact;
import com.backend.entity.users.enums.TypeUser;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String cpf;
    private Date bornDate;
    private Date updatedDate;

    @NotEmpty(message = "A senha deve ser informada!")
    @NotBlank(message = "A senha deve ser informada!")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "APP_TYPE_USER_AUTHORITIES")
    private Set<Integer> user_authorities = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public ApplicationUser() {
    }

    public ApplicationUser(String name, String cpf, TypeUser typeUser, Date bornDate, Date updatedDate,String password) {
        this.name = name;
        this.cpf = cpf;
        this.bornDate = bornDate;
        this.updatedDate = updatedDate;
        this.user_authorities.add(typeUser.getCod());
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<TypeUser> getAuthorities() {
        return user_authorities.stream().map(TypeUser::toEnum).collect(Collectors.toSet());
    }

    public void setAuthorities(Set<TypeUser> user_authorities) {
        this.user_authorities = user_authorities.stream().map(TypeUser::getCod).collect(Collectors.toSet());
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ApplicationUser other = (ApplicationUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}

