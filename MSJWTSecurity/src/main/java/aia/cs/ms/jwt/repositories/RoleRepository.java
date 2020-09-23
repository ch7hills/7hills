package aia.cs.ms.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aia.cs.ms.jwt.entities.Role;
@Repository
public interface RoleRepository  extends JpaRepository<Role,Long>{

	Role findByName(String string);

}
