package com.adkr38.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adkr38.app.models.*;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

  // @Query("""
  //   SELECT * 
  //   FROM appointment a 
  //   JOIN users u  ON a.user_id = u.id
  //   WHERE u.username= :username 
  // """)
  // List<Appointment> findByUsername(@Param("username") String username);

  List<Appointment> findByUserUsername(String username);

  // @Query("""
  //   SELECT  * 
  //   FROM Appointment a 
  //   JOIN users u  
  //   WHERE u.username= :username 
  // """)
  // List<Appointment> findByUsername(@Param("username") String username);

}

