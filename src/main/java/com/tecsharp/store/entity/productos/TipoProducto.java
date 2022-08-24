package com.tecsharp.store.entity.productos;

import java.util.Date;

import com.tecsharp.store.entity.estatus.Estatus;

import lombok.Data;

@Data
public class TipoProducto {
	
	private Integer idProductType;
	private String name;
	private String description;
	private Integer userCreate;
	private Integer userUpdate;
	private Date dateCreate;
	private Date dateUpdate;
	private Estatus status;

}
