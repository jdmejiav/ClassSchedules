public class Aulas {

	private final String idAula;
	private final String tipoAula;
	private final String capacidad;
	private final int acceso;


	private final int [][] horario = new int [6][30];
	private final String [][] horarioAula = new String [60][60];

	public Aulas(String idAula, String tipoAula, String capacidad, int acceso) {
		this.idAula = idAula;
		this.tipoAula = tipoAula;
		this.capacidad = capacidad;
		this.acceso = acceso;
	}


	public void agregarClase (String dia, String horaInicio, String horaFinal, String aula) {
		int d = 7;
		if (dia.equalsIgnoreCase("lunes"))d=0;
		if (dia.equalsIgnoreCase("martes"))d=1;
		if (dia.equalsIgnoreCase("miércoles"))d=2;
		if (dia.equalsIgnoreCase("jueves"))d=3;
		if (dia.equalsIgnoreCase("viernes"))d=4;
		if (dia.equalsIgnoreCase("sábado"))d=5;

		int horaIni = Integer.parseInt(horaInicio.replace(":",""));
		horaIni = (horaIni-6000)/1000;
		if (horaIni%1000!=0) {
			horaIni++;
		}
		int horaFin = Integer.parseInt(horaFinal.replace(":",""));
		horaFin =(horaFin-6000)/1000;
		if (horaFin%1000!=0) {
			horaFin++;
		}
		for (int i = horaIni;i<horaFin;i++){
			horario [d][i] = 1;
			horarioAula [d][i] = aula;
		}

		

	}
}
