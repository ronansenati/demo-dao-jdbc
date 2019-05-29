package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class ProgramTestDepartment {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		//System.out.println("===Test 1 Insert Department ===");
		//Department department = new Department(5,"DVD");	
		//departmentDao.Insert(department);			
		//System.out.println("Department insert:"+ department.getId());
		
		System.out.println("===Test 2 Update Department ===");
		Department department2 = new Department();	
		department2.setId(7);
		department2.setName("NEWSPAPER");
		departmentDao.update(department2);
		System.out.println("Update complete! " );
		
		System.out.println("===Test 3 deletebyIDUpdate Department ===");
		
		System.out.println("Informe o ID a ser deletado:");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("ID de departamento deletado com sucesso!");		
		sc.close();
		
		System.out.println("===Test 4 findByID Department ===");
		System.out.println(departmentDao.findById(8));
		
		System.out.println("===Test 5 findAll Department ===");
		
		List<Department> list = new ArrayList<>();
		
		list = departmentDao.findAll();		
		for (Department obj : list) {
			System.out.println(obj);
		}
		
	
			
	}

}
