package com.tecsharp.store.repository.productos.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public boolean agregarProductoAlCarrito(Integer productoID, Integer idUser, Integer numItems) {
		
		

		ProductosServiceImpl service = new ProductosServiceImpl();

		LocalDateTime fecha = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaFormateada = fecha.format(myFormatObj);

		String query = "INSERT INTO cart VALUES (0,1,1,?,?,1,?,?,?)";
		boolean enCarrito = false;
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
	public List<Producto> comprarCarrito(List<Producto> productos, Usuario usuario) {

		System.out.println("Seleccionando idSale");
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

		Integer idUser = usuario.getIdUser();
		Integer idProduct = null;
		Integer numItems = null;
		idSale = idSale + 1;
		

		for (Producto producto : productos) {
			idProduct = producto.getIdProduct();
			numItems = producto.getNumItems();
			System.out.println("REALIZANDO INSERT");
			System.out.println("LOGS");
			System.out.println("idUser: " + idUser);
			System.out.println("idSale: " + idSale); // log
			System.out.println("userCreate: " + idUser);
			System.out.println("userUpdate: " + idUser);
			System.out.println("idProduct: " + idProduct); // log
			System.out.println("numItems: " + numItems);
			
			LocalDateTime fecha = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String fechaFormateada = fecha.format(myFormatObj);

			String query = "INSERT INTO sales VALUES (?,'Productos vendido',?,?,?,?,?,?,?,1);";
			boolean enCarrito = false;
			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setInt(1, idSale); // Correcto
				statement.setInt(2, idUser); // Correcto
				statement.setInt(3, idProduct);
				statement.setInt(4, numItems);
				statement.setInt(5, idUser);
				statement.setInt(6, idUser);
				statement.setString(7, fechaFormateada);
				statement.setString(8, fechaFormateada);
				
				statement.executeUpdate();

			}
			

			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			
		}

		return productos;

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
			
			//System.out.println("Nombre del producto: " + producto.getName());
			//System.out.println("ID del producto: " + idProduct);
			System.out.println("Numero de veces en carrito: " + numCountProduct);
		}

		return null;

	}

}
