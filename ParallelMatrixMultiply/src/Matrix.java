import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.Callable;


public class Matrix {
	
	public double[][] content;
	int N;
	
	public Matrix(int N){
		this.N=N;
		content = new double[N][N];
	}
	
	public void fillMatrixFile(Scanner source){
		
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				content[i][j] = source.nextDouble();
			}
		}
	}
	
	public double[] getColumn(int j){
		
		double[] column= new double[N];
		
		for(int i=0;i<N;i++){
			column[i] = content[i][j];
		}
		return column;
		
	}
	
	public void printMatrix(){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				System.out.print(content[i][j]+"  ");
			}
			System.out.println();
		}
	}
	
	
	public void printMatrixFile(PrintWriter printer){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				printer.print(content[i][j]+"  ");
			}
			printer.println();
		}
		
		printer.close();
	}
	
}
