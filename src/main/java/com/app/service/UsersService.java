package com.app.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.UsersDto;
import com.app.dto.UsersUpdateDto;
import com.app.entity.Users;
import com.app.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	ModelMapper modelMapper;

	public UsersDto addUserDetails(UsersDto usersDto) {
		usersRepository.saveAndFlush(modelMapper.map(usersDto, Users.class));
		return usersDto;

	}
	
	@Transactional
	public UsersUpdateDto updateCustomer(String userName, UsersUpdateDto usersUpdateDto) {
		usersUpdateDto.setUserName(userName);
		System.out.println(usersUpdateDto.getUserName()+":"+ usersUpdateDto.getName()+":"+ usersUpdateDto.getPassword());
		usersRepository.setPasswordByUserName(usersUpdateDto.getUserName(), 
				usersUpdateDto.getName(), usersUpdateDto.getPassword());
		return usersUpdateDto;
	}
	
	public ResponseEntity<Map<String, String>> login(String userName, String password){
		Map<String, String> map= new HashMap<>();
		int valid = usersRepository.vlaidUserNameAndPassword(userName, password);
		
		if(valid > 0) {
			map.put("Status", "Successfully loggedIn");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
		}
		map.put("Status", "falied to login. Please check username and password once.");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
	}
}
