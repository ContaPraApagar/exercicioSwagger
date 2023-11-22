package com.nicolas.Lombok.PrjLombok.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicolas.Lombok.PrjLombok.entities.User;
import com.nicolas.Lombok.PrjLombok.repository.UserRepository;

@Service
public class UserServices {
	
	private final UserRepository userRepository;

	@Autowired
	public UserServices (UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// preparando as buscas por id
	public User findUserById(Long id) {
		Optional<User> User = userRepository.findById(id);
		return User.orElse(null);
	}

	// preparando a busca geral
	
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	// salvando o Jogo
	public User insertUser(User User) {
		return userRepository.save(User);
	}

	// fazendo o update do jogo com o optional
	public User updateUser(Long id, User novoUser) {
		Optional<User> UserOptional = userRepository.findById(id);
		if (UserOptional.isPresent()) {
			User UserExistente = UserOptional.get();
			UserExistente.setNome(novoUser.getNome());
			UserExistente.setEmail(novoUser.getEmail());
			return userRepository.save(UserExistente);
		} else {
			return null;
		}
	}

	// deletando o update do User com o optional
	public boolean deleteUser(Long id) {
		Optional<User> UserExistente = userRepository.findById(id);
		if (UserExistente.isPresent()) {
			userRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}

