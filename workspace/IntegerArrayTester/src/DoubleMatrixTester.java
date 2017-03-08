import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

class InsufficientElementsException extends Exception{
	public InsufficientElementsException(String s){
		super(String.format("%s", s));
	}
};
class InvalidRowNumberException extends Exception{
	public InvalidRowNumberException(String s){
		super(String.format("%s", s));
	}
};
class InvalidColumnNumberException extends Exception{
	public InvalidColumnNumberException(String s){
		super(String.format("%s", s));
	}
};
final class DoubleMatrix{
private  double [][] matrix;
private  int a;
private  int b;
    public DoubleMatrix (){
    	matrix=null;
    	a=0;
    	b=0;
    }
    public DoubleMatrix(double [] matrix,int a , int b) throws InsufficientElementsException {
        int counter=0;
        try{
        
            if((a*b)>matrix.length){
            throw new InsufficientElementsException("Insufficient Elements");
           //   DoubleMatrix();
            }
        }
        finally {
        	
                this.a=a;
                this.b=b;
        //int counter=0;
                for(int i =0;i<a;i++){
                    for(int j =0 ;j<b;j++){
                     
                    this.matrix[i][j]=matrix[counter];
                        counter++;
                    }
                
                }
        	
        }
        
    
    
    }
    
    public String getDimensions(){
    
    return String.format("[%d,%d]\n",a,b);
    }
    
    public int rows(){
    return a;
    }	
    
    public int columns(){
    return b;
    }
    
    @SuppressWarnings("finally")
	public double maxElementAtRow(int row) throws InvalidRowNumberException{
        try{
            if(row >a){
            throw new InvalidRowNumberException("Invalid row number");
            }
        }
        finally {
        	double max=matrix[row][0];
            for(int i =0;i<b;i++){
            
                if(matrix[row][i]>max){
                 max=matrix[row][i];
                }
            }
            return max;
        }
        
        //return max;
    
    }
    
    @SuppressWarnings("finally")
	public double maxElementAtColumn(int column) throws InvalidColumnNumberException{
    
        try{
            if(column>b){
            throw new InvalidColumnNumberException("Invalid Column Number");
            }
        
        }
        finally {
        	 double max=matrix[0][column];
             for(int i =0;i<a;i++){
             
                 if(matrix[i][column]>max){
                 max=matrix[i][column];
                 }
             }
             return max;
        }
        
       
    }
    public double sum(){
    double sum=0;
        for(int i =0;i<a;i++){
            for(int j =0 ; j<b;j++){
             sum+=matrix[i][j];
            
            }
        }
        
        return sum;
    
    }
    
    public double [] toSortedArray(){
     double [] array = new double [a*b];
        int counter=0;
        for(int i =0;i<a;i++){
        
            for(int j=0;j<b;j++){
             array[counter]=matrix[i][j];
                counter++;
            
            }
        }
        for(int i =0;i<array.length;i++){
        	
        	for(int j=0;j<array.length-1;j++){
        		if(array[j]>array[j+1])
        		{
        			double tmp=array[j];
        			array[j]=array[j+1];
        			array[j+1]=tmp;
        		}
        	
   
        	}
        }
   
    return array;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		result = prime * result + Arrays.deepHashCode(matrix);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoubleMatrix other = (DoubleMatrix) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		if (!Arrays.deepEquals(matrix, other.matrix))
			return false;
		return true;
	}
	@Override
    public String toString(){
		String str="";
		for(int i =0;i<a;i++){
			
			for(int j =0;j<b;j++){
				str+=matrix[i][j] + "\t";
				if(i==a && j==b){
					str+="\n";
				}
			}
		}
		return str;
	}
};
class MatrixReader {
	public static DoubleMatrix read(InputStream input) throws InsufficientElementsException{
		Scanner scan = new Scanner (input);
		int a= scan.nextInt();
		int b=scan.nextInt();
	    double [] matrix = new double [a*b];
		for(int i =0;i<a*b;i++){
			matrix[i]=scan.nextDouble();
			
		}
		scan.close();
		//DoubleMatrix d=null;
		 
     
    	  DoubleMatrix d= new DoubleMatrix(matrix,a,b);
    	  return d;
     
        
       
	}
};
public class DoubleMatrixTester {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        DoubleMatrix fm = null;

