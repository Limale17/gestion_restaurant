package sn.niit.restauranManagementApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.domain.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>
{
    @Query(value = "select * from Produit p where p.name like %:keyword% or p.price like %:keyword%",
            nativeQuery = true)
    List<Produit> findByKeyword(String keyword);
    List<Produit> getProduitByCategorie(Categorie categorie);

}
