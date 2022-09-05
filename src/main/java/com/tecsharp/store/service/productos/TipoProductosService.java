package com.tecsharp.store.service.productos;

import java.util.List;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;

public interface TipoProductosService {

	public List<TipoProducto> getTipoProductos();
	
	public TipoProducto validaTipoProductoID(Integer idTipoProducto, List<TipoProducto>tipoProductos);
	
	public Producto crearProducto (Integer userID, Integer tipoProductoID, String name, Integer stock, Double price, String description, Integer idStatus);


}
