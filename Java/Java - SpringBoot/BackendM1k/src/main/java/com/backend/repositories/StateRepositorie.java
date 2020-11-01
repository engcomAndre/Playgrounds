package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entity.address.State;

@Repository
public interface StateRepositorie extends JpaRepository<State, Integer>{

}
