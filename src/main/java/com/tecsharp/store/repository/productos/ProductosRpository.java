package com.tecsharp.store.repository.productos;

import java.util.List;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;

public interface ProductosRpository {
	
	List<Producto> getProductos(Integer tipoProductoID);
	
	public boolean agregarProductoAlCarrito(Integer producto, Integer usuario, Integer numItems);
	
	//public Producto getCarrito (Usuario usuario);

	//List<Producto> getProductos(Usuario usuario);

	public boolean comprarCarrito(List<Producto>productos, Usuario usuario);

	List<Producto> getCarrito(Usuario usuario);
	
	public Integer validarNumeroItems (List<Producto> productos, Usuario usuario);

	public boolean limpiarCarrito (Usuario usuario);
	
	public boolean reducirStockPorCompra (List<Producto> productos);

	//Integer verCarrito(Integer idUser);
	
}
