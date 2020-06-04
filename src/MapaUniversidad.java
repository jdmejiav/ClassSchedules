import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;

public class MapaUniversidad{

	//[Vectice_inicial] [Vertice_final] = distancia
	private int [][] mapa = new int [40][40];

	// codigo_estudiante, discapacidad
	private HashMap <Integer,Integer> estudiantes;
	//<Codigo Materia, Objeto Programacion de esa materia>
	private HashMap <String, Programacion> programacion= new HashMap<String,Programacion>();
	public MapaUniversidad(File mapa,File estudiantes, File programacion) {
		this.estudiantes = new HashMap<Integer,Integer>();
		llenarGrafo(mapa,estudiantes,programacion);
	}

	public int[][] getMapa () {
		return this.mapa;
	}
	public void llenarGrafo(File mapa,File estudiantes,File programacion) {

		try {
			Scanner scMapa = new Scanner (mapa);
			Scanner scEstudiantes = new Scanner (estudiantes);
			Scanner scProgramacion = new Scanner (programacion);
			String tempMapa[];
			String tempEstudiantes[];
			String tempProgramacion[];
			while (scMapa.hasNext()||scEstudiantes.hasNext() || scProgramacion.hasNext()){
				if (scMapa.hasNext()) {
					tempMapa= scMapa.next().split("");
					this.mapa[Integer.parseInt(tempMapa[0])][Integer.parseInt(tempMapa[1])]=Integer.parseInt(tempMapa[2]);
				}
				if (scEstudiantes.hasNext()) {
					tempEstudiantes = scEstudiantes.next().split(",");
					this.estudiantes.put(new Integer(Integer.parseInt(tempEstudiantes[0])),new Integer (Integer.parseInt(tempEstudiantes[1])));
				}
				if (scProgramacion.hasNext()) {
					tempProgramacion = scProgramacion.next().split(",");
					this.programacion.put(tempProgramacion[0], new Programacion (tempProgramacion[0],
					Integer.parseInt(tempProgramacion[1]),tempProgramacion[2],tempProgramacion[3],
					tempProgramacion[4],tempProgramacion[5],Integer.parseInt(tempProgramacion[6])));
				}

			}
		}catch (FileNotFoundException fnfe){
			System.err.println("Archivo no encontrado");
		}
	}




}
