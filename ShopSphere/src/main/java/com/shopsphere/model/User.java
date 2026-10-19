package com.shopsphere.model;
import java.io.Serializable;
public class User implements Serializable {
 private static final long serialVersionUID=1L;
 private int userId;private String name,email,role;
 public User(int id,String n,String e,String r){userId=id;name=n;email=e;role=r;}
 public int getUserId(){return userId;}public String getName(){return name;}public String getEmail(){return email;}public String getRole(){return role;}
}
