package com.bruno.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class Tecnico extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonIgnore  // fazendo ignorar a lista quando der um get
	@OneToMany(mappedBy = "tecnico") // informando que é 1 tecnico para varias ordens e que foi mapeado na outra classe
	private List<Os> list = new ArrayList<>();  // Criando uma lista de oS

	public Tecnico() {
		super();

	}

	public Tecnico(int id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);

	}

	public List<Os> getList() {
		return list;
	}

	public void setList(List<Os> list) {
		this.list = list;
	}
	
	

}
