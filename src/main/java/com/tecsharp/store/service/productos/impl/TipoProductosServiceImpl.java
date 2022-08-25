package com.tecsharp.store.service.productos.impl;

import java.util.Iterator;
import java.util.List;

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

}
