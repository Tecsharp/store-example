package com.tecsharp.store;

import com.tecsharp.store.controllers.productos.ProductosController;
import com.tecsharp.store.controllers.productos.TipoProductosController;
import com.tecsharp.store.controllers.users.UsuariosController;
import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;

public class StoreMain {

	public static void main(String[] args) {
		System.out.println("========================");
		System.out.println("BIENVENIDO A MI TIENDITA");
		System.out.println("========================");
		
		UsuariosController usuarios = new UsuariosController();
		Usuario usuario = null;
		
		while(usuario == null) {
			usuario = usuarios.login();
		}
		
		TipoProductosController tipoProductos = new TipoProductosController();
		TipoProducto tipoProducto = tipoProductos.getTypeProductID();
		
		ProductosController productos = new ProductosController();
		Producto producto = productos.getProductos(tipoProducto);
		
		productos.mostrarArticuloSeleccionado(producto, tipoProducto);
		
		
		
	}


	
}
