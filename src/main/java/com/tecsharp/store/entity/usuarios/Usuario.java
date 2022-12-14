package com.tecsharp.store.entity.usuarios;

import java.util.Date;

import lombok.Data;

@Data
public class Usuario {
	
	private Integer idUser;
	private String username;
	private String nameUser;
	private String lastNameUsr;
	private String password;
	private String email;
	private TipoUsuario userType;
	private String description;
	private Integer userCreate;
	private Integer userUpdate;
	private Date dateCreate;
	private Date dateUpdate;
	private Integer status;
	

}
