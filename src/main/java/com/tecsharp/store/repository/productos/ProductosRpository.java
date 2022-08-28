package com.tecsharp.store.repository.productos;

import java.util.List;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;

public interface ProductosRpository {
	
	List<Producto> getProductos(Integer tipoProductoID);
	
	public Producto agregarProductoAlCarrito(Integer producto, Integer usuario);
}
