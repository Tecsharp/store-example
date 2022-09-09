package com.tecsharp.store.repository.ventas.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tecsharp.store.entity.ventas.Venta;
import com.tecsharp.store.repository.ventas.VentasRpository;
import com.tecsharp.store.utils.Constantes;

public class VentasRepositoryImpl implements VentasRpository{

	@Override
	public List<Venta> getVentas(Integer userId) {
		
		List<Venta> ventas = new ArrayList<>();
		
		String query = "SELECT * FROM sales \n"
				+ "INNER JOIN products ON products.id_product = sales.id_product \n"
				+ "WHERE id_user = ?;";

		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, userId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Venta venta = new Venta();
				venta.setIdSale(result.getInt("id_sale"));
				venta.setNameProduct(result.getString("name"));
				venta.setNumItems(result.getInt("num_items"));
				venta.setDateCreate(result.getDate("date_create"));
				ventas.add(venta);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		
		return ventas;
	}

}
