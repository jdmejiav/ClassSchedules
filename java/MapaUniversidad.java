import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MapaUniversidad {

	//[Vectice_inicial] [Vertice_final] = distancia
	private final int[][] mapa = new int[40][40];

	// codigo_estudiante, discapacidad
	private final HashMap<Integer, Integer> estudiantes = new HashMap<Integer,Integer>();
	private final ArrayList <Integer> estudiantesDiscapacitados = new ArrayList <Integer>();

	private final HashMap<String, Programacion> programacion = new HashMap<String, Programacion>();

	private final HashMap<String, Aulas> aulas = new HashMap<String, Aulas>();

	private final HashMap<String, Materias> materias = new HashMap <String,Materias>();

	public MapaUniversidad(File mapa, File estudiantes, File programacion, File aulas,File materias) {

		llenarGrafo(mapa, estudiantes, programacion, aulas,materias);
	}

	public int[][] getMapa() {
		return this.mapa;
	}

	public void llenarGrafo(File mapa, File estudiantes, File programacion, File aulas, File materias) {

		try {
			Scanner scMapa = new Scanner(mapa);
			Scanner scEstudiantes = new Scanner(estudiantes);
			Scanner scProgramacion = new Scanner(programacion);
			Scanner scAulas = new Scanner(aulas);
			Scanner scMaterias = new Scanner (materias);
			String[] tempMapa;
			String[] tempEstudiantes;
			String[] tempProgramacion;
			String[] tempAulas;
			String[] tempMaterias;
			String[] temp;
			int ejecucion=0;
			while (scMapa.hasNext() || scEstudiantes.hasNext() || scProgramacion.hasNext()
					|| scAulas.hasNext()) {
				if (scMapa.hasNext()) {
					tempMapa = scMapa.next().split(",");
					this.mapa[Integer.parseInt(tempMapa[0])][Integer.parseInt(tempMapa[1])] = Integer.parseInt(tempMapa[2]);
					this.mapa[Integer.parseInt(tempMapa[1])][Integer.parseInt(tempMapa[0])] = Integer.parseInt(tempMapa[2]);
				}
				if (scEstudiantes.hasNext()) {
					tempEstudiantes = scEstudiantes.next().split(",");
					this.estudiantes.put(new Integer(Integer.parseInt(tempEstudiantes[0])), new Integer(Integer.parseInt(tempEstudiantes[1])));
				}
				if (scProgramacion.hasNext()) {
					tempProgramacion = scProgramacion.next().split(",");
					if (tempProgramacion.length == 7) {
					this.programacion.put(tempProgramacion[0], new Programacion(tempProgramacion[0],
							Integer.parseInt(tempProgramacion[1]), tempProgramacion[2], tempProgramacion[3],
							tempProgramacion[4], tempProgramacion[5], Integer.parseInt(tempProgramacion[6])));
					}
				}
				if (scAulas.hasNext()) {

					tempAulas = scAulas.next().split(",");
					if (tempAulas.length==4){
						this.aulas.put(tempAulas[0]+"-"+tempAulas[1], new
						Aulas( tempAulas[0],
						tempAulas[1],tempAulas[2],Integer.parseInt(tempAulas[3])));
					}
				}
				if (scMaterias.hasNext()) {
					tempMaterias= temp.split(",");
					if (tempMaterias.length==3){
						this.materias.put(tempMaterias[0],new
						Materias(tempMaterias[0],tempMaterias[1] ,Integer.parseInt(tempMaterias[tempMaterias.length])));
					}
				}
				ejecucion++;
			}
			System.out.println(ejecucion);
		}catch (FileNotFoundException fnfe){
			System.err.println("Archivo no encontrado");
		}
	}




}
