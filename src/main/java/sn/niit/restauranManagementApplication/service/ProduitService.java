package sn.niit.restauranManagementApplication.service;

import java.util.List;
import java.util.Optional; 

import sn.niit.restauranManagementApplication.domain.Produit;

public interface ProduitService 
{
	void saveOrUpdateProduit(Produit produit);
	Produit getProduitById(Long id);
	List<Produit>getAllProduit();
	void deleteProduit(Long id);

}
