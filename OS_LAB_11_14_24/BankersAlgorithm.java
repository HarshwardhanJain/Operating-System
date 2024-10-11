package OS_LAB_11_14_24;

import java.util.Arrays;

class Banker {
    private final int numProcesses;
    private final int numResources;
    private final int[] available;
    private final int[][] maximum;
    private final int[][] allocation;
    private final int[][] need;

    public Banker(int numProcesses, int numResources, int[] available, int[][] maximum, int[][] allocation) {
        this.numProcesses = numProcesses;
        this.numResources = numResources;
        this.available = available;
        this.maximum = maximum;
        this.allocation = allocation;
        this.need = new int[numProcesses][numResources];

        // Calculate the need matrix
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                need[i][j] = maximum[i][j] - allocation[i][j];
            }
        }
    }

    public boolean isSafe() {
        boolean[] finish = new boolean[numProcesses];
        int[] work = Arrays.copyOf(available, available.length);

        while (true) {
            boolean found = false;
            for (int i = 0; i < numProcesses; i++) {
                if (!finish[i] && canProceed(i, work)) {
                    for (int j = 0; j < numResources; j++) {
                        work[j] += allocation[i][j];
                    }
                    finish[i] = true;
                    found = true;
                }
            }
            if (!found) {
                break;
            }
        }

        for (boolean f : finish) {
            if (!f) {
                return false;
            }
        }
        return true;
    }

    private boolean canProceed(int process, int[] work) {
        for (int j = 0; j < numResources; j++) {
            if (need[process][j] > work[j]) {
                return false;
            }
        }
        return true;
    }

    public boolean requestResources(int process, int[] request) {
        System.out.println("Process " + process + " requesting resources: " + Arrays.toString(request));
        for (int j = 0; j < numResources; j++) {
            if (request[j] > need[process][j] || request[j] > available[j]) {
                return false;
            }
        }

        // Pretend to allocate the resources
        for (int j = 0; j < numResources; j++) {
            available[j] -= request[j];
            allocation[process][j] += request[j];
            need[process][j] -= request[j];
        }

        // Check if the state is safe
        if (isSafe()) {
            return true;
        } else {
            // Rollback
            for (int j = 0; j < numResources; j++) {
                available[j] += request[j];
                allocation[process][j] -= request[j];
                need[process][j] += request[j];
            }
            return false;
        }
    }

    public void releaseResources(int process, int[] release) {
        System.out.println("Process " + process + " releasing resources: " + Arrays.toString(release));
        for (int j = 0; j < numResources; j++) {
            available[j] += release[j];
            allocation[process][j] -= release[j];
            need[process][j] += release[j];
        }
    }

    public void printState() {
        System.out.println("\nAvailable: " + Arrays.toString(available));
        System.out.println("Allocation | Maximum | Need");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print(Arrays.toString(allocation[i]) + " | ");
            System.out.print(Arrays.toString(maximum[i]) + " | ");
            System.out.println(Arrays.toString(need[i]));
        }
        System.out.println();
    }
}

public class BankersAlgorithm {
    public static void main(String[] args) {
        int numProcesses = 5;
        int numResources = 3;

        int[] available = {3, 3, 2};

        int[][] maximum = {
            {7, 5, 3},
            {3, 2, 2},
            {9, 0, 2},
            {2, 2, 2},
            {4, 3, 3}
        };

        int[][] allocation = {
            {0, 1, 0},
            {2, 0, 0},
            {3, 0, 2},
            {2, 1, 1},
            {0, 0, 2}
        };

        Banker banker = new Banker(numProcesses, numResources, available, maximum, allocation);

        System.out.println("\n1) Initial State:");
        banker.printState();

        int[] request = {1, 0, 2};
        int process = 1;

        if (banker.requestResources(process, request)) {
            System.out.println("Request granted.");
        } else {
            System.out.println("Request denied.");
        }

        System.out.println("\n2) State after request:");
        banker.printState();

        banker.releaseResources(process, request);
        System.out.println("\n3) State after release:");
        banker.printState();
    }
}