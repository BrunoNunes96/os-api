package com.bruno.os.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;



@Entity
public class Cliente extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "cliente") // mapeando cliente e informando relacionamento 1 p/ muitos
	private List<Os> list = new ArrayList<>();

	public Cliente() {
		super();
		
	}
	public Cliente(int id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
		
	}
	public List<Os> getList() {
		return list;
	}
	public void setList(List<Os> list) {
		this.list = list;
	}
	
	

}
