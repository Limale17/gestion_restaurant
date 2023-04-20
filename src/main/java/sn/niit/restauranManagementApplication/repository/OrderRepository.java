package sn.niit.restauranManagementApplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.niit.restauranManagementApplication.domain.OrderItems;

@Repository
public interface OrderRepository extends JpaRepository<OrderItems, Long>
{

}
