package com.marcelo.api.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marcelo.api.model.Produto;
import com.marcelo.api.model.ProdutoId;



@Repository
public interface ProdutoRepository extends JpaRepository<Produto, ProdutoId>{
	
	List<Produto> findByCdEmpresa(Long cdEmpresa);


	List<Produto> findDistinctByCnpjOrderByCdTabelaPreco(String cnpj);


	List<Produto> findDistinctByCnpjOrderByCdTabelaPrecoDesc(String cnpj);

	@Query(value = "select distinct p.CD_TABELA_PRECO, " +
            "p.CD_PRESTADOR, " +
            "p.TABELA_TISS, " +
            "p.CODIGO_TUSS, " +
            "p.CD_EMPRESA, " +
            "p.CD_CGC_CPF, " +
            "p.DESCRICAO_ITEM, " +
            "p.DT_VIGENCIA_CALC, " +
			"p.DT_VIGENCIA_FINAL, " +
            "p.UNIDADE_MEDIDA, " +
            "p.VL_PROCEDIMENTO, " +
	        "p.STATUS " +
            "FROM V_API_SGUTP p where p.CD_CGC_CPF = ?1 " +
            "group by p.CD_TABELA_PRECO, " +
            "p.CD_PRESTADOR, " +
            "p.TABELA_TISS, " +
            "p.CODIGO_TUSS, " +
            "p.CD_EMPRESA, " +
            "p.CD_CGC_CPF, " +
            "p.DESCRICAO_ITEM, " +
            "p.DT_VIGENCIA_CALC, " +
			"p.DT_VIGENCIA_FINAL, " +
            "p.UNIDADE_MEDIDA, " +
            "p.VL_PROCEDIMENTO, " +
			"p.STATUS " +
            "order by p.CD_TABELA_PRECO, p.CD_PRESTADOR",  nativeQuery = true)
	List<Produto> pesquisaCnpj(String cnpj);

}
