package com.tecsharp.store.service.productos.impl;

import java.util.List;

import com.tecsharp.store.controllers.productos.ProductosController;
import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;
import com.tecsharp.store.repository.productos.impl.ProductosRepositoryImpl;
import com.tecsharp.store.service.productos.ProductosService;

public class ProductosServiceImpl implements ProductosService{

	@Override
	public List<Producto> getProducto(TipoProducto tipoProducto) {
		ProductosRepositoryImpl producto = new ProductosRepositoryImpl();
		return producto.getProductos(tipoProducto.getIdProductType());
	}

	@Override
	public Producto validaProductoID(Integer productoID, List<Producto> productos) {
		for (Producto producto : productos) {
			if(productoID.equals(producto.getIdProduct())) {
				return producto;
			}
		}
		return null;
	}

	@Override
	public boolean agregarCarritoByIdUser(Integer productoID, Integer idUser) {
	
		ProductosRepositoryImpl carritoData = new ProductosRepositoryImpl();
		return carritoData.agregarProductoAlCarrito(productoID, idUser);
	}

	
	@Override
	public boolean validaProductoCarritoAgregado(boolean enCarrito) {
		ProductosController productoController = new ProductosController();
		return productoController.validarAgregarCarrito(enCarrito);
	}

	@Override
	public List<Producto> verCarrito(Usuario usuario) {
		ProductosRepositoryImpl carrito = new ProductosRepositoryImpl();
		
		return carrito.getCarrito(usuario);
				
	}

	@Override
	public List<Producto> comprarCarrito(List<Producto> productos, Usuario usuario, Integer numItems) {
		ProductosRepositoryImpl producto = new ProductosRepositoryImpl();
		return producto.comprarCarrito(productos, usuario, numItems);
	}

	@Override
	public Integer validarNumeroItems(List<Producto> productos, Usuario usuario) {
		ProductosRepositoryImpl producto = new ProductosRepositoryImpl();
		return producto.validarNumeroItems(productos, usuario);
	}


}
