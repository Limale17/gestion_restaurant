package sn.niit.restauranManagementApplication.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.domain.Produit;

public interface ProduitService 
{
	Produit saveOrUpdateProduit(Produit produit);
	Produit getProduitById(Long id);
	List<Produit>getAllProduit(String keyword);

	void deleteProduit(Long id);
	Page<Produit> findPaginated(String keyword, int pageNumber, int pageSize);
	List<Produit> listeProduitParCategorie(Categorie categorie);



}
