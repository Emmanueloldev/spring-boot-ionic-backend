package com.nelioalves.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.dto.CategoriaDTO;
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
	
	@RequestMapping(method=RequestMethod.POST)	// Anotações do framework para reconhecer o metodo feito abaixo como metodo que vai ser mapeado no EndPoint CATEGORIA e no metodo POST
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){ //@RequestBody faz o JASON ser convertido para o objeto JAVA automaticamente 
		
		obj = service.insert(obj); // "Obj" vai ser inserido no Banco de dados, o Bd vai atribuir o novo "Id" para o Objeto obj//
		// pegar o novo "Id" e fornecer como argumento da URI//
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); 
	}

}
