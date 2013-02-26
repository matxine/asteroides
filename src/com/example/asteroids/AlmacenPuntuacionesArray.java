package com.example.asteroids;

import java.util.Vector;

public class AlmacenPuntuacionesArray implements AlmacenPuntuaciones {
	private Vector<String> puntuaciones;
	
	public AlmacenPuntuacionesArray()
	{
		puntuaciones = new Vector<String>();
		puntuaciones.add("12300 Pepito Domínguez");
		puntuaciones.add("10000 Décimo Meridio");
		puntuaciones.add("5000 Un tio cualquiera");
	}
	
	@Override
	public void guardarPuntuacion(int puntos, String nombre, long fecha)
	{
		puntuaciones.add(0,puntos + " " + nombre);
	}

	@Override
	public Vector<String> listaPuntuaciones(int cantidad) 
	{
		return puntuaciones;
	}
	
	

}
