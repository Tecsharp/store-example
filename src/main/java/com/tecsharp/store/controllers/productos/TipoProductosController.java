package com.tecsharp.store.controllers.productos;

import java.util.List;
import java.util.Scanner;

import com.tecsharp.store.controllers.ventas.VentasController;
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

			TipoProductosServiceImpl service = new TipoProductosServiceImpl();
			List<TipoProducto> tipoProductos = service.getTipoProductos();

			ProductosController verCarrito = new ProductosController();

			boolean isEntero = false;
			while (!isEntero) {
				try {
					System.out.println("SELECCIONA UN TIPO DE PRODUCTO\n");
					for (TipoProducto tipoProducto1 : tipoProductos) {
						System.out.println(tipoProducto1.getIdProductType() + ": ".concat(tipoProducto1.getName()));
					}

					System.out.println("");

					System.out.println("0. Ver carrito");
					System.out.println("8. Ver compras");
					System.out.println("9. Cerrar sesion");

					if (usuario.getUserType() == 2) {
						System.out.println("\n============ ADMIN ============");
						System.out.println("7. Herramientas de administrador");
					}
					Scanner sc = new Scanner(System.in);
					idTipoProducto = sc.nextInt();
					isEntero = true;
				} catch (Exception e) {
					System.out.println("Ingresa un digito numerico valido");
					Utilidad.clearScreen();
				}
			}

			if (idTipoProducto == 0) {
				Utilidad.clearScreen();
				verCarrito.verCarrito(usuario);

			} else if (idTipoProducto == 9) {
				Utilidad.clearScreen();
				// UsuariosController usuarios = new UsuariosController();

			} else if (idTipoProducto == 7) {
				Utilidad.clearScreen();
				herramientasDeAministrador(usuario);

			} else if (idTipoProducto == 8) {
				Utilidad.clearScreen();
				VentasController.verCompras(usuario.getIdUser());

			}

			tipoProducto = service.validaTipoProductoID(idTipoProducto, tipoProductos);

		}

		return tipoProducto;
	}

	public void crearProductoVista(Usuario usuario) {

		TipoProductosServiceImpl service = new TipoProductosServiceImpl();
		List<TipoProducto> tipoProductos = service.getTipoProductos();

		Integer tipoProductoID = null;
		String name = "";
		String description = null;
		Integer stock = null;
		Double price = null;
		Integer userID = usuario.getIdUser();

		Scanner sc = new Scanner(System.in);

		boolean isEntero = false;

		while (!isEntero) { // VALIDA QUE SEA UN ENTERO POSITIVO Y NO UNA LETRA
			try {
				Utilidad.clearScreen();
				System.out.println("INGRESA EL TIPO DE PRODUCTO A REGISTRAR");
				for (TipoProducto tipoProducto1 : tipoProductos) {
					System.out.println(tipoProducto1.getIdProductType() + ": ".concat(tipoProducto1.getName()));
				}

				Scanner sc2 = new Scanner(System.in);

				tipoProductoID = sc2.nextInt();

				if (tipoProductoID > 0) {
					isEntero = true;
				} else {
				}
			} catch (Exception e) {
				isEntero = false;
				Utilidad.clearScreen();
				System.out.println("AGREGA UN NUMERO VALIDO");

			}
		}

		System.out.println("Ingresa el nombre del producto");
		name = sc.nextLine();

		System.out.println("Ingresa la descripcion");
		description = sc.nextLine();

		isEntero = false;
		while (!isEntero) { // VALIDA QUE SEA UN ENTERO POSITIVO Y NO UNA LETRA
			try {
				Scanner sc2 = new Scanner(System.in);
				System.out.println("Ingresa el stock");
				stock = sc2.nextInt();

				if (stock > 0) {
					isEntero = true;
				} else {
				}
			} catch (Exception e) {
				isEntero = false;
				System.out.println("AGREGA UN NUMERO VALIDO");
			}
		}

		System.out.println("Ingresa el precio");
		price = sc.nextDouble();

		System.out.println("Ingresa 1 si esta activo o 2 para inactivo");
		Integer idStatus = sc.nextInt();

		// AGREGAR UN IF A service.crearProducto

		if (service.crearProducto(userID, tipoProductoID, name, stock, price, description, idStatus)) {
			System.out.println("EL PRODUCTO SE HA REGISTRADO CORRECTAMENTE");
			System.out.println("PRESIONA ENTER PARA CONTINUAR");

			try {
				System.in.read();
			} catch (Exception e) {
			}
		} else {
			System.out.println("HUBO UN ERROR AL REGISTRAR EL PRODUCTO");
			System.out.println("PRESIONA ENTER PARA CONTINUAR");

			try {
				System.in.read();
			} catch (Exception e) {
			}
		}

	}

	public void herramientasDeAministrador(Usuario usuario) {
		System.out.println("Herramientas de administrador:\n");
		System.out.println("1. Crear producto");
		System.out.println("2. Modificar producto");
		//System.out.println("3. Activar o desactivar producto");
		Producto producto = null;
		TipoProducto tipoProducto = null;
		Scanner sc = new Scanner(System.in);
		Integer option = sc.nextInt();
		switch (option) {
		case 1:
			crearProductoVista(usuario);
			break;
		case 2:
			modificarProducto(usuario);

//			tipoProducto = getTipoProductoParaModificar(usuario); // Obtiene la lista de tipo productos
//			
//			producto = modificarProducto(tipoProducto); // Obtiene la lista de productos

			break;

		default:
			break;
		}

	}

	public void modificarProducto(Usuario usuario) {
		Object isProducto = null;
		while (isProducto == null) {
			// INSTANCIAS //
			TipoProductosController tipoProductos = new TipoProductosController();
			ProductosController productos = new ProductosController();
			Producto producto = null;
			TipoProducto tipoProducto = null;
			while (producto == null) {
				tipoProducto = getTipoProductoParaModificar(usuario); // Obtiene la lista de

				producto = productos.getProductos(tipoProducto); // Obtiene la lista de productos

			}
			if (productos.mostrarArticuloSeleccionado(usuario, producto, tipoProducto) != null) {
				modificarProducto(producto, usuario);

			} else {
				System.out.println("Regresando al inicio");
			}
		}
	}

	public TipoProducto getTipoProductoParaModificar(Usuario usuario) {
		TipoProductosServiceImpl service = new TipoProductosServiceImpl();
		List<TipoProducto> tipoProductos = service.getTipoProductos();
		TipoProducto tipoProducto = null;

		while (tipoProducto == null) {
			Utilidad.clearScreen();

			System.out.println("TIPO DE PRODUCTOS\n");
			for (TipoProducto tipoProducto2 : tipoProductos) {
				Integer idTipoProducto = tipoProducto2.getIdProductType();
				String nombreTipoProducto = tipoProducto2.getName();
				System.out.println(idTipoProducto + ": ".concat(nombreTipoProducto));

			}

			System.out.println("\nSELECCIONA EL TIPO DE PRODUCTO A MODIFICAR");

			System.out.println("");
			Scanner sc = new Scanner(System.in);
			Integer idTipoProducto = sc.nextInt();

			tipoProducto = service.validaTipoProductoID(idTipoProducto, tipoProductos);

		}
		return tipoProducto;
	}

	public Producto modificarProducto(Producto producto, Usuario usuario) {

		System.out.println("MODIFICANDO: >> " + producto.getName());

		System.out.println("INFORMACION A EDITAR:");
		System.out.println("1. Nombre: " + producto.getName());
		System.out.println("2. Stock: " + producto.getStock());
		System.out.println("3. Precio: " + producto.getPrice());
		System.out.println("4. Caracteristicas: " + producto.getDescription());
		System.out.println("5. Status: " + producto.getStatus());
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		System.out.println("SELECCIONA CAMPO A EDITAR");
		Integer opcionACambiar = sc.nextInt();

		Integer productoID = producto.getIdProduct();
		TipoProductosServiceImpl service = new TipoProductosServiceImpl();
		String nuevoDato = null;
		switch (opcionACambiar) {
		case 1:

			System.out.println("INTRODUCE EL NUEVO NOMBRE");
			Scanner nombre = new Scanner(System.in);
			nuevoDato = nombre.nextLine();

			break;
		case 2:

			System.out.println("INTRODUCE EL NUEVO STOCK");
			Scanner stock = new Scanner(System.in);
			nuevoDato = stock.nextLine();

			break;
		case 3:
			System.out.println("INTRODUCE EL NUEVO PRECIO");
			Scanner precio = new Scanner(System.in);
			nuevoDato = precio.nextLine();
			break;

		case 4:
			System.out.println("INTRODUCE LA NUEVA DESCRIPCION");
			Scanner descripcion = new Scanner(System.in);
			nuevoDato = descripcion.nextLine();
			break;
		case 5:
			System.out.println("ACTUALIZA EL STATUS");
			System.out.println("0. DESACTIVADO\n1. ACTIVADO  ");
			Scanner status = new Scanner(System.in);
			nuevoDato = status.nextLine();
			break;

		default:
			break;
		}

		service.modificarInfoProducto(usuario, opcionACambiar, productoID, nuevoDato);
		return null;

	}

}
