import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class Prime 
{
	public static final int maxValue = 100000000;
	public static final int numThreads = 8; 

	public static void main(String args[])
	{
		//The clock start and the program immediately jumps into the algorithm for finding primes
		long startTime = System.currentTimeMillis();
        	findPrime();
       		long endTime = System.currentTimeMillis();
        	long timeTook = (endTime - startTime);
        	//Once the prime algorthim is successfully execurted and finished, the clock stops
        	//Creating the output file doesnt work unless enclosed in a try-catch block
        	try
        	{
         		File myFile = new File("primeFile.txt");
         		myFile.createNewFile();
         		FileWriter myWriter = new FileWriter("primeFile.txt");

         		myWriter.write(timeTook + " milliseconds, Sum of primes: " + ClassForPrime.primeSum+ ", Count of primes: " + ClassForPrime.primeCount + "\n");
         		myWriter.close();
     		}
     		catch (IOException io)
     		{
     			System.out.println("[IOException]: ");
            		io.printStackTrace();
     		}
	}

	public static void findPrime()
	{
		//The thread length are as evenly divided as they can be by taking
		// the max number (10^8) divided by 8 threads
		int threadLength = maxValue / numThreads;
		//Start and end point of each thread
		long start = 1;
		long end = start + threadLength;
		//For each thread, the helper class ClassForPrime is created
		for(int i = 0; i < numThreads; i++)
		{
			Thread newThread = new Thread(new ClassForPrime(start, end));
			newThread.start();
		}
	}
}
//As i understand it, when using threads, the class has to be runnable?
class ClassForPrime implements Runnable
{
    //The helper class will determine whether a number is prime
    //and also keep track of the prime count and sum
    public static int primeSum = 0;
    public static long primeCount = 0;
    private final long start;
    private final long end;

    public ClassForPrime(long start, long end)
    {
    	this.start = start;
    	this.end = end;
    }
    //Made primePlus synchromized like the example in class to make it an atomic operation
    private synchronized void primePlus(long number)
    {
    	primeSum += number;
    }
    //algorithm for determining if the number is prime
    private static boolean isPrime(long l) {
        long x = (long) Math.floor(Math.sqrt(l));
        
        for (long i = 2; i <= x; i++) {
            if (l % i == 0)
                return false;
        }
        
        return true;
    }
    //algorithm for counting the number of primes as well as the sum
    private void numOfPrimes() 
    {
        primeCount = 0;
        for (long i = start; i <= end; i++) {
            if (isPrime(i))
                primeCount++;
        }
        primePlus(primeCount);
    }
    
    public void run() 
    {
    	//running the alogrithm above then telling the program to stop using the current thread
        numOfPrimes();
        Thread.yield();
    }
}




