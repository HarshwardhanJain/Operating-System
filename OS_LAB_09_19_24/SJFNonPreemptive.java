import java.util.Scanner;

class ProcessSJF implements Comparable<ProcessSJF> {
    int processId;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;

    public ProcessSJF(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    // Sort by arrival time, then by burst time for SJF
    @Override
    public int compareTo(ProcessSJF other) {
        if (this.arrivalTime == other.arrivalTime) {
            return this.burstTime - other.burstTime;
        }
        return this.arrivalTime - other.arrivalTime;
    }
}

public class SJFNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        ProcessSJF[] processes = new ProcessSJF[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            processes[i] = new ProcessSJF(i + 1, arrivalTime, burstTime);
        }

        // Sort processes by arrival time
        java.util.Arrays.sort(processes);

        int currentTime = 0;
        float totalWaitingTime = 0, totalTurnaroundTime = 0;

        boolean[] completed = new boolean[n];

        for (int i = 0; i < n; i++) {
            int shortest = -1;
            for (int j = 0; j < n; j++) {
                if (!completed[j] && processes[j].arrivalTime <= currentTime) {
                    if (shortest == -1 || processes[j].burstTime < processes[shortest].burstTime) {
                        shortest = j;
                    }
                }
            }

            if (shortest == -1) {
                currentTime++;
                i--;
                continue;
            }

            ProcessSJF process = processes[shortest];
            currentTime += process.burstTime;
            process.completionTime = currentTime;
            process.turnaroundTime = process.completionTime - process.arrivalTime;
            process.waitingTime = process.turnaroundTime - process.burstTime;

            totalWaitingTime += process.waitingTime;
            totalTurnaroundTime += process.turnaroundTime;
            completed[shortest] = true;
        }

        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (ProcessSJF process : processes) {
            System.out.println(process.processId + "\t\t" + process.arrivalTime + "\t\t" + process.burstTime + "\t\t" +
                    process.completionTime + "\t\t" + process.waitingTime + "\t\t" + process.turnaroundTime);
        }

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / n));

        sc.close();
    }
}
