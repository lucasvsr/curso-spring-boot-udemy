package com.lucasribeiro.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.lucasribeiro.curso.boot.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDAO<Funcionario, Long> implements FuncionarioDAO {

}
