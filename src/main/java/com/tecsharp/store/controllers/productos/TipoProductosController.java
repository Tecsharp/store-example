package com.tecsharp.store.controllers.productos;

import java.util.List;
import java.util.Scanner;

import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.service.productos.impl.TipoProductosServiceImpl;
import com.tecsharp.store.utils.Utilidad;

public class TipoProductosController {

	public TipoProducto getTypeProductID() {

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

			idTipoProducto = sc.nextInt();
			tipoProducto = service.validaTipoProductoID(idTipoProducto, tipoProductos);
			Utilidad.clearScreen();

		}

		return tipoProducto;
	}

}
