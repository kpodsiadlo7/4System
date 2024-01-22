package com.project.System.app;

class User {
	private final String name;
	private final String surname;
	private final String login;
	
	User(final String name, final String surname, final String login) {
		this.name = name;
		this.surname = surname;
		this.login = login;
	}
	
	String getName() {
		return name;
	}
	
	String getSurname() {
		return surname;
	}
	
	String getLogin() {
		return login;
	}
}
