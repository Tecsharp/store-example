package com.tecsharp.store.service.productos.impl;


import java.util.List;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.repository.productos.impl.TipoProductosRepositoryImpl;
import com.tecsharp.store.service.productos.TipoProductosService;

public class TipoProductosServiceImpl implements TipoProductosService {

	@Override
	public List<TipoProducto> getTipoProductos() {
		TipoProductosRepositoryImpl tipoProductos = new TipoProductosRepositoryImpl();
		return tipoProductos.getTipoProductos();
	}

	@Override
	public TipoProducto validaTipoProductoID(Integer idTipoProducto, List<TipoProducto> tipoProductos) {
		
		for (TipoProducto tipoProducto : tipoProductos) {
			
			if(idTipoProducto.equals(tipoProducto.getIdProductType())) {
				return tipoProducto;
			}
		}
		
		return null;
	}

	@Override
	public Producto crearProducto(Integer userID, Integer tipoProductoID, String name, Integer stock, Double price, String description, Integer idStatus) {
		TipoProductosRepositoryImpl nuevosProductos = new TipoProductosRepositoryImpl();
		return nuevosProductos.crearProducto(userID, tipoProductoID, name, stock, price, description, idStatus);
	}

}