        double[] info = null;

        DecimalFormat format = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            String operation = scanner.next();

            switch (operation) {
                case "READ": {
                    int N = scanner.nextInt();
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    double[] f = new double[N];

                    for (int i = 0; i < f.length; i++)
                        f[i] = scanner.nextDouble();

                    try {
                        fm = new DoubleMatrix(f, R, C);
                        info = Arrays.copyOf(f, f.length);

                    } catch (InsufficientElementsException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }

                    break;
                }

                case "INPUT_TEST": {
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    StringBuilder sb = new StringBuilder();

                    sb.append(R + " " + C + "\n");

                    scanner.nextLine();

                    for (int i = 0; i < R; i++)
                        sb.append(scanner.nextLine() + "\n");

                    fm = MatrixReader.read(new ByteArrayInputStream(sb
                            .toString().getBytes()));

                    info = new double[R * C];
                    Scanner tempScanner = new Scanner(new ByteArrayInputStream(sb
                            .toString().getBytes()));
                    tempScanner.nextDouble();
                    tempScanner.nextDouble();
                    for (int z = 0; z < R * C; z++) {
                        info[z] = tempScanner.nextDouble();
                    }

                    tempScanner.close();

                    break;
                }

                case "PRINT": {
                    System.out.println(fm.toString());
                    break;
                }

                case "DIMENSION": {
                    System.out.println("Dimensions: " + fm.getDimensions());
                    break;
                }

                case "COUNT_ROWS": {
                    System.out.println("Rows: " + fm.rows());
                    break;
                }

                case "COUNT_COLUMNS": {
                    System.out.println("Columns: " + fm.columns());
                    break;
                }

                case "MAX_IN_ROW": {
                    int row = scanner.nextInt();
                    try {
                        System.out.println("Max in row: "
                                + format.format(fm.maxElementAtRow(row)));
                    } catch (InvalidRowNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "MAX_IN_COLUMN": {
                    int col = scanner.nextInt();
                    try {
                        System.out.println("Max in column: "
                                + format.format(fm.maxElementAtColumn(col)));
                    } catch (InvalidColumnNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "SUM": {
                    System.out.println("Sum: " + format.format(fm.sum()));
                    break;
                }

                case "CHECK_EQUALS": {
                    int val = scanner.nextInt();

                    int maxOps = val % 7;

                    for (int z = 0; z < maxOps; z++) {
                        double work[] = Arrays.copyOf(info, info.length);

                        int e1 = (31 * z + 7 * val + 3 * maxOps) % info.length;
                        int e2 = (17 * z + 3 * val + 7 * maxOps) % info.length;

                        if (e1 > e2) {
                            double temp = work[e1];
                            work[e1] = work[e2];
                            work[e2] = temp;
                        }

                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(work, fm.rows(),
                                fm.columns());
                        System.out
                                .println("Equals check 1: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    if (maxOps % 2 == 0) {
                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(new double[]{3.0, 5.0,
                                7.5}, 1, 1);

                        System.out
                                .println("Equals check 2: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    break;
                }

                case "SORTED_ARRAY": {
                    double[] arr = fm.toSortedArray();

                    String arrayString = "[";

                    if (arr.length > 0)
                        arrayString += format.format(arr[0]) + "";

                    for (int i = 1; i < arr.length; i++)
                        arrayString += ", " + format.format(arr[i]);

                    arrayString += "]";

                    System.out.println("Sorted array: " + arrayString);
                    break;
                }

            }

        }

        scanner.close();
    }
}