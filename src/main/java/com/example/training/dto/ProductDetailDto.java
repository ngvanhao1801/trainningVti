package com.example.training.dto;

import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {

  private int productId;

  private String productName;

  private String shortDes;

  private String description;

  private double price;

  private int total;

  private List<Category> categories = new ArrayList<>();
}
