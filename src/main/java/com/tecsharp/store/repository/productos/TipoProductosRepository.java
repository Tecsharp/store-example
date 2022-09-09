package com.tecsharp.store.repository.productos;

import java.util.List;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;

public interface TipoProductosRepository {

	public List<TipoProducto> getTipoProductos();
	
	public boolean crearProducto(Integer userID, Integer tipoProductoID, String name, Integer stock, Double price, String description, Integer idStatus);
	
	public boolean modificarInfoProducto (Usuario usuario, Integer opcionACambiar, Integer productoID, String nuevoDato);
}
