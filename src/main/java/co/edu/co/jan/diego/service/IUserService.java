package co.edu.co.jan.diego.service;

import java.util.List;
import java.util.Optional;

import co.edu.co.jan.diego.model.Usuario;

public interface IUserService {
	List<Usuario> findAll();
	Optional<Usuario> findById(Integer id);
	Usuario save (Usuario usuario);
	Optional<Usuario> findByEmail(String email);

}