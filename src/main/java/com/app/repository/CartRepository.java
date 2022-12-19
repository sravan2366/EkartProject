package com.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	
	@Query("select count(c) from Cart c where c.userName=?1 and c.pid=?2")
	int countQuantityDetails(String userName, int pid);
	
	@Transactional
	@Modifying
	@Query("update Cart c set c.quantity=?1, c.offerPrice=?2 where c.userName=?3 and c.pid=?4")
	void updateQuantityAndPrice(int quantity, double offerPrice, String userName, int pid);
	
	
	List<Cart> findByUserName(String userName);
	
	@Query("select count(c) from Cart c where c.userName=?1")
	int totalCartItems(String userName);

}
