package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

//SERVIÇO DE CONSULTA DE CATEGORIAS//
@Service
public class CategoriaService {
	
	//BUSCA CATEGORIA POR CODIGO. 
	@Autowired
	private CategoriaRepository repo; // ( repo= OBJETO DE ACESSO A DADOS) DEPENDENCIA intanciando o objeto repo automaticamente pela anotação @Autowired//
	
	
	
	
	
	public Categoria find(Integer id) {            //"Busca objeto por id se ele existe"// find=buscar
		Optional <Categoria>obj = repo.findById(id); //"Busca objeto por id"//
		return obj.orElseThrow(() -> new ObjectNotFoundException(             // EXCEÇÃO, caso o "Id" não exista
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
		
	
	
	
	
	
	public Categoria insert(Categoria obj) {        //" Inserir objeto"//
		obj.setId(null);
		return repo.save(obj);
	}
	
	
	
	
	
	public Categoria update(Categoria obj) { // recebe uma categoria objeto "obj" como argumento
		
		return repo.save(obj);
	}






	public void delete(Integer id) {
		// TODO Auto-generated method stub
		find(id);
		
		try {
		repo.deleteById(id);
		}
		catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}

}