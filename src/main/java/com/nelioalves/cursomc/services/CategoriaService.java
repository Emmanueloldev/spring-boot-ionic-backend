package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

//SERVIÇO DE CONSULTA DE CATEGORIAS//
@Service
public class CategoriaService {
	
	//BUSCA CATEGORIA POR CODIGO. 
	@Autowired
	private CategoriaRepository repo; // ( repo= OBJETO DE ACESSO A DADOS) DEPENDENCIA intanciando o objeto repo automaticamente pela anotação @Autowired//
	
	public Categoria buscar(Integer id) {
		Optional <Categoria>obj = repo.findById(id); //"Busca objeto por id"//
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}