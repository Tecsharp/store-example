package com.tecsharp.store.controllers.productos;

import java.util.List;
import java.util.Scanner;

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
			System.out.println("SELECCIONA UN TIPO DE PRODUCTO");
			TipoProductosServiceImpl service = new TipoProductosServiceImpl();
			List<TipoProducto> tipoProductos = service.getTipoProductos();

			for (TipoProducto tipoProducto1 : tipoProductos) {
				System.out.println(tipoProducto1.getIdProductType() + ": ".concat(tipoProducto1.getName()));
			}
			
			ProductosController verCarrito = new ProductosController();
			
			verCarrito.verCarrito(usuario);
					
			
			idTipoProducto = sc.nextInt();
			
			tipoProducto = service.validaTipoProductoID(idTipoProducto, tipoProductos);
			Utilidad.clearScreen();

		}

		return tipoProducto;
	}

}
