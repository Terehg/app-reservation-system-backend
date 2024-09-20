package com.reservation.system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservation.system.entity.CustomerEntity;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

	List<CustomerEntity> findAll();
	
	Optional<CustomerEntity> findById(Long id);
	
	
}
