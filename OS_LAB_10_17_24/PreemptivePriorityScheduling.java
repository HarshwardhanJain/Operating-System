package OS_LAB_10_17_24;
import java.util.*;

class PriorityProcess {
    int id;
    int burstTime;
    int remainingTime;
    int priority;
    int waitingTime;
    int turnaroundTime;
    int arrivalTime;
    int completionTime;

    public PriorityProcess(int id, int burstTime, int priority, int arrivalTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
    }
}

public class PreemptivePriorityScheduling {
    public static void main(String[] args) {
        PriorityProcess[] processes = {
            new PriorityProcess(1, 10, 3, 0),
            new PriorityProcess(2, 1, 1, 2),
            new PriorityProcess(3, 2, 4, 1),
            new PriorityProcess(4, 1, 5, 3),
            new PriorityProcess(5, 5, 2, 4)
        };

        // Sort processes based on arrival time
        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

        // Calculate waiting and turnaround times
        calculateTimes(processes);

        printResults(processes);
    }

    private static void calculateTimes(PriorityProcess[] processes) {
        int currentTime = 0;
        int completed = 0;
        int n = processes.length;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        while (completed != n) {
            PriorityProcess currentProcess = null;
            int highestPriority = Integer.MAX_VALUE;

            for (PriorityProcess process : processes) {
                if (process.arrivalTime <= currentTime && process.remainingTime > 0 && process.priority < highestPriority) {
                    highestPriority = process.priority;
                    currentProcess = process;
                }
            }

            if (currentProcess == null) {
                currentTime++;
                continue;
            }

            currentProcess.remainingTime--;
            currentTime++;

            if (currentProcess.remainingTime == 0) {
                completed++;
                currentProcess.completionTime = currentTime;
                currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;

                totalWaitingTime += currentProcess.waitingTime;
                totalTurnaroundTime += currentProcess.turnaroundTime;
            }
        }

        System.out.println("\nAverage Waiting Time: " + (double) totalWaitingTime / n);
        System.out.println("Average Turnaround Time: " + (double) totalTurnaroundTime / n);
    }

    private static void printResults(PriorityProcess[] processes) {
        System.out.println("Process ID" + "\t" + "Burst Time" + "\t" + "Priority" + "\t" + "Arrival Time" + "\t" + "Waiting Time" + "\t" + "Turnaround Time");
        for (PriorityProcess process : processes) {
            System.out.println(process.id + "\t\t" + process.burstTime + "\t\t" + process.priority + "\t\t" + process.arrivalTime + "\t\t" + process.waitingTime +
            "\t\t" + process.turnaroundTime);
        }
    }
}