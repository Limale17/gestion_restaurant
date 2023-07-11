package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.repository.CategorieRepository;
import sn.niit.restauranManagementApplication.service.CategorieService;

@Service
public class CategorieServiceImpl implements CategorieService

{
	@Autowired
	CategorieRepository categorieRepository;

	@Override
	public void saveOrUpdateCategorie(Categorie categorie) 
	{
		categorieRepository.save(categorie);
		
	}

	@Override
	public List<Categorie> getAllCategorie() 
	{
		return categorieRepository.findAll();
	}


	@Override
	public Optional<Categorie> getCategorieById(Long id)
	{
//		 Categorie  categorie = null;
//		Optional<Categorie> optionalCategorie=  categorieRepository.findById(id);
//		if(!optionalCategorie.isEmpty()) 
//		{
//			categorie=  optionalCategorie.get();
//			
//		}
//		else 
//		{
//			throw new RuntimeException("Aucune categorie id = "+id+ "n'a ete trouve");
//		}
//		
//		 return categorie;
		return categorieRepository.findById(id);
	}

	@Override
	public void deleteCategorie(Long Id) 
	{
		categorieRepository.deleteById(Id);
		
	}

	@Override
	public Page<Categorie> findPaginated(int pageNumber, int pageSize) 
	{
		PageRequest pageable = PageRequest.of(pageNumber-1, pageSize);
		return categorieRepository.findAll(pageable);
	}

}
