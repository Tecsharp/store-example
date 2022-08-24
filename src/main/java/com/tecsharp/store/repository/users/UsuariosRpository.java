package com.tecsharp.store.repository.users;

import com.tecsharp.store.entity.usuarios.Usuario;

public interface UsuariosRpository {

	Usuario findById(Integer userId);
	
	Usuario findByUsernameAndPassword(String username, String password);
}