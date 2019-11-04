package com.rafaelfelix.ciandt.challenge.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafaelfelix.ciandt.challenge.domains.DeliveryData;
import com.rafaelfelix.ciandt.challenge.domains.Package;
import com.rafaelfelix.ciandt.challenge.dto.PackageDTO;
import com.rafaelfelix.ciandt.challenge.repositories.PackageRepository;

@Component
public class PackageService {

	@Autowired
	private PackageRepository repo;
	
	public List<Package> listAll(){
		return repo.findAll();
	}
	
	public void save(List<PackageDTO> dto, DeliveryData deliveryData){
		List<Package> packages = new ArrayList<>();
		dto.forEach(data -> {
			Package packageData = new Package(data.getId(), data.getWeight(), deliveryData);
			packages.add(packageData);
		});
		
		repo.saveAll(packages);
	}
}
