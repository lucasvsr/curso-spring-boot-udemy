package com.lucasribeiro.curso.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucasribeiro.curso.boot.domain.Cargo;
import com.lucasribeiro.curso.boot.domain.Departamento;
import com.lucasribeiro.curso.boot.service.CargoService;
import com.lucasribeiro.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap map) {
		map.addAttribute("cargos", cargoService.buscarTodos());
		return "cargo/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult resultado, RedirectAttributes attr) {
		
		if(resultado.hasErrors()) {
			return "/cargo/cadastro";
		}
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo incluído com sucesso.");
				
		return "redirect:/cargos/cadastrar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listarDepartamentos() {
			return departamentoService.buscarTodos();
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap map) {
		
		map.addAttribute("cargo", cargoService.buscarPorId(id));
		
		return "cargo/cadastro";
		
	}
	
	@PostMapping("/editar") //QUANDO O RETORNO FOR UM REDIRECIONAMENTO, USAR O REDIRECTATTRIBUTES
	public String editar(@Valid Cargo cargo, BindingResult resultado, RedirectAttributes attr) {
		
		if(resultado.hasErrors()) {
			return "/cargo/cadastro";
		}
		
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Cargo alterado com sucesso.");
		
		return "redirect:/cargos/cadastrar";
		
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap map) {
		
		if(cargoService.cargoTemFuncionarios(id)) {
			
			map.addAttribute("fail", "Cargo não removido. Possui funcionário(s) vinculado(s).");
			
		} else {
			
			map.addAttribute("success", "Cargo excluído com sucesso.");
			cargoService.excluir(id);
		}
		
		return listar(map);
		
	}
	
}
