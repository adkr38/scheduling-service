package com.adkr38.app.models;
import java.time.LocalDate;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

@Entity
public class Appointment{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  protected LocalDate date;
  @ManyToOne
  @JoinColumn(name = "activity_id")
  protected Activity activity;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "user_id")
  User user;

  public Appointment(LocalDate date, Activity activity,User user){
    setDate(date);
    setActivity(activity);
    setUser(user);
  }

public LocalDate getDate() {
	return date;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public void setDate(LocalDate date) {
	this.date = date;
}

public Activity getActivity() {
	return activity;
}

public void setActivity(Activity activity) {
	this.activity = activity;
}

  public Appointment(){};


}
