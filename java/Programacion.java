public class Programacion{


	String codigoMateria;
	int numeroGrupo;
	String idProfesor;
	String dia;
	String horaInicio;
	String horaFin;
	String idAula;

	//
	public Programacion(String codigoMateria, int numeroGrupo, String idProfesor, String dia, String horaInicio, String horaFin, String idAula) {
		this.codigoMateria = codigoMateria;
		this.numeroGrupo = numeroGrupo;
		this.idProfesor = idProfesor;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.idAula = idAula;
	}

	public String getCodigoMateria(){
			return this.codigoMateria;
	}
	public int getNumeroGrupo(){
			return this.numeroGrupo;
	}
	public String getIdProfesor(){
			return this.idProfesor;
	}
	public String getDia(){
			return this.dia;
	}
	public String getHoraInicio(){
			return this.horaInicio;
	}
	public String gethoraFin(){
			return this.horaFin;
	}
	public String getIdAula(){
			return this.idAula;
	}

}
