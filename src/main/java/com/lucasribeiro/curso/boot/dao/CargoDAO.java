package com.lucasribeiro.curso.boot.dao;

import java.util.List;

import com.lucasribeiro.curso.boot.domain.Cargo;

public interface CargoDAO {

	void save(Cargo cargo);
	
	void update(Cargo cargo);
	
	void delete(Long id);
	
	Cargo findById(Long id);
	
	List<Cargo> findAllBy();
	
}
