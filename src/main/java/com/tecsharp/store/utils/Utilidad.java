package com.tecsharp.store.utils;

import java.util.Scanner;

public class Utilidad {
	
	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	
	public static boolean esOpcionUnoCorrecto(Integer idProducto) {
		
		boolean isEntero = false;
		while (!isEntero) { // VALIDA QUE SEA UN ENTERO POSITIVO Y NO UNA LETRA
			try {
				if (idProducto == 1) {
					isEntero = true;
				} else {
				}
			} catch (Exception e) {
				isEntero = false;
				System.out.println("AGREGA UN NUMERO VALIDO");
			}
		}
		
		return false;
	}
	

}
