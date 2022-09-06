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
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.repository.productos.TipoProductosRepository;
import com.tecsharp.store.utils.Constantes;

public class TipoProductosRepositoryImpl implements TipoProductosRepository {

	@Override
	public List<TipoProducto> getTipoProductos() {
	
		List<TipoProducto> tipoProductos = new ArrayList<>();
		
		String query = "SELECT * FROM product_type";

		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query)) {
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				TipoProducto tipoProducto = new TipoProducto();
				tipoProducto.setIdProductType(result.getInt("id_product_type"));
				tipoProducto.setName(result.getString("name"));
				tipoProducto.setDescription(result.getString("description"));
				tipoProducto.setDateCreate(result.getDate("date_create"));
				tipoProducto.setDateUpdate(result.getDate("date_update"));
				tipoProductos.add(tipoProducto);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return tipoProductos;
	}

	@Override
	public boolean crearProducto(Integer userID, Integer tipoProductoID, String name, Integer stock, Double price, String description,
			Integer idStatus) {
		
		
		//CREAR PRODUCTO
		boolean registroExitoso = false;
		LocalDateTime fecha = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaFormateada = fecha.format(myFormatObj);

		String query = "INSERT INTO products VALUES (0, ?,?,?,?,?,?,?,?,?,? )";
		boolean enCarrito = false;
		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query)) {
			
			
			statement.setString(1, name); // Correcto
			statement.setInt(2, stock);
			statement.setDouble(3, price);
			statement.setString(4, description);
			statement.setInt(5, tipoProductoID); // Correcto
			statement.setInt(6, userID);
			statement.setInt(7, userID); 
			statement.setString(8, fechaFormateada); 
			statement.setString(9, fechaFormateada); 
			statement.setInt(10, idStatus);

			
			statement.executeUpdate();
			registroExitoso = true;
			//ventaHecha = true;

		}

		catch (Exception e) {
			e.printStackTrace();
			return registroExitoso == false;
		}

		return registroExitoso;
	}

}
