package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Products;
import com.app.response.ProductResponse;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
	
	@Query("SELECT new com.app.response.ProductResponse(c.name , c.shortDesc, c.description, c.category, p.cost, p.deliveryCharge, p.discount) "
			+ "FROM Products c JOIN c.pricesDetails p where c.name like %?1% ")
    public List<ProductResponse> getProductDetails(String productName);
	
	List<Products> findByName(String productName);
	
	@Query("select p.name from Products p where p.id=?1")
	String getProductName(int id);

}
