package com.tecsharp.store.service.ventas.impl;

import java.util.List;

import com.tecsharp.store.entity.ventas.Venta;
import com.tecsharp.store.repository.ventas.impl.VentasRepositoryImpl;
import com.tecsharp.store.service.ventas.VentasService;

public class VentasServiceImpl implements VentasService{

	@Override
	public List<Venta> getVentas(Integer userId) {
		VentasRepositoryImpl venta = new VentasRepositoryImpl();
		return venta.getVentas(userId);	
	}

}
