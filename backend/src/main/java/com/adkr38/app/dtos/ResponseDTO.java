package com.adkr38.app.dtos;
public interface ResponseDTO{
  void setMessage(String message);
  String getMessage();
  void setStatusCode(int statusCode);
  int getStatusCode();
}
