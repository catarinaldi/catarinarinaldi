package com.generation.blogpessoal.BlogPessoal.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.BlogPessoal.model.UsuariosModel;
import com.generation.blogpessoal.BlogPessoal.repository.UsuariosRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private UsuariosRepository usuariosRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		
		Optional<UsuariosModel> user = usuariosRepository.findByUsuario(usuario);
		user.orElseThrow(() -> new UsernameNotFoundException(usuario + " not found."));
		
		return user.map(UserDetailsImpl::new).get();
	}

}
