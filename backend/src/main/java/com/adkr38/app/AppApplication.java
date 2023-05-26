package com.adkr38.app;
import com.adkr38.app.models.Role;
import com.adkr38.app.models.User;
import com.adkr38.app.models.Activity;
import com.adkr38.app.models.Appointment;
import com.adkr38.app.services.impl.UserService;
import com.adkr38.app.services.impl.ActivityService;
import com.adkr38.app.services.impl.AppointmentService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.time.LocalDateTime;

@SpringBootApplication
public class AppApplication{

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService,ActivityService activityService, AppointmentService AppointmentService ) {
        return args -> {

            Activity mullet = new Activity("Mullet", 30*60, "https://www.amazon.es/photos/share/fMkirw5Jsk2fUqxLResFa05COg6mOyoGWaXGeaf1E5A");
            User johnDoe = new User("John Doe", "john", "1234", new ArrayList<>(), new ArrayList<>());
            LocalDateTime fivePmToday = LocalDateTime.now().withHour(17).withMinute(0);
            Appointment appointment = new Appointment(fivePmToday, mullet, johnDoe);

            activityService.saveActivity(mullet);
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));

            userService.saveUser(johnDoe);
            userService.saveUser(new User("James Smith", "james", "1234", new ArrayList<>(),new ArrayList<>()));
            userService.saveUser(new User("Jane Carry", "jane", "1234", new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User("Chris Anderson", "chris", "1234", new ArrayList<>(),new ArrayList<>()));
    
            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("james", "ROLE_ADMIN");
            userService.addRoleToUser("jane", "ROLE_USER");
            userService.addRoleToUser("chris", "ROLE_ADMIN");
            userService.addRoleToUser("chris", "ROLE_USER");
            AppointmentService.saveAppointment(appointment);
        };
    }

}
