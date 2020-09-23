package aia.cs.ms.jwt.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Role {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String name;
    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
    @JsonManagedReference
    @ManyToMany
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;
    
    public Role() {
    	
    }

	public Role(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}

	/*
	 * @Override public String toString() { return "Role [id=" + id + ", name=" +
	 * name + ", users=" + users + ", privileges=" + privileges + "]"; }
	 */  
    
    
}
