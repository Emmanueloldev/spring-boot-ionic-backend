package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

//SERVIÇO DE CONSULTA DE CLIENTE//
@Service
public class ClienteService {
	
	//BUSCA CLIENTE POR CODIGO. 
	@Autowired
	private ClienteRepository repo; // ( repo= OBJETO DE ACESSO A DADOS) DEPENDENCIA intanciando o objeto repo automaticamente pela anotação @Autowired//
	
	public Cliente buscar(Integer id) {
		Optional <Cliente>obj = repo.findById(id); //"Busca objeto por id"//
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}