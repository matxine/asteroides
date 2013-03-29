package com.example.asteroids;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Asteroides extends Activity {
	public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();
	private Button bJugar;
	private Button bAcercaDe;
//	private MediaPlayer mp;
//	private int musicPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        mp = MediaPlayer.create(this, R.raw.audio);
//        mp.start();
//        musicPosition = 0;
        
//        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.main);
        bJugar = (Button)findViewById(R.id.Button01);
        bAcercaDe = (Button)findViewById(R.id.Button03);        
        bAcercaDe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{			
				lanzarAcercaDe(null);
			}
		});             
        
        bJugar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				lanzarJuego(null);
			}
		});
        
        startService(new Intent(this, ServicioMusica.class));
    } //end onCreate()

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
     }
    
     @Override public boolean onOptionsItemSelected(MenuItem item) {
              switch (item.getItemId()) {
              case R.id.acercaDe:
                     lanzarAcercaDe(null);
                     break;
              case R.id.config:
                  lanzarPreferencias(null);
                  break;
              }
              return true;
     }
    
    public void lanzarJuego(View view)
    {
     	Intent i = new Intent(this, Juego.class);
    	startActivity(i);
    }
    
    public void lanzarAcercaDe(View view)
    {
    	Intent i = new Intent(this, AcercaDe.class);
    	startActivity(i);
    }
    
    public void lanzarPreferencias(View view)
    {
    	Intent i = new Intent(this, Preferencias.class);
    	startActivity(i);
    }
    
    public void lanzarPuntuaciones(View view)
    {
    	Intent i = new Intent(this, Puntuaciones.class);
    	startActivity(i);
    }

    @Override protected void onStart() {
    	   super.onStart();
//    	   mp.start();
    	}
    	 
    	@Override protected void onResume() {
    	   super.onResume();
//    	   mp.start();
    	}
    	 
    	@Override protected void onPause() {
    		super.onPause();
    	}
    	 
    	@Override protected void onStop() {    	   
    	   super.onStop();
//    	   mp.pause();
    	}
    	 
    	@Override protected void onRestart() {
    	   super.onRestart();
//    	   mp.start();
    	}
    	 
    	@Override protected void onDestroy() {
    	   super.onDestroy();
//    	   mp.stop();
    	   stopService(new Intent(this, ServicioMusica.class));
    	}
    	
    	@Override
    	protected void onSaveInstanceState(Bundle guardarEstado)
    	{
    		super.onSaveInstanceState(guardarEstado);
//    		if (mp != null)
//    		{
//    			guardarEstado.putInt("contMusica", mp.getCurrentPosition());
//    		}
    	}
    	
    	@Override
    	protected void onRestoreInstanceState(Bundle recuperarEstado)
    	{
//    		musicPosition = recuperarEstado.getInt("contMusica");
//    		mp.seekTo(musicPosition);
    	}
}
