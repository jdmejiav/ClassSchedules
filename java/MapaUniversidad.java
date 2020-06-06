
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
	private final ArrayList <Integer> estudiantesDiscapacitados = new ArrayList <Integer>();

	//clave= codigoMateria-numeroGrupo
	//valor= Programacion
	private final HashMap<String, Programacion> programacion = new HashMap<String, Programacion>();
	private final ArrayList<String> idProgramacion = new ArrayList<String>();

	private final HashMap<String, Aulas> aulas = new HashMap<String, Aulas>();
	private final ArrayList <String> idAulas= new ArrayList<String>();

	private final HashMap<String, ArrayList<Materias>> materias = new HashMap <String,ArrayList<Materias>>();
	private final ArrayList<String> idMaterias = new ArrayList<String>();

	private final HashMap <String,ArrayList<String>> estudiantesClases = new HashMap<String,ArrayList<String>>();
	private final ArrayList <String> idEsdudiantesClases = new ArrayList<String>();

	private final HashMap <String,Aulas> clasesAsignadas = new HashMap<String,Aulas>();

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
	public void llenarAulas(){

		for (String i: idProgramacion){
			//String dia, String horaInicio, String horaFinal, String aula
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

	public void asinarDiscapacitados(){
		//System.out.println("materias "+materias.size());
		//System.out.println("estudiantes "+estudiantes.size());
		//System.out.println("programacion "+programacion.size());
		//System.out.println("aulas "+aulas.size());
		int estudiantesAsignados=0;
		int idxAula=0;
		Programacion temp=new Programacion("",0,"","","","","");
		for (Integer i: estudiantesDiscapacitados) {
			if (materias.get(String.valueOf(i))!=null) {
				for (Materias k: materias.get(String.valueOf(i))){
					if (programacion.get(k.getCodigoMateriaGrupo())!=null){
						temp = programacion.get(k.getCodigoMateriaGrupo());

						idxAula = calcularIdxAula(idxAula,temp.getDia(),temp.getHoraInicio(),temp.gethoraFin(),i);
						/*
						while (!(aulas.get(idAulas.get(idxAula))
						.checkearHora(temp.getDia(),temp.getHoraInicio(), temp.gethoraFin())) &&
						 aulas.get(idAulas.get(idxAula)).getCapacidad()>= materias.get(i+"").size() &&
						 !((aulas.get(idAulas.get(idxAula)).getAcceso()==1 && estudiantes.get(i)==1) || estudiantes.get(i)==0)) {

							idxAula++;
						}
						*/
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
						int tempHoraFinal = tempHoraInicio+((int) (Math.random()*10))+1;
						int tempDia = ((int)(Math.random()*6));
						String strDia="";

						switch (tempDia){
							case '0':
								strDia="lunes";
								break;
							case '1':
								strDia="martes";
								break;
							case '2':
								strDia="miércoles";
								break;
							case '3':
								strDia="jueves";
								break;
							case '4':
								strDia="viernes";
								break;
							case '5':
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
		StringBuilder builderProgramacion = new StringBuilder();
		for (String i: idProgramacion){
			if (!programacion.get(i).getIdAula().equals("00000"))
			builderProgramacion.append(programacion.get(i).toCsv()+"\n");
		}

		StringBuilder builderEstudiantes = new StringBuilder();

		for (Integer i: estudiantesDiscapacitados){
			//System.out.println(i+":");
			builderEstudiantes.append(i+":\n");
			for (Materias k: materias.get(String.valueOf(i))){
				if (programacion.get(k.getCodigoMateriaGrupo())!=null){
				//	System.out.println("\t\t"+programacion.get(k.getCodigoMateriaGrupo()).toCsv());
					builderEstudiantes.append("\t\t\t"+programacion.get(k.getCodigoMateriaGrupo()).toCsv()+"\n");
				}
			}
			builderEstudiantes.append("\n\n");
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

		/*
		System.out.println("Estudiantes asignados: "+estudiantesAsignados);
		int iguales=0;

		int noexiste=0;


		for (String k:idMaterias){
			for (Materias j: materias.get(k)){
				if (programacion.get(j.getCodigoMateriaGrupo())!=null){

					if (j.getCodigoMateriaGrupo().equals(programacion.get(j.getCodigoMateriaGrupo()).getCodigoMateriaGrupo())) {
							iguales++;

					}
				}else {
					noexiste++;
				}
			}

		}

		System.out.println("no existen = "+noexiste);
		System.out.println("iguales = "+iguales);
		*/
		//System.out.println(estudiantesAsignados);
	}



	public int calcularIdxAula (int idxAula, String dia, String horaInicio, String horaFin, Integer i) {

		boolean disponibilidad = aulas.get(idAulas.get(idxAula)).checkearHora(dia,horaInicio,horaFin);
		boolean capacidad = aulas.get(idAulas.get(idxAula)).getCapacidad() >= materias.get(String.valueOf(i)).size();
		boolean accesibilidad = (aulas.get(idAulas.get(idxAula)).getAcceso()==1 && estudiantes.get(i)==1) || estudiantes.get(i)==0 ;

		if (disponibilidad && capacidad && accesibilidad){
			return idxAula;
		}else {

			return (calcularIdxAula(idxAula+1,dia,horaInicio,horaFin,i));
		}

	}


}
