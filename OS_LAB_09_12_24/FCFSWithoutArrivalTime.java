import java.util.Scanner;

class ProcessWithoutArrival {
    int processId;
    int burstTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;

    public ProcessWithoutArrival(int processId, int burstTime) {
        this.processId = processId;
        this.burstTime = burstTime;
    }
}

public class FCFSWithoutArrivalTime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        ProcessWithoutArrival[] processes = new ProcessWithoutArrival[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            processes[i] = new ProcessWithoutArrival(i + 1, burstTime);
        }

        int currentTime = 0;
        float totalWaitingTime = 0, totalTurnaroundTime = 0;

        for (ProcessWithoutArrival process : processes) {
            process.completionTime = currentTime + process.burstTime;
            process.turnaroundTime = process.completionTime;
            process.waitingTime = process.turnaroundTime - process.burstTime;

            currentTime += process.burstTime;
            totalWaitingTime += process.waitingTime;
            totalTurnaroundTime += process.turnaroundTime;
        }

        System.out.println("\nProcess\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (ProcessWithoutArrival process : processes) {
            System.out.println(process.processId + "\t\t" + process.burstTime + "\t\t" +
                    process.completionTime + "\t\t" + process.waitingTime + "\t\t" + process.turnaroundTime);
        }

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / n));

        sc.close();
    }
}
