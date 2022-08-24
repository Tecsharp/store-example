package com.tecsharp.store.service.users.impl;

import com.tecsharp.store.entity.estatus.EnumEstatus;
import com.tecsharp.store.entity.usuarios.Usuario;
import com.tecsharp.store.repository.users.impl.UsuariosRepositoryImpl;
import com.tecsharp.store.service.users.UsuariosService;

public class UsuariosServiceImpl implements UsuariosService {

	@Override
	public Usuario login(String username, String password) {
		UsuariosRepositoryImpl repository = new UsuariosRepositoryImpl();

		Usuario usuario = repository.findByUsernameAndPassword(username, password);
		if (!islogged(usuario)) {
			return usuario;
		}

		if (!isActive(usuario)) {
			return null;
		}

		return usuario;
	}

	private boolean islogged(Usuario usuario) {

		if (usuario != null) {
			return true;
		} else {
			System.out.println("No se encontro el usuario");
			return false;
		}

	}

	private boolean isActive(Usuario usuario) {

		if (EnumEstatus.ENABLED.getEstatus() == usuario.getStatus()) {
			return true;
		} else {
			System.out.println("El usuario esta desactivado en la bd");
			return false;
		}
		
	}

}
