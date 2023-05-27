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


            Activity mullet = new Activity("Mullet", 25, "./mullet.jpeg");
            Activity burstFade = new Activity("Burst Fade", 25, "./burst_fade.jpeg");
            Activity mohicano = new Activity("Mohicano", 25, "./mohicano.jpeg");
            Activity fadeDiseño = new Activity("Fade y diseño", 25, "./fade_diseno.jpeg");
            Activity dropFade = new Activity("Drop Fade", 20, "./drop_fade.jpeg");
            Activity lowTaper= new Activity("Low taper", 20, "./low_taper_fade.jpeg");
            Activity fadeYBarba = new Activity("Fade + Barba", 30, "./fade_barba.jpeg");
            Activity midTaper= new Activity("Mid taper", 20, "./mid_taper_fade.jpeg");
            Activity waves = new Activity("Tratamiento waves", 15, "./waves.jpeg");
            Activity indeciso = new Activity("Indeciso", 0, "./indeciso.png");

            User johnDoe = new User("John Doe", "john", "1234", new ArrayList<>(), new ArrayList<>());
            LocalDateTime fivePmToday = LocalDateTime.now().withHour(17).withMinute(0);
            Appointment appointment = new Appointment(fivePmToday, mullet, johnDoe);

            activityService.saveActivity(mullet);
            activityService.saveActivity(burstFade);
            activityService.saveActivity(mohicano);
            activityService.saveActivity(fadeDiseño);
            activityService.saveActivity(fadeYBarba);
            activityService.saveActivity(dropFade);
            activityService.saveActivity(lowTaper);
            activityService.saveActivity(midTaper);
            activityService.saveActivity(waves);
            activityService.saveActivity(indeciso);


            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));

            userService.saveUser(johnDoe);
            userService.saveUser(new User("admin", "admin", "admin", new ArrayList<>(),new ArrayList<>()));
            userService.saveUser(new User("Public", "public", "1234", new ArrayList<>(),new ArrayList<>()));
            userService.saveUser(new User("James Smith", "james", "1234", new ArrayList<>(),new ArrayList<>()));
            userService.saveUser(new User("Jane Carry", "jane", "1234", new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User("Chris Anderson", "chris", "1234", new ArrayList<>(),new ArrayList<>()));
    
            userService.addRoleToUser("admin", "ROLE_ADMIN");
            userService.addRoleToUser("admin", "ROLE_USER");
            userService.addRoleToUser("public", "ROLE_USER");
            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("james", "ROLE_ADMIN");
            userService.addRoleToUser("jane", "ROLE_USER");
            userService.addRoleToUser("chris", "ROLE_ADMIN");
            userService.addRoleToUser("chris", "ROLE_USER");

        };
    }

} 
