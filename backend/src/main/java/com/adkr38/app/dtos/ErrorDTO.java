package com.adkr38.app.dtos;

public class ErrorDTO implements ResponseDTO{
  protected String message;
  protected int statusCode;

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

  public ErrorDTO(String message, int statusCode){
    setMessage(message);
    setStatusCode(statusCode);
  }

}
