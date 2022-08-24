package com.tecsharp.store.controllers.productos;

import java.util.List;
import java.util.Scanner;

import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.service.productos.impl.TipoProductosServiceImpl;

public class TipoProductosController {

	public void typeProductView() {
		
		Scanner sc = new Scanner (System.in);
		System.out.println("SELECCIONA UN TIPO DE PRODUCTO");
		TipoProductosServiceImpl service = new TipoProductosServiceImpl();
		List<TipoProducto> tipoProductos = service.getTipoProductos(); 
		
		for (TipoProducto tipoProducto : tipoProductos) {
			System.out.println(tipoProducto.getIdProductType() + ": ".concat(tipoProducto.getName()));
		}
		
		Integer option = sc.nextInt();
		
	}

}
