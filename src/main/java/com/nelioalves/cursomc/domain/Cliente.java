package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Column(unique=true) // Não pode ter Email repetido ...Não tem repetição no campo "Email" Chave canditada. 
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	
	//CLIENTE SERIALIZA ENDEREÇO
	@OneToMany(mappedBy= "cliente", cascade=CascadeType.ALL) // MAPEADO PELO CAMPO CLIENTE// cascade= em cascata.. se apagar um cliente por exemplo apaga um endereço//
	private List<Endereco> enderecos = new ArrayList<>(); //ASSOCIAÇÃO 
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>(); // coleção de string associado ao cliente // Set (CONJUNTO)
												// SET NÃO ACEITA REPETIÇÃO // HashSet =Classe para ser estanciada
	
	@JsonIgnore                 //Os pedidos de um cliente não vão ser SERIALIZADOS ( Os pedidos não vai ser visto pelo cliente)
	@OneToMany(mappedBy= "cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	

	public Cliente() {
	
}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo==null) ? null : tipo.getCod();//Se o tipo for igual a nulo, vou atribuir nulo ou codigo , caso o tipo informado não seja nulo
}

	public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getCpfOuCnpj() {
	return cpfOuCnpj;
}

public void setCpfOuCnpj(String cpfOuCnpj) {
	this.cpfOuCnpj = cpfOuCnpj;
}

public TipoCliente getTipo() {
	return TipoCliente.toEnum(tipo);
}

public void setTipo(TipoCliente tipo) {
	this.tipo = tipo.getCod();
}

public List<Endereco> getEnderecos() {
	return enderecos;
}

public void setEnderecos(List<Endereco> enderecos) {
	this.enderecos = enderecos;
}

public Set<String> getTelefones() {
	return telefones;
}

public void setTelefones(Set<String> telefones) {
	this.telefones = telefones;
}

public List<Pedido> getPedidos() {
	return pedidos;
}

public void setPedidos(List<Pedido> pedidos) {
	this.pedidos = pedidos;
}

@Override
public int hashCode() {
	return Objects.hash(id);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Cliente other = (Cliente) obj;
	return Objects.equals(id, other.id);
}




}


