package sn.niit.restauranManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.niit.restauranManagementApplication.domain.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
