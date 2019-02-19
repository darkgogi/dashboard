package com.example.hello.controller;

import com.example.hello.dto.UserDto;
import com.example.hello.model.User;
import com.example.hello.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Slf4j
@RestController
@RequestMapping(path="/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@GetMapping(path="/all")
	public Iterable<User> getAll() {
		return userService.getAll();
	}

	@GetMapping(path="/{id}")
	public User getUser(@PathVariable String id) {
		return userService.getUser(id);
	}

	@PostMapping(path="")
	public String addUser(@RequestBody UserDto dto) {
		return userService.addUser(dto);
	}

	@PutMapping(path="/{id}")
	public String updateUser(@PathVariable String id, @RequestBody UserDto dto) {
		return userService.updateUser(id, dto);
	}

	@DeleteMapping(path="/{id}")
	public String deleteUser(@PathVariable String id) {
		return userService.deleteUser(id);
	}

	@PutMapping(path="")
	public String addUserBulk(@RequestBody UserDto dto, @RequestParam int count) {
		return userService.addUserBulk(dto, count);
	}

	@DeleteMapping(path="")
	public String deleteUserBulk(@RequestParam String target) {
		return userService.deleteUserBulk(target);
	}

	@GetMapping(path="")
	public Iterable<User> searchUser(@RequestParam(name="k") String key, @RequestParam(name="v") String value) {
		return userService.searchUser(key, value);
	}
}
