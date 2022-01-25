package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.resources.exception.FieldMessage;
import com.nelioalves.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo; 
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}

	
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
			if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())&& !BR.isValidCPF(objDto.getCpfOuCnpj())){
				list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
			}
		
			if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())&& !BR.isValidCNPJ(objDto.getCpfOuCnpj())){ // Se o tipo do objDto for igual ao tipoCliente.PESSOALJURIDICA e não for válido ele como CNPJ eu vou dizer a mensagem CNPL INVÁLIDO                
				list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
			}
		
			
			Cliente aux = repo.findByEmail(objDto.getEmail()); // Lógica pra testa se o "Email" já existe
			if (aux != null) {
				list.add(new FieldMessage("email", "Email já existente")); // Erro de validação
			}
			
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
