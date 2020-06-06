
import java.io.File;
import java.util.Scanner;


public class Main {

	/*
	[0] = estudiantes.csv
	[1] = pa201920192.csv
	[2] = DistanciaBloques.csv
	*/
	public static void main(String[] args) {

		File estudiantes = new File("sources/estudiantes.csv");
		File programacion = new File("sources/pa20192.csv");
		File mapa = new File("sources/DistanciasBloques.csv");
		File aulas = new File("sources/aulas.csv");
		File materias = new File ("sources/mat20192.csv");
		MapaUniversidad mapaU = new MapaUniversidad(mapa, estudiantes, programacion, aulas,materias);
		//System.out.println(mapa.getMapa()[38][31]);
/*
		Aulas aulaprueba = new Aulas ("12345","una Aula muy bonita","20",1);
		aulaprueba.agregarClase("lunes","6:00","6:30","laqueacabodecrear");
		aulaprueba.imprimirHorario();

		System.out.println(aulaprueba.checkearHora("lunes","8:00","10:30"));
*/
	}



}
