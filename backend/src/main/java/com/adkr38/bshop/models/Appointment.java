package com.adkr38.bshop.models;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Appointment{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;

  protected LocalDate date;
  protected Activity activity;

  public Appointment(LocalDate date, Activity activity){
    setDate(date);
    setActivity(activity);
  }

public LocalDate getDate() {
	return date;
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
