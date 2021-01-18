package com.guzman.clientresource.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guzman.clientresource.entities.Client;
import com.guzman.clientresource.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public List<Client> findAll(){
		
		List<Client> list = repository.findAll();
		
		return list;
		
	}
}
