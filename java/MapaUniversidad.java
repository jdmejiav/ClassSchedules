
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapaUniversidad {

	//[Vectice_inicial] [Vertice_final] = distancia
	private final int[][] mapa = new int[40][40];

	// codigo_estudiante, discapacidad
	private final HashMap<Integer, Integer> estudiantes = new HashMap<Integer,Integer>();

	//clave del hash Estudiantes
	private final ArrayList <Integer> idEstudiantes = new ArrayList <Integer>();

	//clave= codigoMateria-numeroGrupo
	//valor= Programacion
	private final HashMap<String, Programacion> programacion = new HashMap<String, Programacion>();

	//Arreglo Clave del hash programacion
	private final ArrayList<String> idProgramacion = new ArrayList<String>();

	//clave idAulas
	//valor Objeto Aula
	private final HashMap<String, Aulas> aulas = new HashMap<String, Aulas>();
	//clave del hashIdAula
	private final ArrayList <String> idAulas= new ArrayList<String>();


	//clave = idEstudiantes
	private final HashMap<String, ArrayList<Materias>> materias = new HashMap <String,ArrayList<Materias>>();
	//arreglo con las claves de materias
	private final ArrayList<String> idMaterias = new ArrayList<String>();

	//Hash que contiene las materias registradas registradas de cada estudiante
	//clave = Id del estudiantes
	//valor = Arreglo de idMaterias
	private final HashMap <String,ArrayList<String>> estudiantesClases = new HashMap<String,ArrayList<String>>();
	private final ArrayList <String> idEsdudiantesClases = new ArrayList<String>();

	//Hash que contiene las clases que ya han sido asigandas
	//clave = Codgio clase
	//valor = objeto Aula
	private final HashMap <String,Aulas> clasesAsignadas = new HashMap<String,Aulas>();

	//Constructor de la clase MapaUniversidad

	public MapaUniversidad(File mapa, File estudiantes, File programacion, File aulas,File materias) {
		llenarGrafo(mapa, estudiantes, programacion, aulas,materias);
		llenarAulas();
		asinarEstudiantes();
		producirSalida();
	}


	public int[][] getMapa() {
		return this.mapa;
	}

	// Metodo para leer los arvhicos y almacenar los datos en sus respectivas variables

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

			//ciclo para leer cada archivo

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
					idEstudiantes.add(new Integer (Integer.parseInt(tempEstudiantes[0])));

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

						if (this.materias.get(tempMaterias[0])!=null){
							this.materias.get(tempMaterias[0]).add(new Materias(tempMaterias[0],tempMaterias[1],
							Integer.parseInt(tempMaterias[2])));
						}else {
							this.materias.put(tempMaterias[0],new ArrayList<Materias>());
							this.materias.get(tempMaterias[0]).add(new Materias(tempMaterias[0],tempMaterias[1],Integer.parseInt(tempMaterias[2])));
							idMaterias.add(tempMaterias[0]);
						}


						if (estudiantesClases.get(tempMaterias[1]+"-"+tempMaterias[2])!=null){
							estudiantesClases.get(tempMaterias[1]+"-"+tempMaterias[2]).add(tempMaterias[0]);
						}else {
							estudiantesClases.put (tempMaterias[1]+"-"+tempMaterias[2], new ArrayList<String>());
							estudiantesClases.get(tempMaterias[1]+"-"+tempMaterias[2]).add(tempMaterias[0]);
							idEsdudiantesClases.add(tempMaterias[1]+"-"+tempMaterias[2]);
						}
					}
				}
			}
		}catch (FileNotFoundException fnfe){
			System.err.println("Archivo no encontrado");
		}
	}

	//método para llenar las aulas que ya se encuentran asignadas en el archivo pa2192.csv

	public void llenarAulas(){

		for (String i: idProgramacion){

			if (!(programacion.get(i).getIdAula().equals("00000"))){
				if (aulas.get(programacion.get(i).getIdAula())!=null){
				aulas.get(programacion.get(i).getIdAula()).agregarClase(
					programacion.get(i).getDia(),
					programacion.get(i).getHoraInicio(),
					programacion.get(i).gethoraFin(),
					programacion.get(i).getCodigoMateriaGrupo());
				clasesAsignadas.put(i,aulas.get(programacion.get(i).getIdAula()));
				}
			}
		}
	}

	//Meétodo para Asignarle clase a los estudiantes cuyas clase figuran en el archivo pa20192

	public void asinarEstudiantes(){


		int estudiantesAsignados=0;
		int idxAula=0;
		Programacion temp=new Programacion("",0,"","","","","");
		for (Integer i: idEstudiantes) {
			if (materias.get(String.valueOf(i))!=null) {
				for (Materias k: materias.get(String.valueOf(i))){
					if (programacion.get(k.getCodigoMateriaGrupo())!=null){
						temp = programacion.get(k.getCodigoMateriaGrupo());

						idxAula = calcularIdxAula(idxAula,temp.getDia(),temp.getHoraInicio(),temp.gethoraFin(),i);


						if (clasesAsignadas.get(temp.getCodigoMateriaGrupo())==null) {
							this.aulas.get(idAulas.get(idxAula)).agregarClase(
								temp.getDia(),
								temp.getHoraInicio(),
								temp.gethoraFin(),
								temp.getCodigoMateriaGrupo());
								clasesAsignadas.put(temp.getCodigoMateriaGrupo(),aulas.get(idAulas.get(idxAula)));
								estudiantesAsignados++;
							programacion.get(k.getCodigoMateriaGrupo()).setIdAula(idAulas.get(idxAula));
						}
						idxAula=0;
					}
					else {
						int tempHoraInicio= ((int) ((Math.random()*5)))+2;
						int tempHoraFinal = tempHoraInicio+((int) (Math.random()*4))+1;
						int tempDia = ((int)(Math.random()*6));
						String strDia="";

						switch (tempDia){
							case 0:
								strDia="lunes";
								break;
							case 1:
								strDia="martes";
								break;
							case 2:
								strDia="miércoles";
								break;
							case 3:
								strDia="jueves";
								break;
							case 4:
								strDia="viernes";
								break;
							case 5:
								strDia="sábado";
								break;
							default:
								strDia="domingo";
								break;
						}

						String strHoraInicio = String.valueOf((int)(tempHoraInicio+6))+":"+"00";
						String strHoraFinal = String.valueOf((int)(tempHoraFinal+6))+":"+"00";

						idxAula = calcularIdxAula(idxAula,strDia,strHoraInicio,strHoraFinal,i);

						if (clasesAsignadas.get(temp.getCodigoMateriaGrupo())==null) {
							idProgramacion.add(k.getCodigoMateriaGrupo());
							programacion.put(k.getCodigoMateriaGrupo(),new Programacion(k.getCodigoMateria(),k.getNumeroGrupo(),
							"No Asignado",strDia,strHoraInicio,strHoraFinal,idAulas.get(idxAula)));
							this.aulas.get(idAulas.get(idxAula)).agregarClase(
								strDia,
								strHoraInicio,
								strHoraFinal,
								k.getCodigoMateriaGrupo());
								clasesAsignadas.put(k.getCodigoMateriaGrupo(),aulas.get(idAulas.get(idxAula)));
								estudiantesAsignados++;
						}
					}
				}
			}
		}
	}



	private int calcularIdxAula (int idxAula, String dia, String horaInicio, String horaFin, Integer i) {

		boolean disponibilidad = aulas.get(idAulas.get(idxAula)).checkearHora(dia,horaInicio,horaFin);
		boolean capacidad = aulas.get(idAulas.get(idxAula)).getCapacidad() >= materias.get(String.valueOf(i)).size();
		boolean accesibilidad = (aulas.get(idAulas.get(idxAula)).getAcceso()==1 && estudiantes.get(i)==1) || estudiantes.get(i)==0 ;

		if (disponibilidad && capacidad && accesibilidad){
			return idxAula;
		}else {

			return (calcularIdxAula(idxAula+1,dia,horaInicio,horaFin,i));
		}
	}

	//método para Producir la salida de los archivos

	private void producirSalida (){

		StringBuilder builderProgramacion = new StringBuilder();
		for (String i: idProgramacion){
			if (!programacion.get(i).getIdAula().equals("00000"))
			builderProgramacion.append(programacion.get(i).toCsv()+"\n");
		}

		StringBuilder builderEstudiantes = new StringBuilder();
		String temp="";
		for (Integer i: idEstudiantes){

			temp=i+":\n";
			for (Materias k: materias.get(String.valueOf(i))){
				if (programacion.get(k.getCodigoMateriaGrupo())!=null){
					temp+="\t\t\t"+programacion.get(k.getCodigoMateriaGrupo()).toCsv()+"\n";

				}
			}
			if (!temp.equals(i+":\n")){
				builderEstudiantes.append(temp);
				builderEstudiantes.append("\n\n");
			}
			temp="";

		}


		StringBuilder builderFile = new StringBuilder();
		for (String i: idAulas){
			builderFile.append(aulas.get(i).horarioToFile());
		}

		try {
			File salida = new File ("salidaHorarioAulas.out");
			File salidaProgramacion = new File ("salidaProgramacion.out");
			File salidaEstudiantes = new File ("salidaEstudiantes.out");

			if (!salida.exists()) {
				salida.createNewFile();
			}else {
				salida.delete();
				salida.createNewFile();
			}
			if (!salidaProgramacion.exists()) {
				salidaProgramacion.createNewFile();
			}else {
				salidaProgramacion.delete();
				salidaProgramacion.createNewFile();
			}
			if (!salidaEstudiantes.exists()) {
				salidaEstudiantes.createNewFile();
			}else {
				salidaEstudiantes.delete();
				salidaEstudiantes.createNewFile();
			}

			//escribir sobre los archivos de salida

			BufferedWriter bfHorario = new BufferedWriter(new FileWriter("salidaHorarioAulas.out"));
			BufferedWriter bfProgramacion = new BufferedWriter(new FileWriter("salidaProgramacion.out"));
			BufferedWriter bfEstudiantes = new BufferedWriter(new FileWriter("salidaEstudiantes.out"));

			bfHorario.write(builderFile.toString());
			bfHorario.close();
			bfProgramacion.write(builderProgramacion.toString());
			bfProgramacion.close();
			bfEstudiantes.write(builderEstudiantes.toString());
			bfEstudiantes.close();

		}catch (IOException e){
				e.printStackTrace();
		}
	}


}
