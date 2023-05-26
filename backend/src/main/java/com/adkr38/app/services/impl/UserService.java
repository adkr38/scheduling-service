package com.adkr38.app.services.impl;
import com.adkr38.app.models.*;
import com.adkr38.app.repositories.*;
import com.adkr38.app.dtos.*;
import com.adkr38.app.services.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface, UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * Autowired UserRepository for database operations.
     */
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ActivityRepository activityRepository;

    /**
     * Autowired RoleRepository for database operations.
     */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Injects a bean of type PasswordEncoder into this class.
     * The bean is used for encoding passwords before storing them.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Loads the user by its username
     *
     * @param username the username to search for
     * @return the UserDetails object that matches the given username
     * @throws UsernameNotFoundException if the user with the given username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user with the given username
        Optional<User> optionalUser = userRepository.findByUsername(username);
        // Check if user exists
        if (optionalUser.isEmpty()) {
            LOGGER.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            User user = optionalUser.get();
            LOGGER.info("User found in the database: {}", username);
            // Create a collection of SimpleGrantedAuthority objects from the user's roles
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            // Return the user details, including the username, password, and authorities
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    /**
     * Saves a new user to the database
     *
     * @param user the user to be saved
     * @return the saved user
     */
    // @Override
    // public int saveUser(User user) {
    //   System.out.println(String.format("ADDING USER %s", user.getUsername()));
    //   Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
    //   if (optionalUser.isPresent()){
    //     System.out.println(String.format("USER %s ALREADY IS IN THE DB.", user.getUsername()));
    //     return 0;
    // }
    // user.setPassword(passwordEncoder.encode(user.getPassword()));
    //  userRepository.save(user);
    //   System.out.println(String.format("USER %s ADDED OK.", user.getUsername()));
    //   return 1;
    // }

    @Override
    public User saveUser(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
     return userRepository.save(user);
    }

    /**
     * Saves a new role to the database
     *
     * @param role the role to be saved
     * @return the saved role
     */
    @Override
    public Role saveRole(Role role) {
      return roleRepository.save(role);
    }


    /**
     * Adds a role to the user with the given username
     *
     * @param username the username of the user to add the role to
     * @param roleUsername the username of the role to be added
     */
    @Override
    public void addRoleToUser(String username, String roleName) {
        LOGGER.info("Adding role {} to user {}", roleName, username);

        // Retrieve the user and role objects from the repository
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()){
          LOGGER.info("User {} doesn't exist.", username);
      return;
    }
        User user = optionalUser.get();
        Optional<Role> optionalRole = roleRepository.findByName(roleName);
        if (optionalRole.isEmpty()){
          return;
        }
        Role role = optionalRole.get();

        // Add the role to the user's role collection
        user.getRoles().add(role);

        // Save the user to persist the changes
        userRepository.save(user);
    }

    /**
     * Retrieves the user with the given username
     *
     * @param username the username to search for
     * @return the user with the given username
     */
    @Override
    public User getUser(String username) {
        LOGGER.info("Fetching user {}", username);
        return userRepository.findOneByUsername(username);
    }

    /**
     * Retrieves all users from the database
     *
     * @return a list of all users
     */
    @Override
    public List<User> getUsers() {
        LOGGER.info("Fetching all users");
        return userRepository.findAll();
    }

    public int addAppointment(AppointmentDTO appointmentDTO) {

        Appointment appointmnet= new Appointment();


        Optional<User> optionalUser = userRepository.findByUsername(appointmentDTO.getUsername());

        if (optionalUser.isEmpty()){
        appointmnet.setUser(userRepository.findByUsername("public").get());
    } else{
        appointmnet.setUser(optionalUser.get());
    }

        Optional<Activity> optionalActivity = activityRepository.findByActivity(appointmentDTO.getActivity());
        if (optionalActivity.isEmpty()){
        appointmnet.setActivity(activityRepository.findByActivity("Indeciso").get());
    } else{
        appointmnet.setActivity(optionalActivity.get());
    }


        appointmnet.setDate(appointmentDTO.getDate());

        return appointmentService.saveAppointment(appointmnet);

    }

    public List<Appointment> getAppointments(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()){
        LOGGER.info(String.format("Tried to add appointments to non existant user {}", username));
        return List.of();
    }
        User user = optionalUser.get();

        return user.getAppointments();
    }
}
