package com.marcelo.api.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@IdClass(ProdutoId.class)
@Table(name = "V_API_SGUTP")
public class Produto {

	@Column(name = "CD_TABELA_PRECO")
	private Long cdTabelaPreco;

	@Column(name = "CD_PRESTADOR")
	private Long cdPrestador;

	@Column(name = "TABELA_TISS")
	private String cdTiss;

	@Id
	@Column(name = "CODIGO_TUSS")
	private Long cdTuss;

	@Column(name="CD_EMPRESA")
	private Long cdEmpresa;

	@Column(name = "CD_CGC_CPF")
	private String cnpj;

	@Column(name="DESCRICAO_ITEM")
	private String descItem;

	@Column(name="DT_VIGENCIA_CALC")
	private Date dtVigenciaIni;

	@Column(name="DT_VIGENCIA_FINAL")
	private Date dtVigenciaFim;

	@Column(name="UNIDADE_MEDIDA")
	private String unidade;

	@Id
	@Column(name="VL_PROCEDIMENTO")
	private Double vlProcedimento;

	@Column(name="STATUS")
	private String status;

	public Produto(){}

	public Produto(DadosConsultaProduto dados){
		this.cdTabelaPreco = dados.getCdTabelaPreco();
		this.cdPrestador = dados.getCdPrestador();
		this.cdTiss = dados.getCdTiss();
		this.cdTuss = dados.getCdTuss();
		this.cdEmpresa = dados.getCdEmpresa();
		this.cnpj = dados.getCnpj();
		this.dtVigenciaIni = DadosConsultaProduto.converterData(dados.getDtVigenciaIni());
		this.dtVigenciaFim = DadosConsultaProduto.converterData(dados.getDtVigenciaFim());
		this.vlProcedimento = dados.getVlProcedimento();
		this.status = dados.getStatus();

	}

	public String getCdTabelaPreco() {
		return cdTabelaPreco.toString();
	}

	public String getCdPrestador() {
		return cdPrestador.toString();
	}

	public String getDtVigenciaIni() {
		if (dtVigenciaIni != null && !dtVigenciaIni.toString().trim().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			String dataFormatada = sdf.format(dtVigenciaIni);
			return dataFormatada;
		}

		return String.valueOf(dtVigenciaIni);
	}

	public String getDtVigenciaFim() {
		if (dtVigenciaFim != null && !dtVigenciaFim.toString().trim().isEmpty()) {
			SimpleDateFormat sdff= new SimpleDateFormat("dd/MM/yyyy");
			sdff.setTimeZone(TimeZone.getTimeZone("UTC"));
			String dataFormatadaFim = sdff.format(dtVigenciaFim);
			return dataFormatadaFim;
		}

		return String.valueOf(dtVigenciaFim);
	}

	public String getCdEmpresa() {
		return cdEmpresa.toString();
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getCdTuss() {
		return cdTuss.toString();
	}

	public String getVlProcedimento() {
		DecimalFormat  decimal  = new DecimalFormat("#0.0000");
		return decimal.format(vlProcedimento) ;
	}

	public String getDescItem() {
		return descItem.trim();
	}

	public String getUnidade() {
		return unidade;
	}

	public String getCdTiss() {
		return cdTiss;
	}

	public String getStatus(){return  status;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Produto produto)) return false;
		return Objects.equals(getCdTabelaPreco(), produto.getCdTabelaPreco()) && Objects.equals(getCdPrestador(), produto.getCdPrestador()) && Objects.equals(getCdTiss(), produto.getCdTiss()) && Objects.equals(getCdTuss(), produto.getCdTuss()) && Objects.equals(getCdEmpresa(), produto.getCdEmpresa()) && Objects.equals(getCnpj(), produto.getCnpj()) && Objects.equals(getDescItem(), produto.getDescItem()) && Objects.equals(getDtVigenciaIni(), produto.getDtVigenciaIni()) && Objects.equals(getDtVigenciaFim(), produto.getDtVigenciaFim()) && Objects.equals(getUnidade(), produto.getUnidade()) && Objects.equals(getVlProcedimento(), produto.getVlProcedimento()) && Objects.equals(getStatus(), produto.getStatus());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCdTabelaPreco(), getCdPrestador(), getCdTiss(), getCdTuss(), getCdEmpresa(), getCnpj(), getDescItem(), getDtVigenciaIni(), getDtVigenciaFim(), getUnidade(), getVlProcedimento(), getStatus());
	}

	@Override
	public String toString() {
		return "Produto{" +
				"cdTabelaPreco=" + cdTabelaPreco +
				", cdPrestador=" + cdPrestador +
				", cdTiss='" + cdTiss + '\'' +
				", cdTuss=" + cdTuss +
				", cdEmpresa=" + cdEmpresa +
				", cnpj='" + cnpj + '\'' +
				", descItem='" + descItem + '\'' +
				", dtVigenciaIni=" + dtVigenciaIni +
				", dtVigenciaFim=" + dtVigenciaFim +
				", unidade='" + unidade + '\'' +
				", vlProcedimento=" + vlProcedimento +
				", status='" + status + '\'' +
				'}';
	}
}
