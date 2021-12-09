package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired //INSTANCIA AUTOMATICAMENTE // DEPENDENCIA
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
		
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		Categoria cat1 = new Categoria(null,"Informática"); 
		Categoria cat2 = new Categoria(null, "Escritório");
				
		
		
		
		Produto p1 = new Produto(null, "Compudaor", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com ","8975498372", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("981678844", "99889798"));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		
		Endereco e1 = new Endereco(null, "Rua flores", "360", "Apto 303", "Jardim", "365465456", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos","105", "sala 800","centro","65456465",cli1, c2);
		
		
		est1.getCidades().addAll(Arrays.asList(c1)); //Cidade c1 pertence a est1
		est2.getCidades().addAll(Arrays.asList(c2, c3));//Cidade c3 e c3 pertence a est2
				
		
		
		cli1.getEndereco().addAll(Arrays.asList(e1, e2)); // Associar Cliente ao endereço e1 e e2.
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
			
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2)); //Caterogias cat1 e cat2 Salvas no Banco
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));//Estado est1 e est2 salvas no Banco de dados
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));//Cidade c1 e c2 salvas no Banco de dados
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}
