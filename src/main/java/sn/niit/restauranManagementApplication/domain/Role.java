package sn.niit.restauranManagementApplication.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role
{
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    public Role(){}
    public Role(String name)
    {
        this.name = name;
    }
    public Long getId()
    {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
