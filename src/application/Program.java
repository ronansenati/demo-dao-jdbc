package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		//Department obj1 = new Department(1, "Book");
	//	Seller seller1 = new Seller(1,"Bob","bob@gmail.com", new Date(),3000.0, obj1);
	//	System.out.println(seller1);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("=== TEST 1:  seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2:  seller findByDepartment ===");
		Department department = new Department(2,null);
		List<Seller> list = sellerDao.findByDepartment(department);		
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3:  seller findAll ===");			
		list = sellerDao.findAll();		
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 4:  seller Insert ===");				
		
		Seller newSeller = new Seller(null,"Rocker Feller","rocker@gmail.com",new Date(),3000.0,department);	
		sellerDao.insert(newSeller);
		System.out.println("Insert new ID"+ newSeller.getId());			
		
		System.out.println("\n=== TEST 4:  seller Update ===");		
		seller = sellerDao.findById(32);
		seller.setName("Umglia");
		sellerDao.update(seller);
		System.out.println("Update:"+ sellerDao.findById(32));	
	
	}

}
