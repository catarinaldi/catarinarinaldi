package com.generation.blogpessoal.BlogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.BlogPessoal.model.TemasModel;

@Repository

public interface TemasRepository extends JpaRepository<TemasModel, Long> {
		
		public List<TemasModel> findAllByDescricaoContainingIgnoreCase (String Nome);

}