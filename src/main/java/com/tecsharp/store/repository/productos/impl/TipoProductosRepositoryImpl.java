package com.tecsharp.store.repository.productos.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

}
