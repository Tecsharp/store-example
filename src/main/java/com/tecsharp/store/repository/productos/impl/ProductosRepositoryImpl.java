package com.tecsharp.store.repository.productos.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.productos.TipoProducto;
import com.tecsharp.store.repository.productos.ProductosRpository;
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

}
