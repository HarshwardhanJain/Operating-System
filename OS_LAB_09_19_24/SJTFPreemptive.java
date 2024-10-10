import java.util.Scanner;

class ProcessSJTF {
    int processId;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;
    boolean isCompleted = false;

    public ProcessSJTF(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class SJTFPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        ProcessSJTF[] processes = new ProcessSJTF[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            processes[i] = new ProcessSJTF(i + 1, arrivalTime, burstTime);
        }

        int currentTime = 0, completed = 0;
        float totalWaitingTime = 0, totalTurnaroundTime = 0;
        int minRemainingTime = Integer.MAX_VALUE;
        int shortestProcessIndex = -1;
        boolean isProcessInProgress = false;

        while (completed != n) {
            // Find the process with the shortest remaining time
            for (int i = 0; i < n; i++) {
                if (processes[i].arrivalTime <= currentTime && !processes[i].isCompleted &&
                        processes[i].remainingTime < minRemainingTime) {
                    minRemainingTime = processes[i].remainingTime;
                    shortestProcessIndex = i;
                    isProcessInProgress = true;
                }
            }

            if (!isProcessInProgress) {
                currentTime++;
                continue;
            }

            // Execute the shortest process
            processes[shortestProcessIndex].remainingTime--;
            minRemainingTime = processes[shortestProcessIndex].remainingTime;
            if (minRemainingTime == 0) {
                minRemainingTime = Integer.MAX_VALUE;
            }

            if (processes[shortestProcessIndex].remainingTime == 0) {
                processes[shortestProcessIndex].completionTime = currentTime + 1;
                processes[shortestProcessIndex].turnaroundTime = processes[shortestProcessIndex].completionTime - processes[shortestProcessIndex].arrivalTime;
                processes[shortestProcessIndex].waitingTime = processes[shortestProcessIndex].turnaroundTime - processes[shortestProcessIndex].burstTime;

                totalWaitingTime += processes[shortestProcessIndex].waitingTime;
                totalTurnaroundTime += processes[shortestProcessIndex].turnaroundTime;

                processes[shortestProcessIndex].isCompleted = true;
                completed++;
            }

            currentTime++;
        }

        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (ProcessSJTF process : processes) {
            System.out.println(process.processId + "\t\t" + process.arrivalTime + "\t\t" + process.burstTime + "\t\t" +
                    process.completionTime + "\t\t" + process.waitingTime + "\t\t" + process.turnaroundTime);
        }

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / n));

        sc.close();
    }
}
