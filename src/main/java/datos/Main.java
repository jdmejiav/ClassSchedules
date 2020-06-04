package datos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

	/*
	[0] = estudiantes.csv
	[1] = pa201920192.csv
	[2] = DistanciaBloques.csv
	*/
	public static void main (String args[]){

		File estudiantes = new File (args[0]);
		File programacion = new File (args[1]);
		File mapa = new File (args[2]);
		MapaUniversidad mapaU = new MapaUniversidad(mapa,estudiantes,programacion);
		//System.out.println(mapa.getMapa()[38][31]);
	}



}
