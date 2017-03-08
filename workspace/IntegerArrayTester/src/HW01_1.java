
import java.io.*;
public class HW01_1 {
	public static void main(String[] args){
		 File f = new File(args[0]);
		 File [] array = f.listFiles();
		 double p=0;
		for(int i =0;i<array.length;i++){
			if(array[i].getName().endsWith(".txt"))
			p+=array[i].length();
		}
		System.out.println("Average file size of .txt files in " +f.getAbsolutePath()+" is " + p/array.length + " bytes");
	}

}
