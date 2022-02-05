package com.generation.blogpessoal.BlogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.BlogPessoal.model.Login;
import com.generation.blogpessoal.BlogPessoal.model.UsuariosModel;
import com.generation.blogpessoal.BlogPessoal.repository.UsuariosRepository;

@Service
public class UsuarioService {
	@Autowired
	
	private UsuariosRepository repository;
	
	public ResponseEntity<UsuariosModel> cadastrarUsuario(UsuariosModel usuario){
		Optional<UsuariosModel> optional = repository.findByUsuario(usuario.getUsuario());
		
		if(optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	public Optional<Login> logar(Optional<Login> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UsuariosModel> usuario = repository.findByUsuario(user.get().getUsuario());		
	
		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
			
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte [] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic" + new String(encodeAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				return user;
			}
		}
		
		return null;
	}
}
