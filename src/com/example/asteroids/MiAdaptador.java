package com.example.asteroids;

import java.util.Vector;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MiAdaptador extends BaseAdapter{
	
	private final Activity actividad;
	private final Vector<String> lista;
	
	public MiAdaptador(Activity actividad, Vector<String> lista) {
        super();
        this.actividad = actividad;
        this.lista = lista;
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.elementAt(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = actividad.getLayoutInflater();
		View view = inflater.inflate(R.layout.elemento_lista,null,true);
		
		TextView textView = (TextView)view.findViewById(R.id.titulo);
		ImageView imageView = (ImageView)view.findViewById(R.id.icono);
		
		textView.setText(lista.elementAt(position));
		switch(Math.round((float)Math.random()*3))
		{
		case 1:
			imageView.setImageResource(R.drawable.asteroide1);
			break;
		case 0:
			imageView.setImageResource(R.drawable.asteroide2);
			break;
		default:
			imageView.setImageResource(R.drawable.asteroide3);
			break;
		}
		
		return view;
	}
	

}
