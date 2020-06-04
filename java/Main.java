import java.io.File;
import java.util.Scanner;


public class Main {

	/*
	[0] = estudiantes.csv
	[1] = pa201920192.csv
	[2] = DistanciaBloques.csv
	*/
	public static void main(String[] args) {

		File estudiantes = new File("estudiantes.csv");
		File programacion = new File("pa20192.csv");
		File mapa = new File("DistanciasBloques.csv");
		File aulas = new File("aulas.csv");
		File materias = new File ("mat20192.csv");
		MapaUniversidad mapaU = new MapaUniversidad(mapa, estudiantes, programacion, aulas,materias);
		//System.out.println(mapa.getMapa()[38][31]);
	}



}
