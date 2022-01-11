package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.CategoriaService;

@RestController //CONTROLADOR REST
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service; //ACESSANDO O SERVICE ( CAMADA SERVIÇO )

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {  // Método "FIND" pra efetuar operação de "GET", passando "ID" e 
		//.... retornar CATEGORIA //
		// <?> indica que pode ser qq tipo//
		
				
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}

	@RequestMapping(method=RequestMethod.POST)	// Anotações do framework para reconhecer o metodo feito abaixo como metodo que vai ser mapeado no EndPoint CATEGORIA e no metodo POST
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){ //@RequestBody faz o JASON ser convertido para o objeto JAVA automaticamente 
		obj = service.insert(obj); // "Obj" vai ser inserido no Banco de dados, o Bd vai atribuir o novo "Id" para o Objeto obj
		// pegar o novo "Id" e fornecer como argumento da URI
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); 
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)      //Mistura do "GET" e do "POST", ele recebe o objeto, e tbm recebe o parametro na URL
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){  // retorna um corpo vazio quando a atualizaçao ocorrer com sucesso, chamamos de update
		obj =  service.update(obj);                                      // chamar no "service", o obj recebendo "service.update", cria esse metodo la passando o "obj" aqui
		obj.setId(id);                                                   //se quiser antes de chamar update p garantir que realmente a categoria que vai ser atualizada é que vai ser passada o codigo na URL  
	
		return ResponseEntity.noContent().build();   // " noContent() = conteudo vazio
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);                             //chamar no serviço um metodo pra deletar
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {  // Método que retonar a lista de categoria DTO

		List <Categoria> list = service.findAll();
		List <CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam (value="page", defaultValue="0") Integer page, 
			@RequestParam (value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam (value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam (value="direction", defaultValue="ASC")String direction) {  // Método que retonar a lista de categoria DTO

		Page <Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page <CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	
}
	
}
