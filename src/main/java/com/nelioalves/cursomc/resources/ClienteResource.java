package com.nelioalves.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.services.ClienteService;

@RestController //CONTROLADOR REST

@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service; //ACESSANDO O SERVICE ( CAMADA SERVIÇO )

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {  //<?> indica que pode ser qq tipo//
		
				
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
		
		
	}

	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)      //Mistura do "GET" e do "POST", ele recebe o objeto, e tbm recebe o parametro na URL
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){  // retorna um corpo vazio quando a atualizaçao ocorrer com sucesso, chamamos de update
		Cliente obj = service.fromDTO(objDto);
		
		obj.setId(id); 
		obj =  service.update(obj);                                      // chamar no "service", o obj recebendo "service.update", cria esse metodo la passando o "obj" aqui
		                                                  //se quiser antes de chamar update p garantir que realmente a categoria que vai ser atualizada é que vai ser passada o codigo na URL  
	
		return ResponseEntity.noContent().build();   // " noContent() = conteudo vazio
	}
	
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);                             //chamar no serviço um metodo pra deletar
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {  // Método que retonar a lista de categoria DTO

		List <Cliente> list = service.findAll();
		List <ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam (value="page", defaultValue="0") Integer page, 
			@RequestParam (value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam (value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam (value="direction", defaultValue="ASC")String direction) {  // Método que retonar a lista de categoria DTO

		Page <Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page <ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	
}
}
