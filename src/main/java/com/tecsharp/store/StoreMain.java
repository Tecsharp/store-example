package com.tecsharp.store;

import java.util.Scanner;

import com.tecsharp.store.repository.users.impl.UsuariosRepositoryImpl;


public class StoreMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner (System.in);
		UsuariosRepositoryImpl cliente = new UsuariosRepositoryImpl();
		
		System.out.println("BIENVENIDO A LA TIENDITA\n=======================");
		System.out.println("INICIA SESIÓN");

		System.out.println("Escribe la usuario");
		String username = sc.next();
		
		System.out.println("Escribe el contraseña");
		String password = sc.next();
		
		cliente.getUserclient(username, password);
		
		
		
		
		

	}

}
