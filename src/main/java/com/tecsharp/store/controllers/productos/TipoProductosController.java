package com.tecsharp.store.controllers.productos;

import java.util.List;
import java.util.Scanner;

import com.tecsharp.store.controllers.users.UsuariosController;
import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;
import com.tecsharp.store.service.productos.impl.TipoProductosServiceImpl;
import com.tecsharp.store.utils.Utilidad;

public class TipoProductosController {

	public TipoProducto getTypeProductID(Usuario usuario) {

		Integer idTipoProducto = null;
		TipoProducto tipoProducto = null;

		while (tipoProducto == null) {

			Scanner sc = new Scanner(System.in);
			System.out.println("SELECCIONA UN TIPO DE PRODUCTO\n");
			TipoProductosServiceImpl service = new TipoProductosServiceImpl();
			List<TipoProducto> tipoProductos = service.getTipoProductos();

			for (TipoProducto tipoProducto1 : tipoProductos) {
				System.out.println(tipoProducto1.getIdProductType() + ": ".concat(tipoProducto1.getName()));
			}

			System.out.println("");
			ProductosController verCarrito = new ProductosController();
			System.out.println("0. Ver carrito");
			System.out.println("9. Cerrar sesion");

			if (usuario.getUserType() == 2) {
				System.out.println("\n============ ADMIN ============");
				System.out.println("7. Registrar nuevos productos");
			}

			idTipoProducto = sc.nextInt();

			if (idTipoProducto == 0) {
				verCarrito.verCarrito(usuario);
			} else if (idTipoProducto == 9) {
				// UsuariosController usuarios = new UsuariosController();

			} else if (idTipoProducto == 7) {
				crearProductoVista(usuario);
			}

			tipoProducto = service.validaTipoProductoID(idTipoProducto, tipoProductos);
			Utilidad.clearScreen();

		}

		return tipoProducto;
	}

	public Producto crearProductoVista(Usuario usuario) {

		TipoProductosServiceImpl service = new TipoProductosServiceImpl();
		List<TipoProducto> tipoProductos = service.getTipoProductos();

		for (TipoProducto tipoProducto1 : tipoProductos) {
			System.out.println(tipoProducto1.getIdProductType() + ": ".concat(tipoProducto1.getName()));
		}
		
		
		Integer tipoProductoID = 0;
		String name = null;
		String description = null;
		Integer stock = null;
		Double price = null;
		
		Integer userID = usuario.getIdUser();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("INGRESA EL TIPO DE PRODUCTO A INGRESAR");
		tipoProductoID = sc.nextInt();
		
		System.out.println("Ingresa el nombre del producto");
		name = sc.next();
		
		System.out.println("Ingresa la descripcion");
		description = sc.next();
		
		System.out.println("Ingresa el stock");
		stock = sc.nextInt();
		
		System.out.println("Ingresa el precio");
		price = sc.nextDouble();
		
		
		
		System.out.println("Ingresa 1 si esta activo o 2 para inactivo");
		Integer idStatus = sc.nextInt();
		
		return service.crearProducto(userID, tipoProductoID, name, stock, price, description, idStatus);

	}

}






