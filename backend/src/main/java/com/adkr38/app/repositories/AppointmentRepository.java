package com.adkr38.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.adkr38.app.models.*;
import org.springframework.stereotype.Repository;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

}
