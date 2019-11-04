package com.rafaelfelix.ciandt.challenge.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ApiModel(description = "All details about a Delivery")
public class DeliveryData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String vehicleId;
	private String deliveryId;
	
	@JsonIgnore
	@OneToMany(mappedBy = "delivery")
	private List<Package> packages = new ArrayList<>();
	
	public DeliveryData() {
		
	}

	public DeliveryData(Integer id, String vehicleId, String deliveryId) {
		this.id = id;
		this.vehicleId = vehicleId;
		this.deliveryId = deliveryId;
	}

	public List<Package> getPackages() {
		return this.packages.stream().sorted(Comparator.comparingDouble(data -> data.getWeight())).collect(Collectors.toList());
	}

}
