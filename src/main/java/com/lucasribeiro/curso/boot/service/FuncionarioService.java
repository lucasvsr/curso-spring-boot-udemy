package com.lucasribeiro.curso.boot.service;

import java.time.LocalDate;
import java.util.List;

import com.lucasribeiro.curso.boot.domain.Funcionario;

public interface FuncionarioService {
	
	void salvar(Funcionario funcionario);
	
	void editar(Funcionario Funcionario);
	
	void excluir(Long id);
	
	Funcionario buscarPorId(Long id);
	
	List<Funcionario> buscarTodos();

	List<Funcionario> buscarPorNome(String nome);

	List<Funcionario> buscarPorCargo(Long cargo);

	List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida);

	

}
