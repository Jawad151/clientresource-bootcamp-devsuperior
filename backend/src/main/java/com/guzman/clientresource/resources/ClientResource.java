package com.guzman.clientresource.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guzman.clientresource.entities.Client;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@GetMapping
	public ResponseEntity<List<Client>> findAll (){
		
		List<Client> list = new ArrayList<>();
		
		list.add(new Client(1L, "José da Silva", "12345678901", 1500.00, null, 3));
		
		return ResponseEntity.ok().body(list);
	}
	
}
