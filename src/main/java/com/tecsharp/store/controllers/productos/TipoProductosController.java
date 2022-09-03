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
				crearProductoVista();
			}

			tipoProducto = service.validaTipoProductoID(idTipoProducto, tipoProductos);
			Utilidad.clearScreen();

		}

		return tipoProducto;
	}

	public Producto crearProductoVista() {

		TipoProductosServiceImpl service = new TipoProductosServiceImpl();
		List<TipoProducto> tipoProductos = service.getTipoProductos();

		for (TipoProducto tipoProducto1 : tipoProductos) {
			System.out.println(tipoProducto1.getIdProductType() + ": ".concat(tipoProducto1.getName()));
		}
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nINGRESA EL TIPO DE PRODUCTO A INGRESAR");
		Integer tipoProductoID = sc.nextInt();
		
		System.out.println("\nIngresa el nombre del producto");
		String name = sc.next();
		
		System.out.println("\nIngresa el stock");
		Integer stock = sc.nextInt();
		
		System.out.println("\nIngresa el precio");
		Double price = sc.nextDouble();
		
		System.out.println("\nIngresa la descripcion");
		String description = sc.next();
		
		System.out.println("\nIngresa 1 si esta activo o 2 para inactivo");
		Integer idStatus = sc.nextInt();
		
		return service.crearProducto(tipoProductoID, name, stock, price, description, idStatus);

	}

}






