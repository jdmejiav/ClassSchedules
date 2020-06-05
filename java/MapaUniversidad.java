import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MapaUniversidad {

	//[Vectice_inicial] [Vertice_final] = distancia
	private final int[][] mapa = new int[40][40];

	// codigo_estudiante, discapacidad
	private final HashMap<Integer, Integer> estudiantes = new HashMap<Integer,Integer>();
	private final ArrayList <Integer> estudiantesDiscapacitados = new ArrayList <Integer>();

	//clave= codigoMateria-numeroGrupo
	//valor= Programacion
	private final HashMap<String, Programacion> programacion = new HashMap<String, Programacion>();
	private final ArrayList<String> idProgramacion = new ArrayList<String>();

	private final HashMap<String, Aulas> aulas = new HashMap<String, Aulas>();
	private final ArrayList <String> idAulas= new ArrayList<String>();

	private final HashMap<String, Materias> materias = new HashMap <String,Materias>();
	private final ArrayList<String> idMaterias = new ArrayList<String>();

	private final HashMap <String,ArrayList> estudiantesClases = new HashMap<String,ArrayList>();
	private final ArrayList <String> idEsdudiantesClases = new ArrayList<String>();

	public MapaUniversidad(File mapa, File estudiantes, File programacion, File aulas,File materias) {
		llenarGrafo(mapa, estudiantes, programacion, aulas,materias);
		llenarAulas();
		asinarDiscapacitados();
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
			String temp ="";
			while (scMapa.hasNext() || scEstudiantes.hasNext() || scProgramacion.hasNext()
					|| scAulas.hasNext() || scMaterias.hasNext()) {
				if (scMapa.hasNext()) {
					tempMapa = scMapa.next().split(",");
					this.mapa[Integer.parseInt(tempMapa[0])][Integer.parseInt(tempMapa[1])] = Integer.parseInt(tempMapa[2]);
					this.mapa[Integer.parseInt(tempMapa[1])][Integer.parseInt(tempMapa[0])] = Integer.parseInt(tempMapa[2]);
				}
				if (scEstudiantes.hasNext()) {
					tempEstudiantes = scEstudiantes.next().split(",");
					this.estudiantes.put(new Integer(Integer.parseInt(tempEstudiantes[0])), new Integer(Integer.parseInt(tempEstudiantes[1])));
					estudiantesDiscapacitados.add(new Integer (Integer.parseInt(tempEstudiantes[0])));
				}
				if (scProgramacion.hasNext()) {
					tempProgramacion = scProgramacion.next().split(",");
					if (tempProgramacion.length == 7) {
						this.programacion.put(tempProgramacion[0]+"-"+tempProgramacion[1], new Programacion(tempProgramacion[0],
						Integer.parseInt(tempProgramacion[1]), tempProgramacion[2], tempProgramacion[3],
						tempProgramacion[4], tempProgramacion[5], tempProgramacion[6]));
						idProgramacion.add(tempProgramacion[0]+"-"+tempProgramacion[1]);
					}
				}
				if (scAulas.hasNext()) {
					tempAulas = scAulas.nextLine().split(",");
					if (tempAulas.length>3){
					for (int i=1;i<tempAulas.length-2;i++){
						temp+=tempAulas[i];
					}
					this.aulas.put(tempAulas[0], new Aulas( tempAulas[0],
					temp,tempAulas[tempAulas.length-2],Integer.parseInt(tempAulas[tempAulas.length-1])));
					idAulas.add(tempAulas[0]);
				  }
					temp="";
				}
				if (scMaterias.hasNext()) {
					tempMaterias= scMaterias.next().split(",");
					if (tempMaterias.length==3){
						this.materias.put(tempMaterias[0],new
						Materias(tempMaterias[0],tempMaterias[1],Integer.parseInt(tempMaterias[2])));
						idMaterias.add(tempMaterias[0]);
					}
				}
			}
		}catch (FileNotFoundException fnfe){
			System.err.println("Archivo no encontrado");
		}
	}
	public void llenarAulas(){

		for (String i: idProgramacion){
			//String dia, String horaInicio, String horaFinal, String aula
			if (!(programacion.get(i).getIdAula().equals("00000"))){
				if (aulas.get(programacion.get(i).getIdAula())!=null){
				aulas.get(programacion.get(i).getIdAula()).agregarClase(
					programacion.get(i).getDia(),
					programacion.get(i).getHoraInicio(),
					programacion.get(i).gethoraFin(),
					programacion.get(i).getIdAula() );
				}
			}
		}
	}

	public void asinarDiscapacitados(){
		for (Integer i : estudiantesDiscapacitados) {

		}

	}

}
