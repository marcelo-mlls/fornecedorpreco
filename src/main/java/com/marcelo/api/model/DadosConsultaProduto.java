package com.marcelo.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosConsultaProduto {

    private Long cdTabelaPreco;
    private Long cdPrestador;
    private String cdTiss;
    private Long cdTuss;
    private Long cdEmpresa;
    private String cnpj;
    @JsonFormat(pattern="dd/mm/yyyy")
    private String dtVigenciaIni;
    @JsonFormat(pattern="dd/mm/yyyy")
    private String dtVigenciaFim;
    private Double vlProcedimento;
    private String status;

    public DadosConsultaProduto(Produto produto) {
        this.cdTabelaPreco = Long.valueOf(produto.getCdTabelaPreco());
        this.cdPrestador = Long.valueOf(produto.getCdPrestador());
        this.cdTiss = produto.getCdTiss();
        this.cdTuss = Long.valueOf(produto.getCdTuss());
        this.cdEmpresa = Long.valueOf(produto.getCdEmpresa());
        this.cnpj = produto.getCnpj();
        this.dtVigenciaIni = String.valueOf(converterData(produto.getDtVigenciaIni()));
        this.dtVigenciaFim = String.valueOf(converterData(produto.getDtVigenciaFim()));
        this.vlProcedimento = Double.valueOf(produto.getVlProcedimento());
        this.status = produto.getStatus();
    }

    public static Date converterData(String data) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dataVig = null;

        try {
            dataVig = sdf.parse(data);
        } catch (ParseException e) {
            System.out.println("A data informada deve estar no padrão dd/mm/yyyy");
            throw new RuntimeException(e);
        }

        return dataVig;
    }

}
