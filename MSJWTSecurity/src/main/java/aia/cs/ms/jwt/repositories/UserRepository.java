package aia.cs.ms.jwt.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aia.cs.ms.jwt.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	/* Optional<User> findByUserName(String username); */

	User findByEmail(String email);

}
