package com.rafaelfelix.ciandt.challenge.enums;

public enum DeliveryStepsEnum {

	ZONA_ABASTECIMENTO(1, "Zona de Abastecimento"),
	ZONA_CAMINHAO(2, "Zona do Caminhão"),
	ZONA_TRANSFERENCIA(3, "Zona de Transferência");
	
	private int cod;
	private String descricao;
	
	private DeliveryStepsEnum(int cod, String descricao) { 
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static DeliveryStepsEnum toEnum(Integer id) {
		if (id == null) { 
			return null;
		}
		for (DeliveryStepsEnum x : DeliveryStepsEnum.values()) { 
			if (id.equals(x.getCod())) {
				return x; 
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
}
