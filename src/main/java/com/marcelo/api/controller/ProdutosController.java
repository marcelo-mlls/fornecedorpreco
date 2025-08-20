package com.marcelo.api.controller;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.api.model.Produto;
import com.marcelo.api.repository.ProdutoRepository;



@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(path = "/produtos")
public class ProdutosController {

	@Autowired
	ProdutoRepository repository;

	@GetMapping(path = "/{cnpj}")
	public List<Produto> listar(@PathVariable String cnpj) {
		System.out.println("cnpj:  " + cnpj);
		List<Produto> produtosLista = repository.pesquisaCnpj(cnpj);
/*		Iterable<Produto> produtosAll = repository.pesquisaCnpj(cnpj);
		for (Produto prods : produtosAll) {
			produtosLista.add(prods);
		}*/
		System.out.println(produtosLista);
		System.out.println(produtosLista.size() + " rows");
		return produtosLista;

		//return repository.pesquisaCnpj(cnpj);

	}
}
