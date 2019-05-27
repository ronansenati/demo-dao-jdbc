package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {
	
	void Insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();

}
