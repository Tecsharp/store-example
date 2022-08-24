package com.tecsharp.store.service.users;

import com.tecsharp.store.entity.usuarios.Usuario;

public interface UsuariosService {

	Usuario login(String username, String password);
	
}
