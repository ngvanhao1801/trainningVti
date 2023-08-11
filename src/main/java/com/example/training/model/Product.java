package com.example.training.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  private int id;

  @NotBlank(message = "Name is mandatory")
  private String name;

  private String shortDes;

  private String description;

  private double price;
}
