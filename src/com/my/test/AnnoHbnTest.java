package com.my.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.my.pojo.Product;
import com.my.pojo.User;

public class AnnoHbnTest {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
/*		Category c = (Category) s.get(Category.class, 1);
		s.getTransaction().commit();
		
		Set<Product> products = c.getProducts();
		for (Product product : products) {
			System.out.println(product.getName());
		}*/
		
		//增加3个用户
		Set<User> users = new HashSet<>();
		for (int i = 1; i <= 3; i++) {
			User u = new User();
			u.setName("user" + i);
			users.add(u);
			s.save(u);
		}
		
		//产品1被用户1,2,3购买
		Product p1 = (Product) s.get(Product.class, 1);
		p1.setUsers(users);
		
		s.getTransaction().commit();
		
		s.close();
		sf.close();
	}

}
