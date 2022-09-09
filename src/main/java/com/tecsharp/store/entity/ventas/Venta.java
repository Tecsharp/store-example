package com.tecsharp.store.entity.ventas;

import java.util.Date;


import com.tecsharp.store.entity.estatus.EnumEstatus;
import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.usuarios.Usuario;

import lombok.Data;

@Data
public class Venta {
	
	private String nameProduct;
	private Integer idSale;
	private Usuario idUser;
	private Producto idProduct;
	private Integer numItems;
	private String description;
	private Integer userCreate;
	private Integer userUpdate;
	private Date dateCreate;
	private Date dateUpdate;
	private EnumEstatus status;
	
}
