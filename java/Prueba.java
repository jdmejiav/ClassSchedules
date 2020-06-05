import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;


public class Prueba {

	public static void main (String args[]) {
		try{
			File file = new File ("mat20192.csv");
			Scanner sc = new Scanner (file);
			int i=0;
			while (sc.hasNext()) {
				String temp = sc.next();
				System.out.println(temp.substring(temp.indexOf(",")+1,temp.lastIndexOf(",")));
			}
			System.out.println(i);
		}catch (FileNotFoundException fnfe){
		}
	}

}
