import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {


	public static void main (String args[]){
		String Archivo="";
		File file;

		if (args.length==0){
			Scanner entrada = new Scanner(System.in);
			Archivo = entrada.nextLine();

			file = new File (Archivo);
		}else {
			file = new File (args[0]);
		}
		MapaUniversidad mapa = new MapaUniversidad(file);
		System.out.println(mapa.getMapa()[38][31]);
	}



}
