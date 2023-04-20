package com.example.clothes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;

import com.example.clothes.model.DistributionCentre;

public interface DistributionCentreRepository extends CrudRepository<DistributionCentre, Long> {
}
