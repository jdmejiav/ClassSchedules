import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Prueba {

	public static void main (String args[]) {

		try{

			File file = new File ("sources/mat20192.csv");
			Scanner sc = new Scanner (file);
			String temp[];
			int num=0;
			while (sc.hasNext()){
				temp =sc.next().split(",");
				if (temp.length==3)num++;
			}
			System.out.println(num);
		}catch (FileNotFoundException e){
		}

	}

}
