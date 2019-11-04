package com.rafaelfelix.ciandt.challenge.dto;

import java.io.Serializable;

import com.rafaelfelix.ciandt.challenge.domains.Package;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Double weight;
	
	public PackageDTO (Package obj) {
		this.id = obj.getId();
		this.weight = obj.getWeight();
	}

	
}
