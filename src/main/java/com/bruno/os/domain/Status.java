package com.bruno.os.domain;

public enum Status {

	ABERTO(0, "ABERTO"),
	ANDAMENTO(1, "ANDAMENTO"),
	ENCERRADO(2, "ENCERRADO");
	
	private int cod;
	private String descricao;
	
	
	private Status(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}


	public int getCod() {
		return cod;
	}


	public String getDescricao() {
		return descricao;
	}

	
	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		// verificando se o codigo solicitado esta retornando corretamente
		for(Status x: Status.values()) {
			
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status invalido" + cod);
	}
}


