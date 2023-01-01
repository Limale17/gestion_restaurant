package sn.niit.restauranManagementApplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.niit.restauranManagementApplication.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{

}
