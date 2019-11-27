package com.mitocode.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mitocode.model.Cliente;
import com.mitocode.model.Usuario;
//import com.mitocode.model.Usuario;
import com.mitocode.service.IClienteService;
//import com.mitocode.service.IUsuarioService;
import com.mitocode.service.IUsuarioService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService service;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
//	@PostMapping
//	public Cliente registrar(@RequestBody Cliente g) {
//		return service.registrar(g);
//	}
	
	@PostMapping
	public Cliente registrar(@RequestPart("cliente") Cliente g, @RequestPart("usuario") Usuario us, @RequestPart("file") MultipartFile file)
			throws IOException {
		Cliente c = g;
		c.setFoto(file.getBytes());
		us.setCliente(g);
		us.setClave(bcrypt.encode(us.getClave()));
		Cliente newCliente = service.registrar(g);
		usuarioService.registrarTransaccional(us);
		return newCliente;
	}

//	@PutMapping
//	public Cliente modificar(@RequestBody Cliente g) {
//		return service.modificar(g);
//	}
	
	@PutMapping
	public Cliente modificar(@RequestPart("cliente") Cliente g, @RequestPart("file") MultipartFile file)
			throws IOException {
		Cliente c = g;
		c.setFoto(file.getBytes());
		Cliente updCliente = service.modificar(g);
		return updCliente;
	}

//	@GetMapping("/{id}")
//	public Cliente listarPorId(@PathVariable("id") Integer id) {
//		return service.listarPorId(id);
//	}
	
	@GetMapping(value = "/foto/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> fotoPorId(@PathVariable("id") Integer id) {
		Cliente c = service.listarPorId(id);
		byte[] data = c.getFoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable("id") Integer id) {
		Cliente c = service.listarPorId(id);
		return new ResponseEntity<Cliente>(c, HttpStatus.OK);
	}

	@GetMapping
	public List<Cliente> listar() {
		return service.listar();
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		service.eliminar(id);
	}
	
}
