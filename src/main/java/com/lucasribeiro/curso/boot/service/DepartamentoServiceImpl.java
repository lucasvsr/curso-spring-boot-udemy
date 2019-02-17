package com.lucasribeiro.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasribeiro.curso.boot.dao.DepartamentoDAO;
import com.lucasribeiro.curso.boot.domain.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	DepartamentoDAO dao;
	
	@Transactional(readOnly = false)
	public void salvar(Departamento departamento) {
		
		dao.save(departamento);

	}

	@Transactional(readOnly = false)
	public void editar(Departamento departamento) {
		
		dao.update(departamento);

	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		
		dao.delete(id);

	}

	@Transactional(readOnly = true)
	public Departamento buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Departamento> buscarTodos() {
		
		return dao.findAllBy();
	}

	@Override
	public boolean departamentoTemCargos(Long id) {
		
		if(buscarPorId(id).getCargos().isEmpty())
		{ 
			return false; 
		} 
		
		return true;
	}

}
