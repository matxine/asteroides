package com.example.asteroids;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;


public class VistaJuego extends View {

    // //// ASTEROIDES //////

    private Vector<Grafico> Asteroides; // Vector con los Asteroides
    private int numAsteroides= 5; // Número inicial de asteroides
    private int numFragmentos= 3; // Fragmentos en que se divide
    
    // Nave :-)
    private Grafico nave; 
    private int giroNave;
    private int aceleracionNave;
    private static final int PASO_GIRO_NAVE = 5;
    private static final float PASO_ACELERACION_NAVE = 0.5f;
    
	 // //// THREAD Y TIEMPO //////
	 // Thread encargado de procesar el juego
	 private ThreadJuego thread = new ThreadJuego();
	 // Cada cuanto queremos procesar cambios (ms)
	 private static int PERIODO_PROCESO = 50;
	 // Cuando se realiz� el �ltimo proceso
	 private long ultimoProceso = 0;

//    //
//    Path xA, xB;
    public VistaJuego(Context context, AttributeSet attrs) {

          super(context, attrs);
          Drawable drawableNave, drawableAsteroide, drawableMisil;
          
          drawableAsteroide = context.getResources().getDrawable(R.drawable.asteroide1);
          drawableNave = context.getResources().getDrawable(R.drawable.nave);
          
          nave = new Grafico(this, drawableNave); 
          
//
//          xA = new Path();
//          xB = new Path();
          
          Asteroides = new Vector<Grafico>();
          for (int i = 0; i < numAsteroides; i++) 
          {
                Grafico asteroide = new Grafico(this, drawableAsteroide);
                asteroide.setIncY(Math.random() * 4 - 2);
                asteroide.setIncX(Math.random() * 4 - 2);
                asteroide.setAngulo((int) (Math.random() * 360));
                asteroide.setRotacion((int) (Math.random() * 8 - 4));
                Asteroides.add(asteroide);
          }

    }



    @Override protected void onSizeChanged(int ancho, int alto,int ancho_anter, int alto_anter) 
    {
          super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);

          // Una vez que conocemos nuestro ancho y alto.
          nave.setPosX(ancho/2);
          nave.setPosY(alto/2);
          for (Grafico asteroide: Asteroides) 
          { 
        	  do{
        	      asteroide.setPosX(Math.random()*(ancho-asteroide.getAncho()));
        	      asteroide.setPosY(Math.random()*(alto-asteroide.getAlto()));
        	  } while(asteroide.distancia(nave) < (ancho+alto)/5);
          }
          
          ultimoProceso = System.currentTimeMillis();
          thread.start();
          
          //
//          xA.moveTo(0, alto);
//          xA.lineTo(ancho,0);
//          xB.moveTo(0, 0);
//          xB.lineTo(ancho,alto);
    }



    @Override protected synchronized void onDraw(Canvas canvas) 
    {
          super.onDraw(canvas);
          //dibuja la nave
          nave.dibujaGrafico(canvas);
          
          //dibuja los asteroides
          for (Grafico asteroide: Asteroides) 
          {
              asteroide.dibujaGrafico(canvas);
          }
          
          //
//          Paint pincel = new Paint();
//          pincel.setColor(Color.WHITE);
//          pincel.setStrokeWidth(5);
//          pincel.setStyle(Style.STROKE);
//          canvas.drawPath(xA, pincel);
//          canvas.drawPath(xB, pincel);
//          pincel.setStrokeWidth(1);
//          pincel.setStyle(Style.FILL);
    }
    
    protected synchronized void actualizaFisica() 
    {
        long ahora = System.currentTimeMillis();
        // No hagas nada si el per�odo de proceso no se ha cumplido.
        if (ultimoProceso + PERIODO_PROCESO > ahora) 
        {
              return;
        }
        // Para una ejecuci�n en tiempo real calculamos retardo           
        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
        ultimoProceso = ahora; // Para la pr�xima vez
        // Actualizamos velocidad y direcci�n de la nave a partir de 
        // giroNave y aceleracionNave (seg�n la entrada del jugador)
        nave.setAngulo((int) (nave.getAngulo() + giroNave * retardo));
        double nIncX = nave.getIncX() + aceleracionNave * Math.cos(Math.toRadians(nave.getAngulo())) * retardo;
        double nIncY = nave.getIncY() + aceleracionNave * Math.sin(Math.toRadians(nave.getAngulo())) * retardo;
        // Actualizamos si el m�dulo de la velocidad no excede el m�ximo
        if (Math.hypot(nIncX,nIncY) <= Grafico.getMaxVelocidad())
        {
              nave.setIncX(nIncX);
              nave.setIncY(nIncY);
        }
        // Actualizamos posiciones X e Y
        nave.incrementaPos(retardo);
        for (Grafico asteroide : Asteroides) {
              asteroide.incrementaPos(retardo);
        }
    }
    
    class ThreadJuego extends Thread {
    	   @Override
    	   public void run() 
    	   {
    	          while (true) 
    	          {
    	                 actualizaFisica();
    	          }
    	   }
    	}
}
