package com.adkr38.app.models;
import jakarta.persistence.*;

@Entity
public class Activity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String activity;
  double price;
  @Column(name="image_url")
  String imageUrl;

  public Activity(String activity, double price, String imageUrl){
    setActivity(activity);
    setPrice(price);
    setImageUrl(imageUrl);
  }


public String getActivity() {
	return activity;
}

public void setActivity(String activity) {
	this.activity = activity;
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

public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
};

  public Activity(){}




}
