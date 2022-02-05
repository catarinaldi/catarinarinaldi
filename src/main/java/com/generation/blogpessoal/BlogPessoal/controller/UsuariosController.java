package com.generation.blogpessoal.BlogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.BlogPessoal.model.Login;
import com.generation.blogpessoal.BlogPessoal.model.UsuariosModel;
import com.generation.blogpessoal.BlogPessoal.repository.UsuariosRepository;
import com.generation.blogpessoal.BlogPessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuariosController {
	
	@Autowired
	private UsuariosRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/all")
	public ResponseEntity<List<UsuariosModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuariosModel> getById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<List<UsuariosModel>> getByUsuarios (@PathVariable String nome)  {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping("/logar")
	public ResponseEntity <Login> Autentication (@RequestBody Optional<Login> usuario) {
		return usuarioService.logar(usuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity <UsuariosModel> Post (@RequestBody UsuariosModel usuario) {
		return usuarioService.cadastrarUsuario(usuario);
	}
//	public ResponseEntity <UsuariosModel> Post (@Valid @RequestBody UsuariosModel usuario) {
//		return usuarioService.cadastrarUsuario(usuario)
//				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
//				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
//	}

//	@PutMapping("/edit")
//	public ResponseEntity <UsuariosModel> editUsuarios (@RequestBody UsuariosModel editUsuarios) {
//		return ResponseEntity.status (200).body(repository.save(editUsuarios));
//	}
//	
//	@DeleteMapping("/delete/{id}")
//	public void deleteUsuarios (@PathVariable long id) {
//		repository.deleteById(id);
//	}

}
