import java.util.*;
public class Euclid {
   public static int euclid(int a ,int b){
	   if(b>a){
		   return euclid(b,a);
	   }
	   if(a%b==0){
		   return b;
	   }
	   return euclid (b,a%b);
   }
	public static void main(String [] args){
	   Scanner sc= new Scanner(System.in);
	   int a=sc.nextInt();
	   int b= sc.nextInt();
	   System.out.println(euclid(a,b));
	   sc.close();
   }
}
