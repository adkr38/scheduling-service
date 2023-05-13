package com.adkr38.app.models;
import jakarta.persistence.*;
import com.adkr38.app.enums.*;

@Entity
public class Role{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private RoleType role;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public RoleType getRole() {
	return role;
}
public void setRole(RoleType role) {
	this.role = role;
}

  public Role(RoleType role){
    setRole(role);
  }

  public Role(){};

}
