package com.nelioalves.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();  //Instancia de calendário
		cal.setTime(instanteDoPedido);          
 		cal.add(Calendar.DAY_OF_MONTH, 7);      // acresentando 7 dias depois do dia do pedido
		pagto.setDataVencimento(cal.getTime());
	}
}
