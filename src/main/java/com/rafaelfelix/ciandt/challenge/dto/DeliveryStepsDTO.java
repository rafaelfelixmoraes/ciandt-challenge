package com.rafaelfelix.ciandt.challenge.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DeliveryStepsDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer step;
	private Integer packageId;
	
	@JsonProperty("from")
	private String originStep;
	
	@JsonProperty("to")
	private String destinyStep;

}
