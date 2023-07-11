package sn.niit.restauranManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import sn.niit.restauranManagementApplication.domain.Categorie;

public interface CategorieService  
{
	void saveOrUpdateCategorie(Categorie categorie);
	Optional<Categorie> getCategorieById(Long id);
	List<Categorie> getAllCategorie();
	void deleteCategorie(Long Id);
	Page<Categorie> findPaginated(int pageNumber, int pageSize);
}
