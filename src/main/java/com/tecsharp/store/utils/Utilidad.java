package com.tecsharp.store.utils;

import java.util.Scanner;

public class Utilidad {
	
	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	
	public static boolean esOpcionUnoCorrecto() {
		
		boolean isEntero = false;
		while (!isEntero) { // VALIDA QUE SEA UN ENTERO POSITIVO Y NO UNA LETRA
			try {
				Scanner sc = new Scanner(System.in);
				//System.out.println("1. PARA CONFIRMAR");
				Integer opcion = sc.nextInt();
				
				if (opcion == 1) {
					isEntero = true;
					return true;
				} else {
					System.out.println("AGREGA UN NUMERO VALIDO");
					return false;
				}
			} catch (Exception e) {
				isEntero = false;
				//System.out.println("AGREGA UN NUMERO VALIDO");
				//boolean back = false;
				return false;
				
			}
		}
		
		return false;
	}
	

}
