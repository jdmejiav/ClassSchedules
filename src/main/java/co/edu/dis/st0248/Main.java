import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {


	public static void main (String args[]){

		File estudiantes = new File ("estudiantes.csv");
		File programacion = new File ("pa20192.csv");
		File mapa = new File ("DistanciaBloques.csv");
		MapaUniversidad mapaU = new MapaUniversidad(mapa,estudiantes,programacion);
		//System.out.println(mapa.getMapa()[38][31]);
	}



}
