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
	public boolean agregarProductoAlCarrito(Integer productoID, Integer idUser) {
		
		ProductosServiceImpl service = new ProductosServiceImpl();
			
	    LocalDateTime fecha = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String fechaFormateada = fecha.format(myFormatObj);
		
		String query = "INSERT INTO cart VALUES (0,1,1,?,?,1,?,?);";
		boolean enCarrito = false;
		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query)) {

			
			statement.setString(1, fechaFormateada);
			statement.setString(2, fechaFormateada);
			statement.setInt(3, idUser);
			statement.setInt(4, productoID);
					
			if(statement.executeUpdate() == 1) {
				enCarrito = true;
			} else {
				System.out.println("No se pudo agregar al carrito");
				enCarrito = false;
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		
		return service.validaProductoCarritoAgregado(enCarrito) ;
	}

	@Override
	public List<Producto> getProductos(Usuario usuario) {
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
				carrito.add(producto);
				
				//System.out.println(producto);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return carrito;
	}

}
