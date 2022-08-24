package com.tecsharp.store.entity.usuarios;

import java.util.Date;

import com.tecsharp.store.entity.estatus.Estatus;

import lombok.Data;

@Data
public class Usuario {
	
	private Integer nameUsr;
	private String lastNameUsr;
	private String username;
	private String password;
	private String email;
	private TipoUsuario userType;
	private String description;
	private Integer userCreate;
	private Integer userUpdate;
	private Date dateCreate;
	private Date dateUpdate;
	private Estatus status;
	

}
