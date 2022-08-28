package com.tecsharp.store.controllers.productos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tecsharp.store.StoreMain;
import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;
import com.tecsharp.store.service.productos.impl.ProductosServiceImpl;
import com.tecsharp.store.service.users.impl.UsuariosServiceImpl;
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

			int i = 1;
			for (Producto productos1 : productos) {
				System.out.println(i++ + ": ".concat(productos1.getName()));
			}

			idProducto = sc.nextInt();
			Producto productoRecuperado = productos.get(idProducto - 1);

			producto = service.validaProductoID(productoRecuperado.getIdProduct(), productos); // Envia entero y lista
																								// productos

			Utilidad.clearScreen();

		}
		return producto;

	}

	public Producto mostrarArticuloSeleccionado(Producto producto, TipoProducto tipoProducto) {

		Scanner sc = new Scanner(System.in);
		ProductosServiceImpl service = new ProductosServiceImpl();
		List<Producto> productos = service.getProducto(tipoProducto);

		Integer agregarOpcion = null;
		while (agregarOpcion == null) {

			System.out.println("ARTICULO SELECCIONADO:");
			System.out.println("Nombre: " + producto.getName());
			System.out.println("Stock disponible: " + producto.getStock());
			System.out.println("Precio: " + producto.getPrice());
			System.out.println("Caracteristicas: " + producto.getDescription());

			System.out.println("Presiona 1 para agregar al carrito");
			System.out.println("Presiona cualquier tecla para regresar");
			agregarOpcion = sc.nextInt();
			if (agregarOpcion == 1) {

				return producto;
			} else {
				return null;
			}
		}
		return null;

	}

	public boolean agregarAlCarritoVista(Producto producto, Usuario usuario) {
		Scanner sc = new Scanner(System.in);
		Integer agregarOpcion = null;
		while (agregarOpcion == null) {
			ProductosServiceImpl carritoData = new ProductosServiceImpl();

			String productoNombre = producto.getName();
			Integer productoID = producto.getIdProduct();
			Integer idUser = usuario.getIdUser();

			System.out.println("Has agregado: " + productoNombre + " al carrito.");

			System.out.println("Presiona 1 para confirmar");
			System.out.println("Presiona cualquier tecla para rechazar");
			agregarOpcion = sc.nextInt();

			if (agregarOpcion == 1) {
				return carritoData.agregarCarritoByIdUser(productoID, idUser);
			} else {
				System.out.println("Regresando al inicio");
				return false;
			}

		}

		return false;
	}

	public boolean validarAgregarCarrito(boolean enCarrito) {

		if (enCarrito = true) {
			System.out.println("El producto se agrego correctamente xd");
		} else {
			System.out.println("El producto no se agrego al carrito. Intenta de nuevo.");
		}
		return enCarrito;

	}

	public List<Producto> verCarrito (Usuario usuario) {

		
		Integer option = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("0. Ver carrito");
		option = sc.nextInt();
		
		if(option == 0) {
			
			Integer idProducto = null;
			Producto producto = null;
			while (producto == null) {
				
				System.out.println("Carrito de " + usuario.getNameUser());
				ProductosServiceImpl service = new ProductosServiceImpl();
				List<Producto> productos = service.verCarrito(usuario);

				int i = 1;
				for (Producto productos1 : productos) {
					System.out.println(i++ + ": ".concat(productos1.getName()));
				}
				
				System.out.println("Presiona: ");
				System.out.println("1. Comprar");
				System.out.println("2. Cancelar");
				
				option = sc.nextInt();
				if(option == 1) {
					System.out.println("Pedido comprado");
				} else {
					System.out.println("Pedido cancelado");
					TipoProductosController tipoProductos = new TipoProductosController();
					TipoProducto tipoProducto = tipoProductos.getTypeProductID(usuario);
				}
				
				//idProducto = sc.nextInt();
				//Producto productoRecuperado = productos.get(idProducto - 1);
				//productos = service.validaProductoID(productoRecuperado.getIdProduct(), productos); // Envia entero y lista
																									// productos

				Utilidad.clearScreen();

			}
			
			
			
			
		} else {
			
		}
		return null;

	}
	


}
