package com.example.training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisProductDto {

  private int month;

  private String name;

  private int totalMoney;

  private int totalProduct;
}
