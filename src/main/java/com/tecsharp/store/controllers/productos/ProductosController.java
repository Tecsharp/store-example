package com.tecsharp.store.controllers.productos;

import java.util.List;
import java.util.Scanner;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.service.productos.impl.ProductosServiceImpl;
import com.tecsharp.store.utils.Utilidad;

public class ProductosController {

	public Producto getProductos(TipoProducto tipoProducto) {

		Integer idProducto = null;
		Producto producto = null;

		while (producto == null) {
			Scanner sc = new Scanner(System.in);
			System.out.println("SELECCIONA UN PRODUCTO\n");
			ProductosServiceImpl service = new ProductosServiceImpl();
			List<Producto> productos = service.getProducto(tipoProducto);

			int i = 0;
			for (Producto productos1 : productos) {
				System.out.println(i++ + ": ".concat(productos1.getName()));
			}

			idProducto = sc.nextInt();
			producto = service.validaProductoID(idProducto, productos);
			Utilidad.clearScreen();

		}
		return producto;

	}
	
	public void mostrarArticuloSeleccionado(Producto producto, TipoProducto tipoProducto) {
		ProductosServiceImpl service = new ProductosServiceImpl();
		List<Producto> productos = service.getProducto(tipoProducto);
		
		System.out.println("ARTICULO SELECCIONADO:");
		System.out.println("Nombre: " + producto.getName());
		System.out.println("Stock disponible: " + producto.getStock());
		System.out.println("Precio: " + producto.getPrice());
		System.out.println("Caracteristicas: " + producto.getDescription());
		
	}

}
