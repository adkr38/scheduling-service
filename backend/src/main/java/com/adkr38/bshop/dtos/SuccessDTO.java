package com.adkr38.bshop.dtos;
import java.util.List;

public class SuccessDTO<T> implements ResponseDTO{
  protected String message; 
  protected int statusCode; 
  protected List<T> content;


  public List<T> getContent() {
    return content;
  }
  public void setContent(List<T> content) {
    this.content = content;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public int getStatusCode() {
    return statusCode;
  }
  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public SuccessDTO(List<T> content, String message, int statusCode){
    setContent(content);
    setMessage(message);
    setStatusCode(statusCode);
  }

}
