package com.marcelo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.marcelo.api.model.Produto;
import com.marcelo.api.model.ProdutoId;

import java.util.Date;
import java.util.List;


@Repository
public interface ConsultaRepository extends JpaRepository<Produto, ProdutoId> {

    @Query("SELECT distinct p FROM Produto p " +
            "WHERE (:cnpj IS NULL OR p.cnpj = :cnpj ) " +
            "AND (:cdTabelaPreco IS NULL OR p.cdTabelaPreco = :cdTabelaPreco ) " +
            "AND (:cdPrestador IS NULL OR p.cdPrestador = :cdPrestador ) " +
            "AND (:cdTuss IS NULL OR p.cdTiss = :cdTuss ) " +
            "AND (:dtVigenciaIni IS NULL OR p.dtVigenciaIni = :dtVigenciaIni ) " +
            "AND (:dtVigenciaFim IS NULL OR p.dtVigenciaFim = :dtVigenciaFim ) " +
            "AND (:status IS NULL OR p.status ILike %:status% ) ")
    List<Produto> consultaComFiltros(String cnpj,
                                     Long cdTabelaPreco,
                                     Long cdPrestador,
                                     Long cdTuss,
                                     Date dtVigenciaIni,
                                     Date dtVigenciaFim,
                                     String status);

}
