package com.example.training.model;

import com.example.training.customAnnotation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private int id;

  @NotEmpty
  @Size(min = 2, max = 100, message = "name is between 2-100 characters")
  private String name;

  @NotEmpty
  @Email
  private String email;

  @NotEmpty
  @Size(min = 2, max = 15, message = "username is between 2-15 characters")
  private String username;

  @NotEmpty
  @ValidPassword
  private String password;
}
