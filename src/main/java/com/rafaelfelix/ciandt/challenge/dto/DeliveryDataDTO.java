package com.rafaelfelix.ciandt.challenge.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rafaelfelix.ciandt.challenge.domains.DeliveryData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDataDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String vehicle;
	private String deliveryId;
	
	private List<PackageDTO> packages = new ArrayList<>();
	
	public DeliveryDataDTO(DeliveryData obj) {
		this.id = obj.getId();
		this.vehicle = obj.getVehicleId();
		this.deliveryId = obj.getDeliveryId();
		this.packages = obj.getPackages().stream().map(data -> new PackageDTO(data)).collect(Collectors.toList());
	}
}
