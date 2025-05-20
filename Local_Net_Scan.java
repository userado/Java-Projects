import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class scannernetwork {
    private static final int TIMEOUT = 1000; // timeout in milliseconds
    private static final int THREAD_COUNT = 10; // number of threads

    public static void main(String[] args) {
    	{
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Enter subnet (e.g., 192.168.1.): ");
                String subnet = scanner.nextLine();
                scanSubnet(subnet);
            } finally {
                scanner.close();
                System.out.println("Scanner closed.");
                }
            }//try
    }//main

    public static void scanSubnet(String subnet) {
        System.out.println("Starting scan of subnet: " + subnet);

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 1; i <= 254; i++) {
            String host = subnet + i;
            executor.submit(() -> {
                try {
                    InetAddress inetAddress = InetAddress.getByName(host);
                    if (inetAddress.isReachable(TIMEOUT)) {
                        System.out.println("Host " + host + " is reachable.");
                    }
                } catch (IOException e) {
                    System.err.println("Error scanning host: " + host);
                }
            });
        }

        executor.shutdown();
        try {
            if (executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Scan complete.");
            } else {
                System.err.println("Scan did not complete within the timeout period. Forcing shutdown...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.err.println("Scan was interrupted.");
        }
    }
}

