package com.example.training.mapper;

import com.example.training.dto.ProductDto;
import com.example.training.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "name", source = "dto.name")
  @Mapping(target = "shortDes", source = "dto.shortDes")
  @Mapping(target = "description", source = "dto.description")
  @Mapping(target = "price", source = "dto.price")
  Product toProduct(ProductDto dto);

}
