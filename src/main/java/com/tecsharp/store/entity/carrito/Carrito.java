package com.tecsharp.store.entity.carrito;

import java.util.Date;

import com.tecsharp.store.entity.estatus.Estatus;
import com.tecsharp.store.entity.productos.Producto;
import com.tecsharp.store.entity.usuarios.Usuario;

import lombok.Data;

@Data
public class Carrito {

	private Integer idCart;
	private Integer userCreate;
	private Integer userUpdate;
	private Date dateCreate;
	private Date dateUpdate;
	private Estatus status;
	private Usuario idUser;
	private Producto idProduct;
}
