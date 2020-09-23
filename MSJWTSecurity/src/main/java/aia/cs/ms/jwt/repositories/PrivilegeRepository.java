package aia.cs.ms.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aia.cs.ms.jwt.entities.Privilege;

@Repository
public interface PrivilegeRepository  extends JpaRepository<Privilege,Long>{

	Privilege findByName(String name);

}
