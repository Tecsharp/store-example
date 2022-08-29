package com.tecsharp.store.repository.productos;

import java.util.List;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;

public interface ProductosRpository {
	
	List<Producto> getProductos(Integer tipoProductoID);
	
	public boolean agregarProductoAlCarrito(Integer producto, Integer usuario);
	
	//public Producto getCarrito (Usuario usuario);

	//List<Producto> getProductos(Usuario usuario);

	public List<Producto> comprarCarrito(List<Producto>productos, Usuario usuario, Integer numItems);

	List<Producto> getCarrito(Usuario usuario);
	
	public Integer validarNumeroItems (List<Producto> productos, Usuario usuario);

	//Integer verCarrito(Integer idUser);
	
}
