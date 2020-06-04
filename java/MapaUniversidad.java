import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ArrayIndexOutOfBoundsException;


public class MapaUniversidad {

	//[Vectice_inicial] [Vertice_final] = distancia
	private final int[][] mapa = new int[40][40];

	// codigo_estudiante, discapacidad
	private final HashMap<Integer, Integer> estudiantes;

	private final HashMap<String, Programacion> programacion = new HashMap<String, Programacion>();

	private final HashMap<String, Aulas> aulas = new HashMap<String, Aulas>();


	public MapaUniversidad(File mapa, File estudiantes, File programacion, File aulas) {
		this.estudiantes = new HashMap<Integer, Integer>();
		llenarGrafo(mapa, estudiantes, programacion, aulas);
	}

	public int[][] getMapa() {
		return this.mapa;
	}

	public void llenarGrafo(File mapa, File estudiantes, File programacion, File aulas) {

		try {
			Scanner scMapa = new Scanner(mapa);
			Scanner scEstudiantes = new Scanner(estudiantes);
			Scanner scProgramacion = new Scanner(programacion);
			Scanner scAulas = new Scanner(aulas);
			String[] tempMapa;
			String[] tempEstudiantes;
			String[] tempProgramacion;
			String[] tempAulas;
			while (scMapa.hasNext() || scEstudiantes.hasNext() || scProgramacion.hasNext()
					|| scAulas.hasNext()) {
				if (scMapa.hasNext()) {
					tempMapa = scMapa.next().split(",");
					this.mapa[Integer.parseInt(tempMapa[0])][Integer.parseInt(tempMapa[1])] = Integer.parseInt(tempMapa[2]);
				}
				if (scEstudiantes.hasNext()) {
					tempEstudiantes = scEstudiantes.next().split(",");
					this.estudiantes.put(new Integer(Integer.parseInt(tempEstudiantes[0])), new Integer(Integer.parseInt(tempEstudiantes[1])));
				}
				if (scProgramacion.hasNext()) {
					tempProgramacion = scProgramacion.next().split(",");
					this.programacion.put(tempProgramacion[0], new Programacion(tempProgramacion[0],
							Integer.parseInt(tempProgramacion[1]), tempProgramacion[2], tempProgramacion[3],
							tempProgramacion[4], tempProgramacion[5], Integer.parseInt(tempProgramacion[6])));
				}
				if (scAulas.hasNext()) {
					tempAulas = scAulas.next().split(",");
					this.aulas.put(tempAulas[0], new Aulas( tempAulas[0], 
					tempAulas[1], Integer.parseInt(tempAulas[2])));
				}
			}
		}catch (FileNotFoundException fnfe){
			System.err.println("Archivo no encontrado");
		}catch (ArrayIndexOutOfBoundsException e) {
			System.err.println();
		}
	}




}
