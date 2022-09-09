package com.tecsharp.store.service.ventas;

import java.util.List;

import com.tecsharp.store.entity.ventas.Venta;

public interface VentasService {
	
	public List<Venta> getVentas(Integer userId);

}
