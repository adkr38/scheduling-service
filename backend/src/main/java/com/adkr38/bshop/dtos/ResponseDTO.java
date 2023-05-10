package com.adkr38.bshop.dtos;
public interface ResponseDTO{
  void setMessage(String message);
  String getMessage();
  void setStatusCode(int statusCode);
  int getStatusCode();
}
