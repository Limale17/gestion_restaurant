package sn.niit.restauranManagementApplication;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import sn.niit.restauranManagementApplication.domain.Produit;
import sn.niit.restauranManagementApplication.service.ProduitService;
import sn.niit.restauranManagementApplication.serviceImpl.ProduitServiceImpl;

@SpringBootTest
class RestaurantManagementApplicationTests {

	@Test
	void contextLoads() {
		ProduitService produitService = new ProduitServiceImpl();
		List<Produit> listProduit = produitService.getAllProduit("burger");
		
		for(Produit p : listProduit) {
			System.out.println(p);
		}
	}

}
