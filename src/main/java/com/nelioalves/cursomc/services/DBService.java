package com.nelioalves.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;



@Service //DBService passa a ser um componente do Spring
public class DBService {
	
	
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public void instantiateTestDatabase() throws ParseException {  //Gera uma excessão de Parse da data
		
		// Estanciação //

				Categoria cat1 = new Categoria(null,"Informática"); 
				Categoria cat2 = new Categoria(null,"Escritório");
				Categoria cat3 = new Categoria(null,"Cama mesa e banho" );
				Categoria cat4 = new Categoria(null,"Eletrônicos");
				Categoria cat5 = new Categoria(null,"Jardinagem");
				Categoria cat6 = new Categoria(null,"Decoração");
				Categoria cat7 = new Categoria(null,"Perfumaria");
				

				
				// Estanciação //
				Produto p1 = new Produto(null, "Compudaor", 2000.00);
				Produto p2 = new Produto(null, "Impressora", 800.00);
				Produto p3 = new Produto(null, "Mouse", 80.00);
				Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
				Produto p5 = new Produto(null, "Toalha", 50.00);
				Produto p6 = new Produto(null, "Colcha", 200.00);
				Produto p7 = new Produto(null, "TV true color", 80.00);
				Produto p8 = new Produto(null, "Roçadeira", 800.00);
				Produto p9 = new Produto(null, "Abajour", 100.00);
				Produto p10 = new Produto(null, "Pendente", 180.00);
				Produto p11 = new Produto(null, "Shampoo", 90.00);
				
				// Estanciação //

				Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com ","8975498372", TipoCliente.PESSOAFISICA);
				cli1.getTelefones().addAll(Arrays.asList("981678844", "99889798"));
				
				
				// Estanciação //

				Estado est1 = new Estado(null, "Minas Gerais");
				Estado est2 = new Estado(null, "São Paulo");
				
				// Estanciação //
				
				Cidade c1 = new Cidade(null, "Uberlândia", est1);
				Cidade c2 = new Cidade(null, "São Paulo", est2);
				Cidade c3 = new Cidade(null, "Campinas", est2);
				
				// Estanciação //

				Endereco e1 = new Endereco(null, "Rua flores", "360", "Apto 303", "Jardim", "365465456", cli1, c1);
				Endereco e2 = new Endereco(null, "Avenida Matos","105", "sala 800","centro","65456465",cli1, c2);
				
				
				est1.getCidades().addAll(Arrays.asList(c1));    // ASSOCIAÇÕES    //Cidade c1 pertence a est1
				est2.getCidades().addAll(Arrays.asList(c2, c3));// ASSOCIAÇÕES    //Cidade c3 e c3 pertence a est2
						
				
				
				cli1.getEnderecos().addAll(Arrays.asList(e1, e2)); // Associar Cliente ao endereço e1 e e2.
				
				
				cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));     // ASSOCIAÇÕES    //
				cat2.getProdutos().addAll(Arrays.asList(p2, p4));         // ASSOCIAÇÕES    //
				cat3.getProdutos().addAll(Arrays.asList(p5, p6));         // ASSOCIAÇÕES    //
				cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7)); // ASSOCIAÇÕES    //
				cat5.getProdutos().addAll(Arrays.asList(p8));             // ASSOCIAÇÕES    //
				cat6.getProdutos().addAll(Arrays.asList(p9, p10));        // ASSOCIAÇÕES    //
				cat7.getProdutos().addAll(Arrays.asList(p11));            // ASSOCIAÇÕES    //
				
				p1.getCategorias().addAll(Arrays.asList(cat1, cat4));       // ASSOCIAÇÕES    //
				p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));// ASSOCIAÇÕES    //
				p3.getCategorias().addAll(Arrays.asList(cat1, cat4));             // ASSOCIAÇÕES    //
				p4.getCategorias().addAll(Arrays.asList(cat2));
				p5.getCategorias().addAll(Arrays.asList(cat3));
				p6.getCategorias().addAll(Arrays.asList(cat3));
				p7.getCategorias().addAll(Arrays.asList(cat4));
				p8.getCategorias().addAll(Arrays.asList(cat5));
				p9.getCategorias().addAll(Arrays.asList(cat6));
				p10.getCategorias().addAll(Arrays.asList(cat6));
				p11.getCategorias().addAll(Arrays.asList(cat7));
						
					
				
				//Salvando os objetos no Banco de dados
				
				categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7)); //Caterogias cat1 e cat2 Salvas no Banco
				produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
				
				estadoRepository.saveAll(Arrays.asList(est1,est2));//Estado est1 e est2 salvas no Banco de dados
				cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));//Cidade c1 e c2 salvas no Banco de dados
				
				clienteRepository.saveAll(Arrays.asList(cli1));
				enderecoRepository.saveAll(Arrays.asList(e1, e2));
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				
				Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1, e1); //INSTANCIA DOS PEDIDOS
				Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
				
				Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
				ped1.setPagamento(pagto1);
				
				Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:01"), null);
				ped2.setPagamento(pagto2);
				
				
				cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
				
				pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
				pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
				
				
				
				
				//instanciação
				ItemPedido ip1 = new ItemPedido(ped1, p1,0.00, 1, 2000.00); 
				ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
				ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
				
				//Associação 
				
				//( PEDIDO CONHECE ITENS )
				
				ped1.getItens().addAll(Arrays.asList(ip1,ip2));
				ped2.getItens().addAll(Arrays.asList(ip3));
				
				// (PRODUTO CONHECE ITENS)
				
				p1.getItens().addAll(Arrays.asList(ip1));
				p2.getItens().addAll(Arrays.asList(ip3));
				p3.getItens().addAll(Arrays.asList(ip2));
				
				//Salvar na Base de Dados - REPOSITORY
				
				itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2, ip3));
				
	}

}
