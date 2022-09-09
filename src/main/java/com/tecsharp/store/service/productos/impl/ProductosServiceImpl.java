package com.tecsharp.store.service.productos.impl;

import java.util.List;

import com.tecsharp.store.controllers.productos.ProductosController;
import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.entity.usuarios.Usuario;
import com.tecsharp.store.repository.productos.impl.ProductosRepositoryImpl;
import com.tecsharp.store.service.productos.ProductosService;

public class ProductosServiceImpl implements ProductosService {

	@Override
	public List<Producto> getProducto(TipoProducto tipoProducto) {
		ProductosRepositoryImpl producto = new ProductosRepositoryImpl();
		return producto.getProductos(tipoProducto);
	}

	@Override
	public Producto validaProductoID(Integer productoID, List<Producto> productos) {
		for (Producto producto : productos) {
			if (productoID.equals(producto.getIdProduct())) {
				return producto;
			}
		}
		return null;
	}

	@Override
	public boolean agregarCarritoByIdUser(Integer productoID, Integer idUser, Integer numItems,
			boolean productoDuplicado) {

		ProductosRepositoryImpl carritoData = new ProductosRepositoryImpl();
		return carritoData.agregarProductoAlCarrito(productoID, idUser, numItems, productoDuplicado);
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
	public boolean comprarCarrito(List<Producto> productos, Usuario usuario) {
		ProductosRepositoryImpl producto = new ProductosRepositoryImpl();

		return producto.comprarCarrito(productos, usuario);
	}

	@Override
	public Integer validarNumeroItems(List<Producto> productos, Usuario usuario) {
		ProductosRepositoryImpl producto = new ProductosRepositoryImpl();
		return producto.validarNumeroItems(productos, usuario);
	}

	@Override
	public boolean carritoProductoEsIgual(Integer productoID, Usuario usuario, Integer numItems) {
		ProductosRepositoryImpl producto = new ProductosRepositoryImpl();

		List<Producto> listCarrito = producto.getCarrito(usuario);

		for (Producto producto1 : listCarrito) {

			if (producto1.getIdProduct() == productoID) {
				System.out.println("Producto duplicado");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean limpiarCarrito(Usuario usuario) {
		ProductosRepositoryImpl carrito = new ProductosRepositoryImpl();
		return carrito.limpiarCarrito(usuario);
	}

}
