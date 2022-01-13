
// HARJEET SINGH YADAV
// CSAI - 2020561
// AP BONUS LAB - 21/12/2021


// I have used memoization approach to optimize the solution of the problem in O(n) time complexity 
// it stores the previous results and saves us form overhead of calculating the result again and again

// NOTE 
// Ans will in 0 based  indexing 
// i.e    1     this is row 0  & col 0
//      1   1   this is row 1  & cot 0 and 1

import java.util.concurrent.*;
import java.util.Scanner;

public class Pascal extends RecursiveTask<Integer> {

    int n,k;
    static volatile int dp[][] = new int[50][50];       // memoization  & its volatile because i want updated value all the time
    public Pascal(int _n, int _k) {
        n=_n; k = _k ; 
    }

    public Integer compute() {              // compute method for resucsive task 

        if(n<=0 || k<=0 || n==k){

            if(n>=0 && k>=0 )dp[n][k]=1;
            return 1;

        }
        if(n<0 | k<0){
            System.out.println("thsi");
        }
        Integer r,l;
        int flag = 0;
        Pascal  left = null;

        if(dp[n-1][k-1]==0){

            flag = 1;
            left = new Pascal(this.n-1, this.k -1);           // creating a left task if there is a need otherwise using previoud results
            left.fork();

        }

        if(dp[n-1][k]==0){

            Pascal right = new Pascal(this.n-1, this.k);       // creating right task  if there is a need otherwise using previoud results
            r=right.compute();

        }else r = dp[n-1][k];

        if(flag==1)l=left.join();
        else l = dp[n-1][k-1];
        
        synchronized(this){ dp[n][k] = r+l; }       // synchroized this code because we don't want any race condition in this code otherwise it will alter the result

        return l+r;

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the no of threads : ");
        int thread;
        thread = sc.nextInt();

        ForkJoinPool pool = new ForkJoinPool(thread);     // creatin the thread pool 

        System.out.print("\nEnter value of n : ");
        int n = sc.nextInt();
        System.out.print("Enter value of k : ");
        int k = sc.nextInt();

        Pascal task = new Pascal(n,k);
        dp[0][0]=1;                             // base conditon for the memoization 
        
        int result = pool.invoke(task);         //  ivokint the task 

        System.out.println("\nResult -> " +result);     // printting the result 
      
    }

}