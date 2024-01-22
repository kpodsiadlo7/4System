package com.project.System.app;

import jakarta.persistence.*;

@Entity
@Table(name="users")
class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname;
	private String login;
	
	UserEntity() 
	{
	}
	
	UserEntity(final String name, final String surname, final String login)
	{
		this.name = name;
		this.surname = surname;
		this.login = login;
	}
	
	String getName() {
		return name;
	}
	
	String getSurname()
	{
		return surname;
	}
	
	String getLogin()
	{
		return login;
	}
	
	Long getId()
	{
		return id;
	}
}
