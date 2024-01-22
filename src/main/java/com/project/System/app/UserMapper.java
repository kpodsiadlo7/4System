package com.project.System.app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
class UserMapper {

	UserDto mapToUserDtoFromUser(final User user) {
		return new UserDto(
				user.getName(),
				user.getSurname(),
				user.getLogin());
	}
	
	UserEntity mapToUserEntityFromUser(final User user) {
		return new UserEntity(
				user.getName(),
				user.getSurname(),
				user.getLogin());
	}
	
	User mapToUserFromUserEntity(final UserEntity userEntity) {
		return new User(
				userEntity.getName(),
				userEntity.getSurname(),
				userEntity.getLogin());
	}
	
	User mapToUserFromUserDto(final UserDto userDto) {
		return new User(
				userDto.getName(),
				userDto.getSurname(),
				userDto.getLogin());
	}
	
	List<User> mapToUserListFromUserEntityList(final List<UserEntity> userEntityList) {
		return userEntityList
				.stream()
				.map(this::mapToUserFromUserEntity)
				.collect(Collectors.toList());
	}

	List<UserEntity> mapToUserEntityListFromUserList(final List<User> userList) {
		return userList
				.stream()
				.map(this::mapToUserEntityFromUser)
				.collect(Collectors.toList());
	}

	List<User> mapToUserListFromUserDtoList(final List<UserDto> userDtoList) {
		return userDtoList
				.stream()
				.map(this::mapToUserFromUserDto)
				.collect(Collectors.toList());
	}
	
	List<UserDto> mapToUserDtoListFromUserList(final List<User> userList) {
		return userList
				.stream()
				.map(this::mapToUserDtoFromUser)
				.collect(Collectors.toList());
	}
}
