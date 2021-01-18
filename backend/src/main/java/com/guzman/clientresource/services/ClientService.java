package com.guzman.clientresource.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guzman.clientresource.dto.ClientDTO;
import com.guzman.clientresource.entities.Client;
import com.guzman.clientresource.repositories.ClientRepository;
import com.guzman.clientresource.services.exceptions.MyDatabaseIntegrityException;
import com.guzman.clientresource.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {

		List<Client> list = repository.findAll();

		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {

		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException(">>> [APPLICATION] Entity not found!"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {

		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);

	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {

		try {
			
			Client entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ClientDTO(entity);
		
		} catch (EntityNotFoundException e) {
		
			throw new ResourceNotFoundException(">>> [APPLICATION] ID not found! " + id);
		}
	}
	
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			
			throw new ResourceNotFoundException(">>> [APPLICATION] ID not found! " + id);
		
			/*
			 * Although there's not any N-N relation in this project yet, it seemed there
			 * was no problem to anticipate future tasks and make this exception available
			 * already.
			 */
		} catch (DataIntegrityViolationException e) {
		
			throw new MyDatabaseIntegrityException(">>> [APPLICATION] Data integrity violation!");
		}
		
	}

	// AUX METHODS
	private void copyDtoToEntity(ClientDTO dto, Client entity) {

		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}
