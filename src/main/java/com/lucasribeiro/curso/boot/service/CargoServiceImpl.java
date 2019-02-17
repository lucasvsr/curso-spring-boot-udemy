package com.lucasribeiro.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasribeiro.curso.boot.dao.CargoDAO;
import com.lucasribeiro.curso.boot.domain.Cargo;

@Service
@Transactional(readOnly = false) //o valor false é padrão, não precisa usar
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoDAO dao;
	
	
	public void salvar(Cargo cargo) {
			
		dao.save(cargo);
	}

	
	public void editar(Cargo cargo) {
		
		dao.update(cargo);

	}

	
	public void excluir(Long id) {
		
		dao.delete(id);
	}

	@Transactional(readOnly = true)
	public Cargo buscarPorId(Long id) {
		
		return dao.findById(id);
		
	}

	@Transactional(readOnly = true)
	public List<Cargo> buscarTodos() {
		
		return dao.findAllBy();
	}

}
