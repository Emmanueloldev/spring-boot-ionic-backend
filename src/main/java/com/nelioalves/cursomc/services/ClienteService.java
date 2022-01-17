package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;




//SERVIÇO DE CONSULTA DE CLIENTE//
@Service
public class ClienteService {
	
	
	
	
	//BUSCA CLIENTE POR CODIGO. 
	@Autowired
	private ClienteRepository repo; // ( repo= OBJETO DE ACESSO A DADOS) DEPENDENCIA intanciando o objeto repo automaticamente pela anotação @Autowired//
	
	public Cliente find(Integer id) {
		Optional <Cliente>obj = repo.findById(id); //"Busca objeto por id"//
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	
	
	
	
	
	
	public Cliente update(Cliente obj) { // recebe uma categoria objeto "obj" como argumento
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}






	public void delete(Integer id) {
		// TODO Auto-generated method stub
		find(id);
		
		try {
		repo.deleteById(id);
		}
		catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}
	}
	
	
	
	
	public List<Cliente> findAll(){
		return repo.findAll();	
	}
	
		
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}