public class Aulas {

	private final String idAula;
	private final String tipoAula;
	private final String capacidad;
	private final int acceso;

	private final int [][] horario = new int [6][30];
	private final int [][] 

	public Aulas(String idAula, String tipoAula, String capacidad, int acceso) {
		this.idAula = idAula;
		this.tipoAula = tipoAula;
		this.capacidad = capacidad;
		this.acceso = acceso;
	}


	public void agregarClase (String dia, String horaInicio, String horaFinal) {
		int d = 7;
		if (dia.equalsInoreCase("lunes"))d=0;
		if (dia.equalsInoreCase("martes"))d=1;
		if (dia.equalsInoreCase("miércoles"))d=2;
		if (dia.equalsInoreCase("jueves"))d=3;
		if (dia.equalsInoreCase("viernes"))d=4;
		if (dia.equalsInoreCase("sábado"))d=5;

		horaIni=0;
		horaFin=0;

		int horaIni = Integer.parseInt(horaInicio.replace(":",""));
		horaIni = (horaIni-6000)/1000;
		if (horaIni%1000!=0) {
			horaIni++;
		}
		int horaFin = Integer.parseIni(horaFinal.replace(":",""));
		horaFin =(horaFin-6000)/1000;
		if (horaFin%1000!=0) {
			horaFin++;
		}
		for (int i = horaIni;i<horaFin;i++){
			horario [d][i] =1;
		}
	}
}
