package sn.niit.restauranManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.niit.restauranManagementApplication.domain.Role;
import sn.niit.restauranManagementApplication.domain.User;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long>
{
    public Role findByName(String name);
}
