package com.generation.blogpessoal.BlogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.BlogPessoal.model.PostagensModel;

@Repository

public interface PostagensRepository extends JpaRepository<PostagensModel, Long> {
	
	public List<PostagensModel> findAllByTituloContainingIgnoreCase (String Nome);
}