# COP4520_HW1
My program solution to COP 4520 Homework 1

Open your preferred command terminal. Compile the the program using ‘javac Prime.java’
When it compile correctly, then input ‘java Prime’ to have the program execute. 
(You may have to exit the run yourself, on my system I use ‘control C’)
One the program runs, an output file named primeFile.txt will be saved to your computer which will have 
The execution time, the sum of primes and the count of primes.

This program runs by taking 8 threads of numbers and running them each individually in a for loop where the helper class  calculates the sum of primes and the count of primes and then returns back into the loop to work with the next thread. Once this has all been calculated, the program returns to main to create the output text file.
