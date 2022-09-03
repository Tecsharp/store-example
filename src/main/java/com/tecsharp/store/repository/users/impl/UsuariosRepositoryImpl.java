package com.tecsharp.store.repository.users.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import com.tecsharp.store.entity.usuarios.Usuario;
import com.tecsharp.store.repository.users.UsuariosRpository;
import com.tecsharp.store.utils.Constantes;

public class UsuariosRepositoryImpl implements UsuariosRpository {

	@Override
	public Usuario findById(Integer userId) {

		Usuario usuario = null;
		String query = "SELECT name_usr, username, passwd, user_type FROM users WHERE id_user = ?";

		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				usuario = new Usuario();
				usuario.setNameUser(result.getString("name_usr"));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	}

	@Override
	public Usuario findByUsernameAndPassword(String username, String password) {
		
		Usuario usuario = null;
		String query = "SELECT * FROM users WHERE username = ? AND passwd = ?";

		try (Connection connection = DriverManager.getConnection(Constantes.DB_PROPERTIES);
				PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				usuario = new Usuario();
				usuario.setIdUser(result.getInt("id_user"));
				usuario.setNameUser(result.getString("name_usr"));
				usuario.setLastNameUsr(result.getString("lastname_usr"));
				usuario.setUserType(result.getInt("user_type"));
				usuario.setUsername(result.getString("username"));
				usuario.setPassword(result.getString("passwd"));
				usuario.setEmail(result.getString("emailusr"));
				usuario.setDateCreate(result.getDate("date_create"));
				usuario.setDateUpdate(result.getDate("date_update"));
				usuario.setStatus(result.getInt("id_status"));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	}


	
}
