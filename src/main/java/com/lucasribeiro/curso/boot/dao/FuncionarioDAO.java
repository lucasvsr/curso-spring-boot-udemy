package com.lucasribeiro.curso.boot.dao;

import java.time.LocalDate;
import java.util.List;

import com.lucasribeiro.curso.boot.domain.Funcionario;

public interface FuncionarioDAO {
	
void save(Funcionario funcionario);
	
	void update(Funcionario funcionario);
	
	void delete(Long id);
	
	Funcionario findById(Long id);
	
	List<Funcionario> findAllBy();

	List<Funcionario> findByNome(String nome);

	List<Funcionario> findByCargoId(Long cargo);

	List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);

	List<Funcionario> findByDataEntrada(LocalDate entrada);

	List<Funcionario> findByDataSaida(LocalDate saida);

	

}
