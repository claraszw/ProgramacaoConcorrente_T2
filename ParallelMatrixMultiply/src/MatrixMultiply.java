import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.*;


public class MatrixMultiply {
	  private static int NTHREADS = 10;
	  private static int N = 5;
	  private static String fileName="matrix.txt";
	  private static int nMatrix=10;
	  
	  public static void main(String args[]) throws InterruptedException, ExecutionException{
		ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
		Scanner scanner=null;
		Matrix Matrices[] = new Matrix[nMatrix];
		Matrix result = new Matrix(N);
		ArrayList<Future<Double>> futureResult = new ArrayList<Future<Double>>();
		
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long startTime = System.currentTimeMillis();
		  
		  for(int i=0;i<nMatrix;i++){
			  Matrices[i] = new Matrix(N);
			  Matrices[i].fillMatrixFile(scanner);
		  }
		  
		  for(int i=0;i<N;i++){
			  for(int j=0;j<N;j++){
				  Callable<Double> worker = new MatrixCallable(
						  Matrices[0].content[i],Matrices[1].getColumn(j),N);
				  
				  futureResult.add(executor.submit(worker));
			  }
		  }
		  
		  for(int i=0;i<N;i++){
			  for(int j=0;j<N;j++){
				  result.content[i][j] = futureResult.get(N*i+j).get(); 
			  }
		  }
		  
		  for(int k=2;k<nMatrix; k++){
			  
			  futureResult.clear();

			  for(int i=0;i<N;i++){
				  for(int j=0;j<N;j++){
					  Callable<Double> worker = new MatrixCallable(
							  result.content[i],Matrices[k].getColumn(j),N);
					  
					  futureResult.add(executor.submit(worker));
				  }
			  }
			  int count=0;

			  double[] results = new double[N*N];
			  for(Future<Double> future: futureResult){
				  results[count] = future.get();
				  count++;
			  }
			  
			  for(int i=0;i<N;i++){
				  for(int j=0;j<N;j++){
					  result.content[i][j] = results[N*i+j]; 
				  }
			  }
			  
			  
			
		  }
		  
		  result.printMatrix();
		  
		  long endTime = System.currentTimeMillis();
		  long totalTime = endTime-startTime;
		  
		  scanner.close();
		  executor.shutdown();
		  
		  System.out.println("Total time was:" + totalTime + " miliseconds");
		  
	  }
}
