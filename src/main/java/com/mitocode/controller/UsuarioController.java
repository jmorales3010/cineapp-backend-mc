package com.mitocode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.mitocode.model.Cliente;
import com.mitocode.model.Usuario;
//import com.mitocode.service.IClienteService;
import com.mitocode.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	private ResponseEntity<Object> registrar(@RequestBody Usuario usuario){		
		usuario.setClave(bcrypt.encode(usuario.getClave()));
		service.registrarTransaccional(usuario);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
		
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id) {
		Usuario u = service.listarPorId(id);
		return new ResponseEntity<Usuario>(u, HttpStatus.OK);
	}
}