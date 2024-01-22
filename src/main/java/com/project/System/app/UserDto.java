package com.project.System.app;

public class UserDto {
	private final String name;
	private final String surname;
	private final String login;
	
	public UserDto(final String name, final String surname, final String login) {
		this.name = name;
		this.surname = surname;
		this.login = login;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String toString() {
		return name+surname+login;
	}
}
