package com.app.Mapper;

import com.app.domain.UserDto;
import com.app.model.User;

public class MapperTest {

	public static void main(String[] args) {

		UserDto dto = new UserDto();
		dto.setFirstname("firstname");
		dto.setLastname("lastname");
		dto.setEmail("email");
		dto.setMobileno("mobileno");
		dto.setPassword("password");
		User user = UserDtoMapper.INSTANCE.userDtoToUser(dto);

		System.out.println("User ::" + user);

	}

}
