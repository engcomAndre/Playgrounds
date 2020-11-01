package com.backend.entity.users;

import com.backend.entity.users.enums.TypeUser;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@PrimaryKeyJoinColumn(name = "application_user_id")
public class Admin extends ApplicationUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Admin() {
        super();
    }

    public Admin(String name, String cpf, TypeUser typeUser, Date bornDate, Date updatedDate,String password) {
        super(name, cpf, TypeUser.HAPVIDA_USER, bornDate, updatedDate,password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Admin other = (Admin) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
