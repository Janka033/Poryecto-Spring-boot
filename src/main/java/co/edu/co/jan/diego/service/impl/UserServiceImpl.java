package co.edu.co.jan.diego.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.co.jan.diego.dtos.UserDto;
import co.edu.co.jan.diego.mapping.OrderMapper;
import co.edu.co.jan.diego.mapping.UserMapper;
import co.edu.co.jan.diego.model.Usuario;
import co.edu.co.jan.diego.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.co.jan.diego.repository.IUsuarioRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> findById(Integer id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public UserDto save(Usuario usuario) {
		return UserMapper.mapFrom(usuarioRepository.save(usuario));
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public List<UserDto> findAll() {
		return usuarioRepository.findAll().stream().map(UserMapper::mapFrom).collect(Collectors.toList());
	}
	
	
}
