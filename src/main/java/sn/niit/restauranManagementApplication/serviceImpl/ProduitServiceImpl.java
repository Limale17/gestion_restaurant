package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.domain.Produit;
import sn.niit.restauranManagementApplication.domain.User;
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
	public Produit saveOrUpdateProduit(Produit produit) 
	{
		Categorie categorie = categorieService.getCategorieById(produit.getCategorie().getId()).get();
		
		if(categorie==null) 
		{
			categorie= new Categorie();
		}
		categorie.setNom(produit.getCategorie().getNom());	
		produit.setCategorie(categorie);
		produitRepository.save(produit);
		
		return produit;
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
	public List<Produit> getAllProduit(String keyword) {
		if (keyword != null) {

		   return produitRepository.findByKeyword(keyword);
		}
		
		return produitRepository.findAll();
		
	}


	@Override
	public void deleteProduit(Long id)
	{
		 produitRepository.deleteById(id);
		
	}
	@Override
	public Page<Produit> findPaginated(String keyword, int pageNumber, int pageSize)
	{

		PageRequest pageable = PageRequest.of(pageNumber-1, pageSize);
		
		if(keyword != null) {
			Page<Produit> produits = new PageImpl<>(getAllProduit(keyword));
			return produits;
		}
		return produitRepository.findAll(pageable);
	}
	
	@Override
	public List<Produit> listeProduitParCategorie(Categorie categorie) {
		
		return produitRepository.getProduitByCategorie(categorie);
	}


	
}
