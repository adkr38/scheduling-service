package com.adkr38.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.adkr38.app.models.*;

@Repository
public interface ActivityRepository extends JpaRepository<Activity ,Long>{
  Optional<Activity> findByActivity(String activity);


}
