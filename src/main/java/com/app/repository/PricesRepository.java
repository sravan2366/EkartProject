package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Prices;

public interface PricesRepository extends JpaRepository<Prices, Integer> {

	@Query(value = "select p.cost from Prices p where p.pid_fk=?1", nativeQuery = true)
	double findByPid_Fk(int pid);

	@Query(value = "select p.id, p.cost, p.discount, p.deliveryCharge from Prices p where p.pid_fk=?1", nativeQuery = true)
	Prices getPriceDetails(int pid);

}
