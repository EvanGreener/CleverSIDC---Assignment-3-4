package utils;

public class Utilities {
    // Finds the prime number closest to n between m and n using the well known
    // sieve of Eratosthenes algorithm
    public static int sieveOfEratosthenes(int n)
    {
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++)
        {
            if (prime[p])
            {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // For the purpose of the hash table, the prime should be as close to 2n as possible.
        // Therefore it starts at the end
        for (int i = n; i >= 2; i--){
            if (prime[i]){
                return (i);
            }
        }
        return -1; // This is impossible but the compiler is not aware of that.
    }

    public static int[] bucketSort(int[] array){
        //TODO
        return new int[2];
    }
}
