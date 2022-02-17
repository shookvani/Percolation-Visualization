import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {

    
        private double[] values;
        private double cirticalValue=1.96;
        private int numberOfTrials;
    
    
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        values= new double[trials];
        for (int i=0; i<trials; i++) {
            
            Percolation x= new Percolation(n);
            while (!x.percolates()) {
                int row=StdRandom.uniform(0, n);
                int col=StdRandom.uniform(0,n);
                x.open(row,col);
         
         }
         values[i]=(double) x.numberOfOpenSites()/(n*n);
        }
        numberOfTrials=trials;
    }

    // sample mean of percolation threshold
    public double mean() {
        
       return 	StdStats.mean(values); 
        
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return 	StdStats.stddev(values);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean()- cirticalValue *(stddev()/Math.sqrt(numberOfTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + cirticalValue *(stddev()/Math.sqrt(numberOfTrials));
    }

   // test client (see below)
   public static void main(String[] args) {
        int n=Integer.parseInt(args[0]);
       int T=Integer.parseInt(args[1]);
       System.out.println(n);
       System.out.println(T);
       try {
           long startTime=System.currentTimeMillis();
           PercolationStats statistics = new PercolationStats(n, T);
            long elapsedTime= System.currentTimeMillis()-startTime;

            System.out.println(String.format("mean=%f",statistics.mean()));
           System.out.println(String.format("stddev=%f",statistics.stddev()));
           System.out.println(String.format("cofindenceLow=%f",statistics.confidenceLow()));
           System.out.println(String.format("confidenceHigh=%f",statistics.confidenceHigh()));
           System.out.println(String.format("elasped time=%d",elapsedTime));
       }
       catch (Exception e) {
           System.out.println(e.getMessage());
       }
       
       
    }

}