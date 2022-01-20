package com.bruno.os.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Os {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")       // passando parametro por json e padrão da data
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")	// passando parametro por json e padrão da data
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	private String observacoes;
	private Integer status;

	@ManyToOne  // notação de muitos para um
	@JoinColumn(name = "tecnico_id")  // notação para criar uma coluna na tabela os com chave estrangeira
	private Tecnico tecnico;
	
	@ManyToOne  // notação de muitos para um
	@JoinColumn(name = "cliente_id")  // notação para criar uma coluna na tabela os com chave estrangeira
	private Cliente cliente;
	
	public Os() {
		super();
		// regras de negocios para aberturas
		this.setDataAbertura(LocalDateTime.now());// pegando data atual de abertura
		this.setPrioridade(Prioridade.BAIXA);
		this.setStatus(Status.ABERTO);
	}

	public Os(Integer id, Prioridade prioridade,String observacoes, Status status, Tecnico tecnico, Cliente cliente) {
		super();
		this.id = id;
		this.setDataAbertura(LocalDateTime.now());  // pegando data atual de abertura
		this.prioridade = (prioridade == null)? 0 : prioridade.getCod();  // fazendo if else diferente para verificar se o codigo vem null ou nao
		this.observacoes = observacoes;
		this.status = (status == null)? 0 : status.getCod();  // se for nulo passa valor sero, se nao pega o codigo
		this.tecnico = tecnico;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);  // pegando o valor inteiro da prioridade e passando para o metodo toEnum
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade.getCod();
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Os other = (Os) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
