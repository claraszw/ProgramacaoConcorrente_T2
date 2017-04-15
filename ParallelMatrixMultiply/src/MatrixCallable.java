import java.util.concurrent.Callable;


public class MatrixCallable implements Callable<Double>{

	double[] row;
	double[] column;
	int N;
	
	public MatrixCallable(double[] row, double column[], int N){
		this.row=row;
		this.column=column;
		this.N=N;
	}
	@Override
	public Double call() throws Exception {
		
		double result=0;
		
		for(int i=0;i<N;i++){
			result+=row[i]*column[i];
		}
		return result;
	}

}
