package com.lucasribeiro.curso.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.lucasribeiro.curso.boot.domain.Departamento;
import com.lucasribeiro.curso.boot.service.DepartamentoService;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {
	
	@Autowired
	private DepartamentoService service;

	@Override
	public Departamento convert(String source) {
		
		if(source.isEmpty()) {
			return null;
		}
		
		
		return service.buscarPorId(Long.valueOf(source));
	}

}
