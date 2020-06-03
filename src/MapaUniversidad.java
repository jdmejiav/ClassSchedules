import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;

public class MapaUniversidad{

	//<Vectice_inicial , Hash<Vertice_final, distancia>>
	private int [][] mapa = new int [40][40];
	public MapaUniversidad(File file){
		llenarGrafo(file);
	}

	public int[][] getMapa (){
		return this.mapa;
	}
	public void llenarGrafo(File file){

		try {
			Scanner sc = new Scanner (file);
			String temp[];
			while (sc.hasNext()){
				temp = sc.next().split(",");
				mapa[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])]=Integer.parseInt(temp[2]);
			}


		}catch (FileNotFoundException fnfe){
			System.err.println("Archivo no encontrado");
		}
	}




}
