package com.example.training.mapper;

import com.example.training.dto.UserDto;
import com.example.training.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mappings({
      @Mapping(source = "dto.name", target = "name"),
      @Mapping(source = "dto.email", target = "email"),
      @Mapping(source = "dto.username", target = "username"),
      @Mapping(source = "dto.password", target = "password")
  })
  User toUser(UserDto dto);

}
