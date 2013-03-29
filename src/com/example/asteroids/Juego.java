package com.example.asteroids;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Juego extends Activity {

	private VistaJuego vistaJuego;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        vistaJuego = (VistaJuego) findViewById(R.id.VistaJuego);
    }
    
    @Override protected void onPause() {
	   super.onPause();
	   vistaJuego.getThread().pausar();	   
	}
	 
	@Override protected void onResume() {
	   super.onResume();
	   vistaJuego.getThread().reanudar();
	}
	 
	@Override protected void onDestroy() {
	   vistaJuego.getThread().detener();
	   super.onDestroy();
	}
}
