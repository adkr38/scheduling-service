package com.adkr38.app.models;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Appointment{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  protected LocalDateTime date;
  @ManyToOne
  @JoinColumn(name= "activity_id")
  protected Activity activity;

  @ManyToOne
  @JoinColumn(name= "user_id")
  @JsonIgnore
  User user;

  public Appointment(LocalDateTime date, Activity activity,User user){
    setDate(date);
    setActivity(activity);
    setUser(user);
  }

public LocalDateTime getDate() {
	return date;
}

public void setDate(LocalDateTime date) {
	this.date = date;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}


public Activity getActivity() {
	return activity;
}

public void setActivity(Activity activity) {
	this.activity = activity;
}

  public Appointment(){};


}
