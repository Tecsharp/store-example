package com.tecsharp.store;

import com.tecsharp.store.controllers.productos.TipoProductosController;
import com.tecsharp.store.controllers.users.UsuariosController;
import com.tecsharp.store.entity.usuarios.Usuario;

public class StoreMain {

	public static void main(String[] args) {
		System.out.println("======================");
		System.out.println("BIENVENIDO A MI TIENDITA");
		System.out.println("======================");
		
		UsuariosController usuarios = new UsuariosController();
		Usuario usuario = null;
		
		while(usuario == null) {
			usuario = usuarios.login();
		}
		
		TipoProductosController productos = new TipoProductosController();
		productos.typeProductView();
		
		
		
	}

	
}
