package com.rafaelfelix.ciandt.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelfelix.ciandt.challenge.domains.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{

}
