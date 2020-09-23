package aia.cs.ms.jwt.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	@JsonBackReference
	@ManyToMany(mappedBy = "privileges")
	private Collection<Role> roles;
	
	public Privilege() {
		
	}

	public Privilege(String name) {
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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	/*
	 * @Override public String toString() { return "Privilege [id=" + id + ", name="
	 * + name + ", roles=" + roles + "]"; }
	 */
	
}
