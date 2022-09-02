package com.tecsharp.store.entity.productos;

import java.util.Date;
import lombok.Data;

@Data
public class Producto {
	
	private Integer idProduct;
	private String name;
	private Integer stock;
	private Double price;
	private TipoProducto productType;
	private String description;
	private Integer numItems;
	private Integer userCreate;
	private Integer userUpdate;
	private Date dateCreate;
	private Date dateUpdate;
	private Integer status;
	
	
}
