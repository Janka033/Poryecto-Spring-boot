package co.edu.co.jan.diego.service;

import java.util.List;
import java.util.Optional;

import co.edu.co.jan.diego.dtos.UserDto;
import co.edu.co.jan.diego.model.Usuario;
import org.springframework.security.core.userdetails.User;

public interface IUserService {
	List<UserDto> findAll();
	Optional<Usuario> findById(Integer id);
	UserDto save (Usuario usuario);
	Optional<Usuario> findByEmail(String email);

}
