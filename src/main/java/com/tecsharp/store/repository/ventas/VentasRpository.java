package com.tecsharp.store.repository.ventas;

import java.util.List;

import com.tecsharp.store.entity.ventas.Venta;


public interface VentasRpository {
	
	public List<Venta> getVentas(Integer userId);
	
	
}
