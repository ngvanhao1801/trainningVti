package com.example.training.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterProductInput {

  private String keyword;

  private Integer limit;

  private Integer page;

  private String sortBy;

  private String sortType;

}
