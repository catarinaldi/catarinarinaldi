package com.generation.blogpessoal.BlogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarios")

public class UsuariosModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	private String usuario;
	
	@NotBlank
	@Size (min = 5, max = 300)
	private String senha;
	
	private String foto;
	
	@OneToMany(mappedBy = "usuariosModel", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuariosModel")
	private List<PostagensModel> postagensModel;

	
	public UsuariosModel(long id, @NotBlank String nome, @NotBlank @Email String usuario,
	@NotBlank @Size(min = 5, max = 300) String senha, String foto) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.foto = foto;
	}
	
	public UsuariosModel() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


}
