package com.guzman.clientresource.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guzman.clientresource.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
