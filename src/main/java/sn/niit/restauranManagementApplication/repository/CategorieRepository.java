package sn.niit.restauranManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.niit.restauranManagementApplication.domain.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> 
{

}
