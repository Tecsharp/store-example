package com.tecsharp.store;

import com.tecsharp.store.controllers.productos.ProductosController;
import com.tecsharp.store.controllers.productos.TipoProductosController;
import com.tecsharp.store.controllers.users.UsuariosController;
import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;
import com.tecsharp.store.utils.Utilidad;

public class StoreMain {
 
	public static void main(String[] args) {
		
		System.out.println("========================");
		System.out.println("BIENVENIDO A MI TIENDITA");
		System.out.println("========================");

		UsuariosController usuarios = new UsuariosController();
		Usuario usuario = null;

		while (usuario == null) {
			usuario = usuarios.login();
			
		}
	


		Object isProducto = null;
		while (isProducto == null) {
			// INSTANCIAS //
			TipoProductosController tipoProductos = new TipoProductosController();
			ProductosController productos = new ProductosController();
			
			TipoProducto tipoProducto = tipoProductos.getTypeProductID(usuario); // Obtiene la lista de departamentos

			Producto producto = productos.getProductos(tipoProducto); // Obtiene la lista de productos

			if (productos.mostrarArticuloSeleccionado(producto, tipoProducto) != null) {
				productos.agregarAlCarritoVista(producto, usuario);

			} else {
				System.out.println("Regresando al inicio");
				Utilidad.clearScreen();
			}
		}

	}

}
