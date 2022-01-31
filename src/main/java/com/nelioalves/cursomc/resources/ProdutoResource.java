package com.nelioalves.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.dto.ProdutoDTO;
import com.nelioalves.cursomc.resources.utils.URL;
import com.nelioalves.cursomc.services.ProdutoService;

@RestController //CONTROLADOR REST // END POINT

@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service; //ACESSANDO O SERVICE ( CAMADA SERVIÇO )

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)  // END POIN para buscar por " Id "
	public ResponseEntity<Produto> find(@PathVariable Integer id) {  //<?> indica que pode ser qq tipo//
		
				
		Produto obj = service.find(id);        //Executa o serviço " buscar "
		return ResponseEntity.ok().body(obj);
		
		
		
	}
	
	// BUSCA PAGINADA POR CATEGORIA //
	
	@RequestMapping(method = RequestMethod.GET) // CONSULTA METODO GET DO HTTP , RECUPERAR DADOS // METODO GET NÃO ACEITA ENVIAR OS PARAMENTROS NO CORPO DA REQUISIÇÃO 
	public ResponseEntity<Page<ProdutoDTO>> findPage(     // Método que retonar a lista de categoria DTO
			@RequestParam (value="nome", defaultValue="") String nome, 
			@RequestParam (value="categoria", defaultValue="") String categorias, 

			@RequestParam (value="page", defaultValue="0") Integer page, 
			@RequestParam (value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam (value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam (value="direction", defaultValue="ASC")String direction) {  
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page <Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page <ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
