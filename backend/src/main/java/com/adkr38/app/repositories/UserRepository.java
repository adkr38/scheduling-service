package com.adkr38.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.adkr38.app.models.*;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

  Optional<User> findByUsername(String username);
  User findOneByUsername(String username);

}
