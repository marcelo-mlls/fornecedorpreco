package com.marcelo.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.marcelo.api.model.DadosConsultaProduto;
import com.marcelo.api.model.Produto;
import com.marcelo.api.repository.ConsultaRepository;
import com.marcelo.api.service.ConversorService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private ConversorService conversor;

    @PostMapping
    public ResponseEntity<List<Produto>> listar(@RequestBody @Valid DadosConsultaProduto dados)  {

        Date dataVigIni = null;
        Date dataVigFim = null;

        if(dados.getDtVigenciaIni() != null && !dados.getDtVigenciaIni().isEmpty()) {
            try {
                DateFormat formatBr = new SimpleDateFormat("dd/MM/yyyy");
                dataVigIni = formatBr.parse(dados.getDtVigenciaIni());
            }catch (ParseException e){
                System.out.println("Data inicio da vigência não é inválido");
            }
        }

        if(dados.getDtVigenciaFim() != null && !dados.getDtVigenciaFim().isEmpty()) {
            try {
                DateFormat formatBr = new SimpleDateFormat("dd/MM/yyyy");
                dataVigFim = formatBr.parse(dados.getDtVigenciaFim());
            }catch (ParseException e){
                System.out.println("Data fim da vigência não é inválido");
            }
        }

        List<Produto> lista = new ArrayList<>();

        lista = repository.consultaComFiltros(dados.getCnpj(),
                dados.getCdTabelaPreco(),
                dados.getCdPrestador(),
                dados.getCdTuss(),
                dataVigIni,
                dataVigFim,
                dados.getStatus());

        return ResponseEntity.ok(lista);

    }
}
