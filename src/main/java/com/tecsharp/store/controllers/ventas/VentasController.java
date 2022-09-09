package com.tecsharp.store.controllers.ventas;

import java.util.List;

import com.tecsharp.store.entity.ventas.Venta;
import com.tecsharp.store.service.ventas.impl.VentasServiceImpl;

public class VentasController {

	public static void verCompras(Integer userId) {
		System.out.println("COMPRAS HECHAS ANTERIORMENTE\n");
		VentasServiceImpl ventas = new VentasServiceImpl();
		ventas.getVentas(userId);
		
		List<Venta> compras = ventas.getVentas(userId);

		for (Venta venta : compras) {
			System.out.println("ID: " + venta.getIdSale() + " | " + venta.getDateCreate() + " | Cant: " + venta.getNumItems() 
			+ " | " + venta.getNameProduct());
		}
		
		try {
			System.out.println();
			System.out.println("PRESIONA ENTER PARA CONTINUAR");
			System.in.read();
		} catch (Exception e) {
		}
		
	}

}
