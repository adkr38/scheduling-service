package com.adkr38.app.dtos;
import lombok.*;
import java.time.LocalDateTime;
import com.adkr38.app.models.*;

@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

  public LocalDateTime date;
  public Activity activity;
  public User user;

  public LocalDateTime getDate(){
    return date;
  }
  
  public void setDate(LocalDateTime date){
    this.date = date;
  }
  
  public Activity getActivity(){
    return activity;
  }
  
  public void setActivity(Activity activity){
    this.activity= activity;
  }

}
