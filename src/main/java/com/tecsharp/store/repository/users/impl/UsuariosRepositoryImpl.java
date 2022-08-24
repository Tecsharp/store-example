package com.tecsharp.store.repository.users.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.tecsharp.store.repository.users.UsuariosRpository;
import com.tecsharp.store.MysqlConnector;


public class UsuariosRepositoryImpl implements UsuariosRpository {

	private boolean isLogged;
	private boolean isAdmin;
	private ResultSet resultado;

	@Override
	public boolean getUserclient(String username, String password) {
		MysqlConnector conn = new MysqlConnector();
		try {

			
			resultado = conn.consultarRegistros(
					"SELECT name_usr, username, passwd, user_type FROM users \n"
					+ "WHERE username = '" + username + "'");
			if (resultado.next()) {
				String usr = resultado.getString("username");
				String pswd = resultado.getString("passwd");
				Integer validaAdmin = resultado.getInt("user_type");
				//String nombre_cliente = resultado.getString("nombre_persona");

				if (usr.equals(username) && pswd.equals(password)) {
					isLogged = true;
					if (validaAdmin == 2) {
						System.out.println("Conectado como admin");
						isAdmin = true;
					}

				} else {
					System.out.println("El usuario es incorrecto");
					isLogged = false;
					conn.desconectar();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isLogged && isAdmin;
	}

}
