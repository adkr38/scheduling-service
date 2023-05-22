package com.adkr38.app.dtos;
import lombok.*;
import java.time.LocalDate;
import com.adkr38.app.models.*;

@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

  public LocalDate date;
  public Activity activity;
  public User user;

  public LocalDate getDate(){
    return date;
  }
  
  public void setDate(LocalDate date){
    this.date = date;
  }
  
  public Activity getActivity(){
    return activity;
  }
  
  public void setActivity(Activity activity){
    this.activity= activity;
  }

}
