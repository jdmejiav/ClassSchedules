public class Aulas {

	private final String idAula;
	private final String tipoAula;
	private final String capacidad;
	private final int acceso;


	private final int [][] horario = new int [7][32];
	private final String [][] horarioAula = new String [7][32];

	public Aulas(String idAula, String tipoAula, String capacidad, int acceso) {
		this.idAula = idAula;
		this.tipoAula = tipoAula;
		this.capacidad = capacidad;
		this.acceso = acceso;
	}


	public void agregarClase (String dia, String horaInicio, String horaFinal, String aula) {
		int d = 8;
		if (dia.equalsIgnoreCase("lunes"))d=0;
		if (dia.equalsIgnoreCase("martes"))d=1;
		if (dia.equalsIgnoreCase("miércoles"))d=2;
		if (dia.equalsIgnoreCase("jueves"))d=3;
		if (dia.equalsIgnoreCase("viernes"))d=4;
		if (dia.equalsIgnoreCase("sábado"))d=5;
		if (dia.equalsIgnoreCase("domingo"))d=6;

		int horaIni = Integer.parseInt(horaInicio.replace(":",""));
		horaIni = ((horaIni-600)/100)*2;
		if ((Integer.parseInt(horaInicio.replace(":",""))%100)!=0) {
			horaIni++;
		}
		int horaFin = Integer.parseInt(horaFinal.replace(":",""));
		horaFin =((horaFin-600)/100)*2;
		if (Integer.parseInt(horaFinal.replace(":",""))%100!=0) {
			horaFin++;
		}

		for (int i = horaIni;i<horaFin;i++){
			horario [d][i] = 1;
			horarioAula [d][i] = aula;
		}
	}
	public String toString(){
		return "idAula "+idAula+"\ntipoAula "+tipoAula+"\ncapacidad "+capacidad+
		"\nacceso "+acceso;
	}

	public boolean checkearHora (String dia,String horaInicio,String horaFinal){
		int d = 8;
		if (dia.equalsIgnoreCase("lunes"))d=0;
		if (dia.equalsIgnoreCase("martes"))d=1;
		if (dia.equalsIgnoreCase("miércoles"))d=2;
		if (dia.equalsIgnoreCase("jueves"))d=3;
		if (dia.equalsIgnoreCase("viernes"))d=4;
		if (dia.equalsIgnoreCase("sábado"))d=5;
		if (dia.equalsIgnoreCase("domingo"))d=6;


		int horaIni = Integer.parseInt(horaInicio.replace(":",""));
		horaIni = ((horaIni-600)/100)*2;
		if ((Integer.parseInt(horaInicio.replace(":",""))%100)!=0) {
			horaIni++;
		}
		int horaFin = Integer.parseInt(horaFinal.replace(":",""));
		horaFin =((horaFin-600)/100)*2;
		if (Integer.parseInt(horaFinal.replace(":",""))%100!=0) {
			horaFin++;
		}
		for (int i = horaIni;i<horaFin;i++){

			if (horario [d][i] == 1){
				return false;
			}
		}
		return true;
	}

	public int getCapacidad(){
		if (this.capacidad.equals("N/A")){
			return 1000000;
		}else {
			return Integer.parseInt(this.capacidad);
		}
	}

	public int getAcceso(){
		return this.acceso;
	}
	public StringBuilder horarioToFile (){
		StringBuilder retorno= new StringBuilder("\n");
		retorno.append("\n"+this.idAula+": \n\n");
		retorno.append("lunes\t\t\t\tMartes\t\t\t\tMiércoles\t\t\t\tJueves\t\t\t\tViernes\t\t\t\tSábado\t\t\t\tDomingo\n");
		for (int i = 0;i<horarioAula[0].length;i++){
			for (int k =0;k<horarioAula.length;k++){
				retorno.append(horarioAula[k][i]+"\t\t\t\t");

			}
			retorno.append("\n");
		}
		return retorno;
	}
	public void imprimirHorario(){
		System.out.println();
		System.out.println(this.idAula+": \n");
		System.out.println("lunes\t\tMartes\t\t\tMiércoles\t\t\tJueves\t\t\tViernes\t\t\tSábado\t\t\tDomingo");
		for (int i = 0;i<horarioAula[0].length;i++){
			for (int k =0;k<horarioAula.length;k++){
				System.out.print(horarioAula[k][i]+"\t\t\t");

			}
			System.out.println();
		}

	}

}
