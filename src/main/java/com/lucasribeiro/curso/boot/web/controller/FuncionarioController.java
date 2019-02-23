package com.lucasribeiro.curso.boot.web.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucasribeiro.curso.boot.domain.Cargo;
import com.lucasribeiro.curso.boot.domain.Funcionario;
import com.lucasribeiro.curso.boot.domain.UF;
import com.lucasribeiro.curso.boot.service.CargoService;
import com.lucasribeiro.curso.boot.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService;
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap map) {
		map.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "funcionario/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Cargo incluído com sucesso.");
				
		return "redirect:/funcionarios/cadastrar";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> getCargos() {
		
		return cargoService.buscarTodos();
	}

	@ModelAttribute("ufs")
	public UF[] getUFs() {
		
		return UF.values();
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap map) {
		
		map.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		
		return "funcionario/cadastro";
		
	}
	
	@PostMapping("/editar") //QUANDO O RETORNO FOR UM REDIRECIONAMENTO, USAR O REDIRECTATTRIBUTES
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		
		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("success", "Departamento alterado com sucesso.");
		
		return "redirect:/funcionarios/cadastrar";
		
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap map) {
		
		map.addAttribute("success", "Funcionário excluído com sucesso.");
		funcionarioService.excluir(id);
		return listar(map);
		
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap map) {
		
		map.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
		
		return "/funcionario/lista";
		
	}
	
	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long cargo, ModelMap map) {
		
		map.addAttribute("funcionarios", funcionarioService.buscarPorCargo(cargo));
		
		return "/funcionario/lista";
		
	}
	
	@GetMapping("/buscar/data")
	public String getPorDatas(@RequestParam("entrada")
							  @DateTimeFormat(iso = ISO.DATE) LocalDate entrada,
							  @RequestParam("saida")
							  @DateTimeFormat(iso = ISO.DATE) LocalDate saida,
							  ModelMap map) {
		
		map.addAttribute("funcionarios", funcionarioService.buscarPorDatas(entrada, saida));
		
		return "/funcionario/lista";
		
	}
	
	
}
