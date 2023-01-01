package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.domain.Produit;
import sn.niit.restauranManagementApplication.repository.ProduitRepository;
import sn.niit.restauranManagementApplication.service.CategorieService;
import sn.niit.restauranManagementApplication.service.ProduitService;

@Service
public class ProduitServiceImpl implements ProduitService
{
	@Autowired
	ProduitRepository produitRepository;
	
	@Autowired
	CategorieService categorieService;

	@Override
	public void saveOrUpdateProduit(Produit produit) 
	{
		Categorie categorie = categorieService.getCategorieById(produit.getCategorie().getId());
		
		if(categorie==null) 
		{
			categorie= new Categorie();
		}
		categorie.setNom(produit.getCategorie().getNom());	
		produit.setCategorie(categorie);
		produitRepository.save(produit);
		
	}
	@Override
	public Produit getProduitById(Long id) 
	{
		Produit produit=null;
		Optional<Produit> optionalProduit =produitRepository.findById(id);
		if(!optionalProduit.isEmpty())
		{
			produit= optionalProduit.get();
		}
		else 
		{
			throw new RuntimeException("Aucun produit trouve");
		}
		return produit;
	}

	
	
	@Override
	public List<Produit> getAllProduit() 
	{
		return produitRepository.findAll();
	}
	@Override
	public void deleteProduit(Long id) 
	{
		 produitRepository.deleteById(id);
		
	}

	



	
}
