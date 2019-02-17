package com.lucasribeiro.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasribeiro.curso.boot.dao.FuncionarioDAO;
import com.lucasribeiro.curso.boot.domain.Funcionario;

@Service
@Transactional(readOnly = true)
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	FuncionarioDAO dao;
	
	@Transactional(readOnly = false)
	public void salvar(Funcionario funcionario) {
		
		dao.save(funcionario);

	}

	@Transactional(readOnly = false)
	public void editar(Funcionario funcionario) {
		
		dao.update(funcionario);

	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		
		dao.delete(id);

	}

	@Override
	public Funcionario buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	public List<Funcionario> buscarTodos() {
		
		return dao.findAllBy();
	}

}
