package com.lucasribeiro.curso.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.lucasribeiro.curso.boot.domain.Cargo;
import com.lucasribeiro.curso.boot.service.CargoService;

@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

	@Autowired
	private CargoService service;

	@Override
	public Cargo convert(String source) {
		
		if(source.isEmpty()) {
			return null;
		}
		
		
		return service.buscarPorId(Long.valueOf(source));
	}

}
