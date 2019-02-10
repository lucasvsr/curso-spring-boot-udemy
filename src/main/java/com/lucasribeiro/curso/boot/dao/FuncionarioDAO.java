package com.lucasribeiro.curso.boot.dao;

import java.util.List;

import com.lucasribeiro.curso.boot.domain.Funcionario;

public interface FuncionarioDAO {
	
void save(Funcionario funcionario);
	
	void update(Funcionario funcionario);
	
	void delete(Long id);
	
	Funcionario findById(Long id);
	
	List<Funcionario> findAllBy();

}
