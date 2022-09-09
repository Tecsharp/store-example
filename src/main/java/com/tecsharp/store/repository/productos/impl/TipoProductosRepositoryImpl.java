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
import com.tecsharp.store.entity.usuarios.Usuario;
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
	public boolean crearProducto(Integer userID, Integer tipoProductoID, String name, Integer stock, Double price,
			String description, Integer idStatus) {

		// CREAR PRODUCTO
		boolean registroExitoso = false;
		LocalDateTime fecha = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaFormateada = fecha.format(myFormatObj);

		String query = "INSERT INTO products VALUES (0, ?,?,?,?,?,?,?,?,?,? )";

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
			// ventaHecha = true;

		}

		catch (Exception e) {
			e.printStackTrace();
			return registroExitoso == false;
		}

		return registroExitoso;
	}

	@Override
	public boolean modificarInfoProducto(Usuario usuario, Integer opcionACambiar, Integer productoID,
			String nuevoDato) {

		String query = null;
		switch (opcionACambiar) {
		case 1:
			// nuevoDato = nuevoDato;
			query = "UPDATE products SET name = ? WHERE id_product = ?"; // NOMBRE
			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setString(1, nuevoDato);
				statement.setInt(2, productoID); // Correcto
				statement.executeUpdate();

			}

			catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			break;
		case 2:

			query = "UPDATE products SET stock = ? WHERE id_product = ?"; // STOCK
			Integer nuevoStock = Integer.parseInt(nuevoDato);
			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setInt(1, nuevoStock);
				statement.setInt(2, productoID); // Correcto
				statement.executeUpdate();

			}

			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			break;
		case 3:
			query = "UPDATE products SET price = ? WHERE id_product = ?"; // PRECIO
			Double nuevoPrecio = Double.parseDouble(nuevoDato);

			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setDouble(1, nuevoPrecio);
				statement.setInt(2, productoID); // Correcto
				statement.executeUpdate();

			}

			catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			break;
		case 4:
			query = "UPDATE products SET description = ? WHERE id_product = ?"; // CARACTERISTICA

			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setString(1, nuevoDato);
				statement.setInt(2, productoID); // Correcto
				statement.executeUpdate();

			}

			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			break;
		case 5:
			Integer nuevoStatus = Integer.parseInt(nuevoDato);
			query = "UPDATE products SET id_status = ? WHERE id_product = ?"; // ACTIVO
			try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
					PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setInt(1, nuevoStatus);
				statement.setInt(2, productoID); // Correcto
				statement.executeUpdate();

			}

			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			break;
		default:
			break;
		}

		// ACTUALIZA EL STOCK
		// String query = "UPDATE products SET stock = ? WHERE id_product = ?";

		return false;
	}

}
