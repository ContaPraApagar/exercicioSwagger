package com.nicolas.Lombok.PrjLombok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicolas.Lombok.PrjLombok.entities.User;
import com.nicolas.Lombok.PrjLombok.services.UserServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Users", description = "API REST DE GERENCIAMENTO DE USUÁRIOS")
@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserServices userServices;

	@Autowired
	public UserController(UserServices userServices) {
		this.userServices = userServices;
	}

	@GetMapping("/{id}")
	@Operation(summary = "localiza usuário por ID")
	public ResponseEntity<User> findUserbyId(@PathVariable Long id) {
		User User = userServices.findUserById(id);
		if (User != null) {
			return ResponseEntity.ok(User);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os usuários ")
	public ResponseEntity<List<User>> findAllUserscontrol() {
		List<User> Users = userServices.findAllUser();
		return ResponseEntity.ok(Users);
	}
	
	

	@PostMapping("/")
	@Operation(summary = "Cadastra um usuário")
	public ResponseEntity<User> insertUsersControl(@RequestBody @Valid User User) {
		User novoUser = userServices.insertUser(User);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoUser);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera um usuário")
	public ResponseEntity<User> updateUserControl(@PathVariable Long id, @RequestBody @Valid User User) {
		User mudaUser = userServices.updateUser(id, User);
		if (mudaUser != null) {
			return ResponseEntity.ok(User);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um usuário")
	public ResponseEntity<String> deleteUserControl(@PathVariable Long id) {
		boolean remover = userServices.deleteUser(id);
		if (remover) {
			return ResponseEntity.ok().body("User Excluido com sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}
