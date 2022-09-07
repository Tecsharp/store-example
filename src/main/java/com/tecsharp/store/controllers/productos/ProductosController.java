package com.tecsharp.store.controllers.productos;

import java.util.List;
import java.util.Scanner;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;

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

			String dispStock = null;
			int i = 1;
			for (Producto productos1 : productos) {

				if (productos1.getStock() >= 0 && productos1.getStatus() == 1) {
					dispStock = "  |=| Stock disponible: " + productos1.getStock();
					if (productos1.getStock() == 0) {
						dispStock = "  |=| Stock no disponible: ";
					}
				}

				System.out.println(i++ + ": ".concat(productos1.getName()).concat(dispStock));
			}

			
			idProducto = sc.nextInt();
			if(idProducto == 1) {
				Utilidad.esOpcionUnoCorrecto(idProducto);
			}
			
			Producto productoRecuperado = productos.get(idProducto - 1);

			producto = service.validaProductoID(productoRecuperado.getIdProduct(), productos); // Envia entero y lista
																								// productos

			Utilidad.clearScreen();

		}
		return producto;

	}

	public Producto mostrarArticuloSeleccionado(Producto producto, TipoProducto tipoProducto) {

		Scanner sc = new Scanner(System.in);
//		ProductosServiceImpl service = new ProductosServiceImpl();
//		List<Producto> productos = service.getProducto(tipoProducto);

		Integer agregarOpcion = null;
		while (agregarOpcion == null) {

			System.out.println("ARTICULO SELECCIONADO:\n");
			System.out.println("Nombre: " + producto.getName());
			System.out.println("Stock disponible: " + producto.getStock());
			System.out.println("Precio: " + producto.getPrice());
			System.out.println("Caracteristicas: " + producto.getDescription());
			System.out.println("");

			System.out.println("1. SELECCIONAR ARTICULO");
			System.out.println("Presiona cualquier tecla para regresar");

			boolean isEntero = false;
			while (!isEntero) { // VALIDA QUE SEA UN ENTERO POSITIVO Y NO UNA LETRA
				try {

					Scanner sc2 = new Scanner(System.in);
					agregarOpcion = sc2.nextInt();
					if (agregarOpcion == 1) {
						isEntero = true;
						if (producto.getStock() == 0) {
							System.out.println("PRODUCTO AGOTADO");
							System.out.println("PRESIONA ENTER PARA CONTINUAR");
							
							try {
								System.in.read();
							} catch (Exception e) {
							}
						}
						return producto;

					} else {
					}
				} catch (Exception e) {
					isEntero = false;
					agregarOpcion = null;
					System.out.println("AGREGA UN NUMERO VALIDO");
				}
			}

		}
		return null;

	}

	public boolean agregarAlCarritoVista(Producto producto, Usuario usuario) {

		Integer agregarOpcion = null;
		Integer numItems = null;
		while (agregarOpcion == null) {
			ProductosServiceImpl carritoData = new ProductosServiceImpl();

			String productoNombre = producto.getName();
			Integer productoID = producto.getIdProduct();
			Integer idUser = usuario.getIdUser();

			boolean validStock = false;
			while (!validStock) {

				System.out.println("Has seleccionado: " + productoNombre + "." + " (" + producto.getStock() + ")"
						+ " Unidades disponibles");
				System.out.println("INGRESE NUMERO DE UNIDADES (SOLO NUMEROS)");

				boolean isEntero = false;

				while (!isEntero) { // VALIDA QUE SEA UN ENTERO POSITIVO Y NO UNA LETRA
					try {
						Scanner sc = new Scanner(System.in);
						System.out.println("INGRESA LA CANTIDAD DE UNIDADES");
						numItems = sc.nextInt();
						if (numItems > 0) {
							isEntero = true;
						} else {
						}
					} catch (Exception e) {
						isEntero = false;
						System.out.println("AGREGA UN NUMERO VALIDO");
					}
				}

				if (numItems > producto.getStock()) {
					System.out.println("NO HAY UNIDADES SUFICIENTES\n");
					System.out.println("PRESIONA ENTER PARA CONTINUAR");
					try {
						System.in.read();
					} catch (Exception e) {
					}
				} else if (numItems <= producto.getStock() && numItems > 0) {
					validStock = true;
				} else {
					System.out.println("INRGESA EL DIGITO CORRECTAMENTE");
					System.out.println("PRESIONA ENTER PARA CONTINUAR");
					try {
						System.in.read();
					} catch (Exception e) {
					}
				}

			}
			System.out.println("Ingresa 1 para confirmar");
			System.out.println("Presiona cualquier tecla para rechazar");
			Scanner sc = new Scanner(System.in);
			agregarOpcion = sc.nextInt();
			sc.close();
			boolean productoDuplicado = false;
			if (agregarOpcion == 1) {

				if (carritoProductoEsIgual(productoID, usuario, numItems) == true) {
					productoDuplicado = true;
					return carritoData.agregarCarritoByIdUser(productoID, idUser, numItems, productoDuplicado);
				}
				return carritoData.agregarCarritoByIdUser(productoID, idUser, numItems, productoDuplicado);

			} else {
				System.out.println("Regresando al inicio");
				return false;
			}

		}

		return false;
	}

	public boolean carritoProductoEsIgual(Integer productoID, Usuario usuario, Integer numItems) {

		ProductosServiceImpl carritoData = new ProductosServiceImpl();
		if (carritoData.carritoProductoEsIgual(productoID, usuario, numItems) == true) {
			return true;
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

	public void verCarrito(Usuario usuario) {

		Integer option = null;

		Scanner sc = new Scanner(System.in);

		Integer idProducto = null;
		Producto producto = null;
		boolean onCart = true;
		while (onCart) {

			System.out.println("Carrito de " + usuario.getNameUser());

			ProductosServiceImpl service = new ProductosServiceImpl();

			List<Producto> productos = service.verCarrito(usuario);

			Integer numList = 1;
			Integer carritoVacio = 0;
			String isDisponible = null;
			for (Producto productos1 : productos) {

				if (productos1.getStock() >= 1) {
					isDisponible = "|=| Art. disponible";
				} else {
					isDisponible = "|=| Art. no disponible";
				}

				carritoVacio++;
				System.out.println(numList++ + ": ".concat(productos1.getName()) + " (" + productos1.getNumItems()
						+ ")  " + isDisponible);
			}

			if (carritoVacio < 1) {
				System.out.println("El carrito esta vacio");
			}

			System.out.println("");
			System.out.println("1. Comprar carrito");
			System.out.println("2. Limpiar carrito");
			System.out.println("3. Cancelar");

			option = sc.nextInt();
			if (option == 1) {
				option = null;
				System.out.println("Presiona 1 para confirmar compra\n2. Cancelar");
				option = sc.nextInt();
				if (option == 1) {
					if (service.comprarCarrito(productos, usuario)) {
						System.out.println("GRACIAS POR TU COMPRA");
						System.out.println("PRESIONA ENTER PARA CONTINUAR");
						try {
							System.in.read();
						} catch (Exception e) {
						}
					} else {
						System.out.println("TU COMPRA NO HA SIDO EFECTUADA\n");
						System.out.println("PRESIONA ENTER PARA CONTINUAR");
						try {
							System.in.read();
						} catch (Exception e) {
						}
					}
				} else {
					onCart = false;
				}

			} else if (option == 2) {
				service.limpiarCarrito(usuario);

			} else {

				System.out.println("Compra cancelada");
				onCart = false;
			}


			onCart = false;
		}

	}

}
