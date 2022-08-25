package com.tecsharp.store.controllers.users;

import java.util.Scanner;

import com.tecsharp.store.entity.usuarios.Usuario;
import com.tecsharp.store.service.users.impl.UsuariosServiceImpl;
import com.tecsharp.store.utils.Utilidad;

public class UsuariosController {

	public Usuario login() {

		UsuariosServiceImpl service = new UsuariosServiceImpl();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("INGRESA TU NOMBRE DE USUARIO");
		String username = sc.next();
		
		System.out.println("INGRESA TU CONTRASENA");
		String password = sc.next();
		Utilidad.clearScreen();
		
		return service.login(username, password);
	}
	
	
}
