package com.tecsharp.store.repository.productos.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import java.util.List;


import com.tecsharp.store.entity.productos.Producto;

import com.tecsharp.store.entity.usuarios.Usuario;
import com.tecsharp.store.repository.productos.ProductosRpository;
import com.tecsharp.store.service.productos.impl.ProductosServiceImpl;
import com.tecsharp.store.utils.Constantes;

public class ProductosRepositoryImpl implements ProductosRpository {

	@Override
	public List<Producto> getProductos(Integer tipoProducto) {
		List<Producto> productos = new ArrayList<>();

		String query = "SELECT * FROM products WHERE product_type = ?";

		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, tipoProducto);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Producto producto = new Producto();
				producto.setIdProduct(result.getInt("id_product"));
				producto.setName(result.getString("name"));
				producto.setStock(result.getInt("stock"));
				producto.setPrice(result.getDouble("price"));
				producto.setDescription(result.getString("description"));
				producto.setDateCreate(result.getDate("date_Create"));
				producto.setDateUpdate(result.getDate("date_update"));
				productos.add(producto);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return productos;
	}

	@Override
	public boolean agregarProductoAlCarrito(Integer productoID, Integer idUser, Integer numItems, boolean productoDuplicado) {
		ProductosServiceImpl service = new ProductosServiceImpl();
		
		boolean enCarrito = false;
		if (productoDuplicado == true) {
			actualizarCarritoPorProductoDuplicado(productoID, idUser, numItems);
			return service.validaProductoCarritoAgregado(enCarrito);
		} else {
			
			LocalDateTime fecha = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String fechaFormateada = fecha.format(myFormatObj);

			String query = "INSERT INTO cart VALUES (0,1,1,?,?,1,?,?,?)";
			
			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query)) {

				// ResultSet result = statement.executeQuery();

				statement.setString(1, fechaFormateada);
				statement.setString(2, fechaFormateada);
				statement.setInt(3, numItems);
				statement.setInt(4, idUser);
				statement.setInt(5, productoID);
				statement.executeUpdate();

			}

			catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			return service.validaProductoCarritoAgregado(enCarrito);
		}
		
		
	}
	
	
	
	
	@Override
	public List<Producto> getCarrito(Usuario usuario) {
		List<Producto> carrito = new ArrayList<>();

		Integer idUser = usuario.getIdUser();

		String query = "SELECT * FROM products INNER JOIN cart ON  cart.id_product = products.id_product WHERE id_user = ?";

		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, idUser);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Producto producto = new Producto();
				producto.setIdProduct(result.getInt("id_product"));
				producto.setName(result.getString("name"));
				producto.setStock(result.getInt("stock"));
				producto.setPrice(result.getDouble("price"));
				producto.setDescription(result.getString("description"));
				producto.setDateCreate(result.getDate("date_Create"));
				producto.setDateUpdate(result.getDate("date_update"));
				producto.setNumItems(result.getInt("num_items"));
				producto.setStatus(result.getInt("id_status"));
				carrito.add(producto);

				// System.out.println(producto);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return carrito;
	}

	@Override
	public boolean comprarCarrito(List<Producto> productos, Usuario usuario) {
		boolean ventaHecha = false;

		// System.out.println("Seleccionando idSale");
		// List<Producto> productos = new ArrayList<>();

		Integer idSale = null;
		String query1 = "SELECT MAX(id_sale) FROM sales";

		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query1)) {

			ResultSet result = statement.executeQuery();
			while (result.next()) {

				idSale = result.getInt("MAX(id_sale)");

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		int idProducto = 0;
		Producto idProductoRecuperado = productos.get(idProducto);

		// Integer idUser = usuario.getIdUser();
		Integer idProduct = null;
		Integer numItems = null;
		idSale = idSale + 1;

		for (Producto producto : productos) {
			// idProduct = producto.getIdProduct();
			// numItems = producto.getNumItems();

			LocalDateTime fecha = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String fechaFormateada = fecha.format(myFormatObj);

			String query = "INSERT INTO sales VALUES (?,'Productos vendido',?,?,?,?,?,?,?,1);";
			boolean enCarrito = false;
			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setInt(1, idSale); // Correcto
				statement.setInt(2, usuario.getIdUser()); // Correcto
				statement.setInt(3, producto.getIdProduct());
				statement.setInt(4, producto.getNumItems());
				statement.setInt(5, usuario.getIdUser());
				statement.setInt(6, usuario.getIdUser());
				statement.setString(7, fechaFormateada);
				statement.setString(8, fechaFormateada);
				statement.executeUpdate();

				ventaHecha = true;

			}

			catch (Exception e) {
				e.printStackTrace();
				return ventaHecha == false;
			}

		}

		limpiarCarrito(usuario);
		reducirStockPorCompra(productos);
		return ventaHecha;

	}

	public Integer validarNumeroItems(List<Producto> carrito, Usuario usuario) {

		Integer idUser = usuario.getIdUser();
		Integer idProduct = null;
		Integer numCountProduct = null;

		for (Producto producto : carrito) {

			idProduct = producto.getIdProduct();

			String query = "SELECT COUNT(id_product) FROM cart WHERE id_product = ?";

			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setInt(1, idProduct);

				ResultSet result = statement.executeQuery();

				while (result.next()) {

					numCountProduct = result.getInt("COUNT(id_product)");

					// System.out.println(producto);
				}
			}

			catch (Exception e) {
				e.printStackTrace();
			}

			// System.out.println("Nombre del producto: " + producto.getName());
			// System.out.println("ID del producto: " + idProduct);
			System.out.println("Numero de veces en carrito: " + numCountProduct);
		}

		return null;

	}

	@Override
	public boolean limpiarCarrito(Usuario usuario) {

		Integer idUser = usuario.getIdUser();
		String query = "DELETE FROM cart WHERE id_user = ?;";

		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, idUser); // Correcto

			statement.executeUpdate();

		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	@Override
	public boolean reducirStockPorCompra(List<Producto> productos) {

		String productName = null;
		Integer productID = null;
		Integer stockRestante = null;

		for (Producto producto : productos) {

			productName = producto.getName();
			productID = producto.getIdProduct();

			// OBTIENE EL STOCK DEL PRODUCTO
			String query = "SELECT stock FROM products WHERE id_product = ?";
			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setInt(1, productID); // Correcto

				ResultSet result = statement.executeQuery();

				while (result.next()) {

					stockRestante = result.getInt("stock");

					// System.out.println(producto);
				}

			}

			catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			Integer items = producto.getNumItems();
			// ACTUALIZA EL STOCK
			String query2 = "UPDATE products SET stock = ? WHERE id_product = ?";
			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query2)) {

				statement.setInt(1, stockRestante -items);
				statement.setInt(2, productID); // Correcto
				statement.executeUpdate();
				
			}

			catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}

		return false;
	}

	@Override
	public void actualizarCarritoPorProductoDuplicado(Integer productoID, Integer idUser, Integer numItems) {
		
		Integer nuevoNumItems = null;
		Integer itemsEnCarrito = null;
		String query = "SELECT num_items FROM cart WHERE id_product = ? AND id_user = ?";
		
		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, productoID);
			statement.setInt(2, idUser);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {

				itemsEnCarrito = result.getInt("num_items");

				// System.out.println(producto);
			}
			
			//statement.executeUpdate();
			
			
		}

		catch (Exception e) {
			e.printStackTrace();
			
		}
		
		nuevoNumItems = itemsEnCarrito + numItems;
		
		String query2 = "UPDATE cart SET num_items = ? WHERE id_product = ? AND id_user = ?";
		
		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query2)) {

			// ResultSet result = statement.executeQuery();

			statement.setInt(1, nuevoNumItems);
			statement.setInt(2, productoID);
			statement.setInt(3, idUser);
			statement.executeUpdate();

		}

		catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

//	@Override
//	public boolean carritoProductoEsIgual(Integer productoID, Usuario usuario, Integer numItems) {
//		List<Producto> listCarrito = getCarrito(usuario);
//		
//		
//		for (Producto producto : listCarrito) {
//			
//			if (producto.getIdProduct() == productoID) {
//				System.out.println("Producto duplicado");
//			}
//			
//		}
//		
//		return false;
//	}





}
