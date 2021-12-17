package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

//SERVIÇO DE CONSULTA DE CATEGORIAS//
@Service
public class PedidoService {
	
	//BUSCA CATEGORIA POR CODIGO. 
	@Autowired
	private PedidoRepository repo; // ( repo= OBJETO DE ACESSO A DADOS) DEPENDENCIA intanciando o objeto repo automaticamente pela anotação @Autowired//
	
	public Pedido buscar(Integer id) {
		Optional <Pedido>obj = repo.findById(id); //"Busca objeto por id"//
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}