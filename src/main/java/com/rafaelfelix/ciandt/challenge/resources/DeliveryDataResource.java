package com.rafaelfelix.ciandt.challenge.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaelfelix.ciandt.challenge.domains.DeliveryData;
import com.rafaelfelix.ciandt.challenge.dto.DeliveryDataDTO;
import com.rafaelfelix.ciandt.challenge.dto.DeliveryStepsDTO;
import com.rafaelfelix.ciandt.challenge.exceptions.DeliveryNotFoundException;
import com.rafaelfelix.ciandt.challenge.services.DeliveryDataService;
import com.rafaelfelix.ciandt.challenge.services.PackageService;

import io.swagger.annotations.ApiModel;

@RestController
@RequestMapping(value = "/delivery")
@ApiModel(description = "Endpoints to manupulate data about Delivery")
public class DeliveryDataResource {
	
	@Autowired
	private DeliveryDataService deliveryService;
	
	@Autowired
	private PackageService packageService;
	
	@GetMapping("/all")
	public ResponseEntity<List<DeliveryDataDTO>> findAll(){
		List<DeliveryData> listData = deliveryService.listAll();
		if(listData == null || listData.isEmpty()) {
			throw new DeliveryNotFoundException("No deliveries found");
		}
		List<DeliveryDataDTO> deliveryData = listData.stream().map(data -> new DeliveryDataDTO(data)).collect(Collectors.toList());
		return ResponseEntity.ok(deliveryData);
	}
	
	/**
	 * Method to insert a new {@link DeliveryData}
	 * @param dto A DTO to insert
	 * @return 201 - Created ({@link HttpStatus})
	 */
	@PostMapping()
	public ResponseEntity<?> createDelivery(@RequestBody DeliveryDataDTO dto){
		DeliveryData deliveryData = deliveryService.save(dto);
		packageService.save(dto.getPackages(), deliveryData);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(deliveryData.getId());
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Return the delivery steps based on Delivery ID
	 * @param deliveryId A Delivery ID to find the steps
	 * @return All the Delivery steps, ordering by Step, or 401 - Not Found ({@link HttpStatus}) case no delivery found
	 */
	@GetMapping("/{deliveryId}/step")
	public ResponseEntity<?> getDeliverySteps(@PathVariable String deliveryId){
		Optional<DeliveryData> deliveryData = deliveryService.findByDeliveryId(deliveryId);
		if(!deliveryData.isPresent()) {
			throw new DeliveryNotFoundException("No delivery found");
		}
		
		List<DeliveryStepsDTO> deliverySteps = deliveryService.getDeliverySteps(deliveryData.get());
		
		return ResponseEntity.ok(deliverySteps);
	}

}
