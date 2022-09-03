package com.tecsharp.store.repository.productos;

import java.util.List;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;

public interface TipoProductosRepository {

	public List<TipoProducto> getTipoProductos();
	
	public Producto crearProducto(Integer tipoProductoID, String name, Integer stock, Double price, String description, Integer idStatus);
	
	
}
