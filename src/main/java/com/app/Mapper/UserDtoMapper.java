package com.app.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.app.domain.UserDto;
import com.app.model.User;

@Mapper
public interface UserDtoMapper {

	UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);
	
	UserDto userToUserDto(User user);
	User userDtoToUser(UserDto userDto);
	
}