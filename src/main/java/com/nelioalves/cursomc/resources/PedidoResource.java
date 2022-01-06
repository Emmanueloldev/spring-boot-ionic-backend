package com.nelioalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.services.PedidoService;

@RestController //CONTROLADOR REST

@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service; //ACESSANDO O SERVICE ( CAMADA SERVIÇO )

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {  //<?> indica que pode ser qq tipo//
		
				
		Pedido obj = service.find(id);        //Executa o serviço " buscar "
		return ResponseEntity.ok().body(obj);
		
		
		
	}

}
