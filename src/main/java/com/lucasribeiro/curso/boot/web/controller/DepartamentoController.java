package com.lucasribeiro.curso.boot.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucasribeiro.curso.boot.domain.Departamento;
import com.lucasribeiro.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap map) {
		map.addAttribute("departamentos", service.buscarTodos());
		
		return "departamento/lista";
		
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult resultado, RedirectAttributes attr) {
		
		if(resultado.hasErrors()) {
			return "/departamento/cadastro";
		}
		
		service.salvar(departamento);
		attr.addFlashAttribute("success", "Departamento incluído com sucesso.");
				
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap map) {
		
		map.addAttribute("departamento", service.buscarPorId(id));
		
		return "/departamento/cadastro";
		
	}
	
	@PostMapping("/editar") //QUANDO O RETORNO FOR UM REDIRECIONAMENTO, USAR O REDIRECTATTRIBUTES
	public String editar(@Valid Departamento departamento, BindingResult resultado, RedirectAttributes attr) {
		
		if(resultado.hasErrors()) {
			return "/departamento/cadastro";
		}
		
		service.editar(departamento);
		attr.addFlashAttribute("success", "Departamento alterado com sucesso.");
		
		return "redirect:/departamentos/cadastrar";
		
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap map) {
		
		if(service.departamentoTemCargos(id)) {
			
			map.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
			
		} else {
			
			map.addAttribute("success", "Departamento excluído com sucesso.");
			service.excluir(id);
		}
		
		return listar(map);
		
	}
	
}
