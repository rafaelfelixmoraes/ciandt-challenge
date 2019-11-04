package com.rafaelfelix.ciandt.challenge.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelfelix.ciandt.challenge.domains.DeliveryData;

@Repository
public interface DeliveryDataRepository extends JpaRepository<DeliveryData, Integer>{
 
	@Transactional(readOnly = true)
	Optional<DeliveryData> findByDeliveryId(String deliveryId);
}
