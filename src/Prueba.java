import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Prueba {

	public static void main (String args[]) {
		try {
			File file = new File ("pa20192.csv");
			Scanner sc = new Scanner (file);
			int i=0;
			while (sc.hasNext()) {
				sc.next();
				i=i+1;
			}
			System.out.println(i);
		}catch (FileNotFoundException fnfe){
			System.exit(1);
		}

	}

}
