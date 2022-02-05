package com.generation.blogpessoal.BlogPessoal.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.BlogPessoal.model.UsuariosModel;
import com.generation.blogpessoal.BlogPessoal.repository.UsuariosRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuariosRepositoryTest {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@BeforeAll
	void start() {
		usuariosRepository.save(new UsuariosModel (0L, "Alex", "alex@email.com.br", "123456", ""));
		usuariosRepository.save(new UsuariosModel (0L, "Cris", "cris@email.com.br", "123456", ""));
		usuariosRepository.save(new UsuariosModel (0L, "Alison", "alison@email.com.br", "123456", ""));
		usuariosRepository.save(new UsuariosModel (0L, "Taylor", "taylor@email.com.br", "123456", ""));
	}

	@Test
	@DisplayName("Retorna 1 usuário")
	public void deveRetornarUmUsuario() {
		
		Optional<UsuariosModel> usuario = usuariosRepository.findByUsuario("alex@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("alex@email.com.br"));
	}

}
