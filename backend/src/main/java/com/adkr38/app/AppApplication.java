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

            Activity fadeYBarba = new Activity("Fade + Barba", 30, "https://www.amazon.es/photos/share/v41A9fWxcc11cVWUoDTBMwPkIKNSFlLwFrzUwuJeXWs");
            Activity mullet = new Activity("Mullet", 25, "https://www.amazon.es/photos/share/fMkirw5Jsk2fUqxLResFa05COg6mOyoGWaXGeaf1E5A");
            Activity burstFade = new Activity("Burst Fade", 25, "https://www.amazon.es/photos/share/kMc2dl0mV1sIoqmPvQhJFly7L4cXhuFhIDHNTG5777F");
            Activity mohicano = new Activity("Mohicano", 25, "https://www.amazon.es/photos/share/2fxtHkIbWh9rhZxXOkzD2isqddknH6UPpkhoeLANik1");
            Activity fadeDiseño = new Activity("Fade y diseño", 25, "https://www.amazon.es/photos/share/1EU2qJcGTy5jbmTXbgKIDpqd2Xb94FbhEwWq9p1hONR");
            Activity dropFade = new Activity("Drop Fade", 20, "https://www.amazon.es/photos/share/lNJ4o8gg1IgB0pDjJhDh8jaSfesB0b19Fb5HDMToEsj");
            Activity lowTaper= new Activity("Low taper", 20, "https://www.amazon.es/photos/share/XNJOGiDuENv4aInmpDXVPyzZw1DjbWG4GAsLQwP7D2l");
            Activity midTaper= new Activity("Mid taper", 20, "https://www.amazon.es/photos/share/IinqI9KmmMZU9ncw2znJDqt8YaWU1EgruA3F3KIR42u");
            Activity waves = new Activity("Tratamiento waves", 15, "https://www.amazon.es/photos/share/oueX2npVN5gXUMMWIhw1bWFWllz6A1LTKHRKVo4J1Gi");
            Activity indeciso = new Activity("Indeciso", 0, "https://www.amazon.es/photos/share/7vr4YDwCpnbyFUSUH0ve6qw6NDfAAAxbCa5v9hisiwq");

            User johnDoe = new User("John Doe", "john", "1234", new ArrayList<>(), new ArrayList<>());
            LocalDateTime fivePmToday = LocalDateTime.now().withHour(17).withMinute(0);
            Appointment appointment = new Appointment(fivePmToday, mullet, johnDoe);

            activityService.saveActivity(fadeYBarba);
            activityService.saveActivity(mullet);
            activityService.saveActivity(burstFade);
            activityService.saveActivity(mohicano);
            activityService.saveActivity(fadeDiseño);
            activityService.saveActivity(dropFade);
            activityService.saveActivity(lowTaper);
            activityService.saveActivity(midTaper);
            activityService.saveActivity(waves);
            activityService.saveActivity(indeciso);


            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));

            userService.saveUser(johnDoe);
            userService.saveUser(new User("Public", "public", "1234", new ArrayList<>(),new ArrayList<>()));
            userService.saveUser(new User("James Smith", "james", "1234", new ArrayList<>(),new ArrayList<>()));
            userService.saveUser(new User("Jane Carry", "jane", "1234", new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User("Chris Anderson", "chris", "1234", new ArrayList<>(),new ArrayList<>()));
    
            userService.addRoleToUser("public", "ROLE_USER");
            userService.addRoleToUser("public", "ROLE_ADMIN");
            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("james", "ROLE_ADMIN");
            userService.addRoleToUser("jane", "ROLE_USER");
            userService.addRoleToUser("chris", "ROLE_ADMIN");
            userService.addRoleToUser("chris", "ROLE_USER");
            System.out.println(appointment);
            AppointmentService.saveAppointment(appointment);
        };
    }

} 
