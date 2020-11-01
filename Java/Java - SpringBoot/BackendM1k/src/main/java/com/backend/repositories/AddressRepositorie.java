package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entity.address.Address;

@Repository
public interface AddressRepositorie extends JpaRepository<Address, Integer>{

}
