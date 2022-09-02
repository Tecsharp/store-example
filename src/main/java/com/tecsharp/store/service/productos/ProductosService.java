package com.tecsharp.store.service.productos;

import java.util.List;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;
import com.tecsharp.store.repository.productos.impl.ProductosRepositoryImpl;


public interface ProductosService {
	
	public List<Producto> getProducto(TipoProducto tipoProducto);
	
	public Producto validaProductoID(Integer productoID, List<Producto>productos);
	
	public boolean agregarCarritoByIdUser (Integer productoID, Integer idUser, Integer numItems, boolean productoDuplicado);
	
	
	public boolean validaProductoCarritoAgregado(boolean enCarrito);
	
	public List<Producto> verCarrito(Usuario usuario);

	public boolean comprarCarrito(List<Producto> productos, Usuario usuario); 

	public Integer validarNumeroItems (List<Producto> productos, Usuario usuario);

	

	public boolean carritoProductoEsIgual(Integer productoID, Usuario usuario, Integer numItems);

	
	
}
