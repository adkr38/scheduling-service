package com.adkr38.bshop.models;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Activity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  String activity;
  int duration;
  double price;
  String imageUrl;

  public Activity(String activity, int duration, double price, String imageUrl){
    setActivity(activity);
    setDuration(duration);
    setPrice(price);
    setImageUrl(imageUrl);
  }

public String getActivity() {
	return activity;
}

public void setActivity(String activity) {
	this.activity = activity;
}

public int getDuration() {
	return duration;
}

public void setDuration(int duration) {
	this.duration = duration;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public String getImageUrl() {
	return imageUrl;
}

public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

  public Activity(){};


}
