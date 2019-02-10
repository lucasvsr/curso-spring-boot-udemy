package com.lucasribeiro.curso.boot.dao;

import java.util.List;
import com.lucasribeiro.curso.boot.domain.Departamento;

public interface DepartamentoDAO {
	
	void save(Departamento departamento);
	
	void update(Departamento departamento);
	
	void delete(Long id);
	
	Departamento findById(Long id);
	
	List<Departamento> findAllBy();
}
