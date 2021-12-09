package com.nelioalves.cursomc.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Cliente;

//CAMADA DE ACESSO A DADOS ( REPOSITORY )= FOI CRIADA UM OBJETO CATEGORIA QUE TA MAPEADO A TABELA CATEGORIA NO BANCO DE DADOS )//

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { //OBJETO CategoriaRepository ACESSA OS DADOS// INTEGER PQ É O TIPO DO ATRIBUTO DO IDENTIFICADOR//

		
	

}
