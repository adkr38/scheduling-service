package com.adkr38.app;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.adkr38.app.models.*;
import com.adkr38.app.repositories.*;
import com.adkr38.app.services.impl.*;
import java.util.Arrays;
import java.util.HashMap;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AppApplicationTests {

  @Autowired
  private WebApplicationContext webApplicationContext; 

  private MockMvc mockMvc;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private AppointmentService appointmentService;

  @Autowired
  private ActivityRepository activityRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  private final ObjectMapper objectMapper = new ObjectMapper();

  private Role adminRole;
  private Role userRole;
  private User user;
  private Activity activity;
  private Appointment appointment;

  @BeforeEach
  void setUp(){
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    adminRole = new Role(null,"ROLE_ADMIN");
    userRole= new Role(null,"ROLE_USER");
    roleRepository.save(adminRole);
    roleRepository.save(userRole);

    user = new User("adrien", "adrien", "1234", new ArrayList<Role>(), new ArrayList<>());
    user.setRoles(List.of(adminRole,userRole));
    userRepository.save(user);

    activity = new Activity("Ejemplo", 25, "./ejemplo.jpeg");
    activityRepository.save(activity);

    appointment = new Appointment(LocalDateTime.now().plusMinutes(90),activity,user);
    appointmentRepository.save(appointment);
  }

  @AfterEach
  void tearDown(){
    appointmentRepository.delete(appointment);
    activityRepository.delete(activity);
    userRepository.delete(user);
    roleRepository.delete(adminRole);
    roleRepository.delete(userRole);

  }

  @Test
  void testAdrienPersisted() throws Exception{
    String username = user.getUsername();
    Assertions.assertTrue(userRepository.findByUsername(username).isPresent());
  }

  @Test
  void testActivityPersisted() throws Exception{
    String activityName = activity.getActivity();
    Assertions.assertTrue(activityRepository.findByActivity(activityName).isPresent());
  }

  @Test
  void testAppointmentPersisted() throws Exception{
    Long appointmentId = appointment.getId();
    Assertions.assertTrue(appointmentRepository.findById(appointmentId).isPresent());
  }
}
