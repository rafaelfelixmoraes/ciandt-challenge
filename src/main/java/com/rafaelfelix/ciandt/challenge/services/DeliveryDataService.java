package com.rafaelfelix.ciandt.challenge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafaelfelix.ciandt.challenge.domains.DeliveryData;
import com.rafaelfelix.ciandt.challenge.domains.Package;
import com.rafaelfelix.ciandt.challenge.dto.DeliveryDataDTO;
import com.rafaelfelix.ciandt.challenge.dto.DeliveryStepsDTO;
import com.rafaelfelix.ciandt.challenge.enums.DeliveryStepsEnum;
import com.rafaelfelix.ciandt.challenge.repositories.DeliveryDataRepository;

@Component
public class DeliveryDataService {

	@Autowired
	private DeliveryDataRepository repo;
	
	private static Integer stepsCount;
	private static List<DeliveryStepsDTO> deliverySteps;
	
	public List<DeliveryData> listAll(){
		return repo.findAll();
	}
	
	public Optional<DeliveryData> findByDeliveryId(String deliveryId){
		return repo.findByDeliveryId(deliveryId);
	}
	
	public DeliveryData save(DeliveryDataDTO dto){
		if(dto.getId() == null) {
			List<DeliveryData> listDeliveryData = listAll();
			dto.setId(listDeliveryData.size() + 1);
		}
		
		DeliveryData deliveryData = new DeliveryData(dto.getId(), dto.getVehicle(), dto.getDeliveryId());
		
		return repo.saveAndFlush(deliveryData);
	}
	
	public List<DeliveryStepsDTO> getDeliverySteps(DeliveryData deliveryData) {
		deliverySteps = new ArrayList<>();
		stepsCount = 1;
		
		List<Package> packages = deliveryData.getPackages();
		
		getSteps(packages.size(), DeliveryStepsEnum.ZONA_ABASTECIMENTO.getDescricao(), 
				                  DeliveryStepsEnum.ZONA_TRANSFERENCIA.getDescricao(), 
								  DeliveryStepsEnum.ZONA_CAMINHAO.getDescricao(), packages);
		
		return deliverySteps;
	}
	
	/**
	 * Move all packages according bussiness rules, based on Hanoi tower algorithm.
	 * 
	 * @param listLength Lenght of packages list
	 * @param startPoint Start point of package movement
	 * @param intermediatePoint Intermediate point of package movement
	 * @param finalPoint Final point of package movement
	 * @param packages The packages to move for all points
	 */
	private static void getSteps(Integer listLength, String startPoint, String intermediatePoint, String finalPoint, List<Package> packages)
    {
        if (listLength == 0)
        {
            return;
        }

        getSteps(listLength - 1, startPoint, finalPoint, intermediatePoint, packages);
        deliverySteps.add(new DeliveryStepsDTO(stepsCount++, packages.get(listLength - 1).getId(), startPoint, finalPoint));
        getSteps(listLength - 1, intermediatePoint, startPoint, finalPoint, packages);
    }
}
