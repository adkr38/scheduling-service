package com.adkr38.app.dtos;
import lombok.*;
import java.time.LocalDateTime;
import com.adkr38.app.models.*;

@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

  public LocalDateTime date;
  public String activity;
  public String username;

  public void setUsername(String username){
    this.username = username;
  }
  public String getUsername(){
    return username;
  }

  public LocalDateTime getDate(){
    return date;
  }
  
  public void setDate(LocalDateTime date){
    this.date = date;
  }
  
  public String getActivity(){
    return activity;
  }
  
  public void setActivity(String activity){
    this.activity= activity;
  }

}
