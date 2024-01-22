package com.project.System.app;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collections;

@Service
class UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final static int MAX_ALLOWED_SIZE_IN_KB = 5120;
	
	private UserService(final UserRepository userRepository, final UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}
	
	String validateBeforeSave(final List<UserDto> jsonXMLData, Model model, HttpServletRequest request) {
		if(jsonXMLData == null || jsonXMLData.isEmpty()) {
			model.addAttribute("errormsg","Plik jest pusty!");
			return "upload";
		}
		
		// checking if jsonXMLData is less than 'MAX_ALLOWED_SIZE_IN_KB'
		if(checkFileSize(request)) {
			model.addAttribute("errormsg","Plik jest za duży! Maks to 5mb");
			return "upload"; 
		}
		
		long startTime = System.currentTimeMillis();
		  
	    // collecting only users without null or blank variables
	    List<User> usersAfterValidate = userMapper.mapToUserListFromUserDtoList(jsonXMLData
	    		.stream()
	    		.filter(user -> validateDataNotNullOrBlank(user))
	    		.collect(Collectors.toList()));
	     
	    long endTime = System.currentTimeMillis();
	    long executionTime = endTime - startTime;
	    summary(model,executionTime, checkIfNewUserExistInDatabase(usersAfterValidate), jsonXMLData.size(), usersAfterValidate.size());
	    return "upload";
	}

	private int checkIfNewUserExistInDatabase(List<User> usersAfterValidate) {
		List<User> usersToSave = new ArrayList<>();
		if(usersAfterValidate.size() > 0) {
	    	List<User> usersFromDb = userMapper.mapToUserListFromUserEntityList(userRepository.findAll());
	    	
	    	// collecting only users that do not exist in the database
	    	usersToSave = usersAfterValidate
	    			.stream()
	    			.filter(newUser -> !isUserExistInDatabase(newUser, usersFromDb))
	    			.collect(Collectors.toList());
	    	if(usersToSave.size() > 0) {
	    		
	    		// sorting users before save
	    		usersToSave.sort(Comparator.comparing(User::getName, new NumericStringComparator()));
	    		
	    		// saving only users that do not exist in the database
	    	    userRepository.saveAll(userMapper.mapToUserEntityListFromUserList(usersToSave));
	    	}
	    }
		return usersToSave.size();
	}
	
	private boolean checkFileSize(HttpServletRequest request) {
        return MAX_ALLOWED_SIZE_IN_KB < (request.getContentLength() / 1024);
	}

	private boolean validateDataNotNullOrBlank(final UserDto user) {
		return user != null && user.getName() != null && user.getSurname() != null && user.getLogin() != null
				&& !user.getName().isBlank() && !user.getSurname().isBlank() && !user.getLogin().isBlank();
	}
	
	private boolean isUserExistInDatabase(User newUser, List<User> existingUsers) {
        return existingUsers.stream()
                .anyMatch(existingUser ->
                        existingUser.getName().equals(newUser.getName()) &&
                        existingUser.getSurname().equals(newUser.getSurname()) &&
                        existingUser.getLogin().equals(newUser.getLogin()));
	}
	
	private void summary(Model model, final long executionTime, final int records, final int entryDataSize, final int qtyCorrectData) {
		int duplicate = ((entryDataSize - (entryDataSize-qtyCorrectData))-records);
		int incorrectData = (entryDataSize-qtyCorrectData);
		model.addAttribute("time", "Pomyślnie dodano użytkowników do bazy danych w " + executionTime / 1000 
				+ "s."+" Przesłano: "+entryDataSize+". Dodano łącznie " 
	    		+ (records == 0 ? records + " rekordów, " : records == 1 ? records + " rekord " : 
	    			records < 5 ? records + " rekordy, " : records + " rekordów, ") 
	    		+ " pomijając duplikaty " + duplicate + " i niepoprawne dane: " + incorrectData);
	}
	
	String sortData(Model model, final int page, final int size, final String searchTerm, final String sortField, final String sortDirection) {
	    List<User> userList = returnUserByNameOrSurnameOrLoginDependsOnSearchTerm(searchTerm);
	    userList = sortDataDependsOnSortField(sortField,userList);

	    if (sortDirection.equals("desc")) {
	        Collections.reverse(userList);
	    }

	    int start = page * size;
	    int end = Math.min((page + 1) * size, userList.size());
	    List<UserDto> paginatedList = userMapper.mapToUserDtoListFromUserList(userList.subList(start, end));
	    int dbSize = userList.size();
	    
	    model.addAttribute("users", paginatedList);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", (int) Math.ceil((double) dbSize / size));
	    model.addAttribute("searchTerm", searchTerm);
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDirection", sortDirection);
	    model.addAttribute("hasNextPage", !hasNextPage(dbSize,page,size));

	    return "display";
	}
	
	private List<User> returnUserByNameOrSurnameOrLoginDependsOnSearchTerm(String searchTerm) {
		return searchTerm != null && !searchTerm.isEmpty() ? 
				userMapper.mapToUserListFromUserEntityList(userRepository.findByNameOrSurnameOrLogin(searchTerm, searchTerm, searchTerm)) 
				: userMapper.mapToUserListFromUserEntityList(userRepository.findAll());
	}

	private boolean hasNextPage(int dbSize, int currentPage, int size) {
		return currentPage+1 > dbSize/size;
	}
	
	private List<User> sortDataDependsOnSortField(final String sortField, List<User> userList) {
		if(sortField == null || sortField.isBlank()) return userList;
		switch (sortField) {
	    	case "name" -> userList.sort(Comparator.comparing(User::getName, new NumericStringComparator()));
	    	case "surname" -> userList.sort(Comparator.comparing(User::getSurname, new NumericStringComparator()));
	    	case "login" -> userList.sort(Comparator.comparing(User::getLogin, new NumericStringComparator()));
	    	default -> userList.sort(Comparator.comparing(User::getName, new NumericStringComparator()));
		}
		return userList;
	}
	
	private class NumericStringComparator implements Comparator<String> {
	    @Override
	    public int compare(String s1, String s2) {
	        int num1 = extractNumber(s1);
	        int num2 = extractNumber(s2);

	        return Integer.compare(num1, num2);
	    }

	    // if there is no number then return 0
	    private int extractNumber(String s) {
	        String digitsOnly = s.replaceAll("\\D", ""); 
	        if (!digitsOnly.isEmpty()) {
	            return Integer.parseInt(digitsOnly);
	        } else {
	            return 0;
	        }
	    }
	}
}
