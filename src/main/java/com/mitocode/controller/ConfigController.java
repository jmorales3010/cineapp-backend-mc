package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Config;
import com.mitocode.service.IConfigService;

@RestController
@RequestMapping("/configuraciones")
public class ConfigController {
	
	@Autowired
	private IConfigService service;
	
	@PostMapping
	public Config registrar(@RequestBody Config g) {
		return service.registrar(g);
	}

	@PutMapping
	public Config modificar(@RequestBody Config g) {
		return service.modificar(g);
	}

	@GetMapping("/{id}")
	public Config listarPorId(@PathVariable("id") Integer id) {
		return service.listarPorId(id);
	}

	@GetMapping
	public List<Config> listar() {
		return service.listar();
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		service.eliminar(id);
	}
	
	@GetMapping(value = "/buscar/{param}")
	public Config listarPorId(@PathVariable("param") String param){
		return service.leerParametro(param);
	}
	
}
