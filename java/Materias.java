
public class Materias {

	private String codigoEstudiante;
	private String codigoMateria;
	private int numeroGrupo;



	public Materias(String codigoEstudiante, String codigoMateria,int numeroGrupo){
		this.codigoEstudiante = codigoEstudiante;
		this.codigoMateria = codigoMateria;
		this.numeroGrupo = numeroGrupo;
	}

	public String getCodigoMateriaGrupo(){
		return this.codigoMateria+"-"+String.valueOf(numeroGrupo);
	}

	public String toString (){
		return "codigoEstudiante "+codigoEstudiante+" CodigoMateria "+codigoMateria+" numeroGrupo "+String.valueOf(numeroGrupo);
	}

}
