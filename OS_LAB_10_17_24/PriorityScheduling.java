package OS_LAB_10_17_24;
import java.util.Arrays;
import java.util.Comparator;

class Process {
    int id;
    int burstTime;
    int priority;
    int waitingTime;
    int turnaroundTime;

    public Process(int id, int burstTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class PriorityScheduling {
    public static void main(String[] args) {
        Process[] processes = {
            new Process(1, 10, 3),
            new Process(2, 1, 1),
            new Process(3, 2, 4),
            new Process(4, 1, 5),
            new Process(5, 5, 2)
        };

        // Sort processes based on priority
        Arrays.sort(processes, Comparator.comparingInt(p -> p.priority));

        // Calculate waiting and turnaround times
        calculateTimes(processes);

        // Print results
        printResults(processes);
    }

    private static void calculateTimes(Process[] processes) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (int i = 0; i < processes.length; i++) {
            if (i == 0) {
                processes[i].waitingTime = 0;
            } else {
                processes[i].waitingTime = processes[i - 1].waitingTime + processes[i - 1].burstTime;
            }
            processes[i].turnaroundTime = processes[i].waitingTime + processes[i].burstTime;

            totalWaitingTime += processes[i].waitingTime;
            totalTurnaroundTime += processes[i].turnaroundTime;
        }

        System.out.println("\nAverage Waiting Time: " + (double) totalWaitingTime / processes.length);
        System.out.println("");
        System.out.println("Average Turnaround Time: " + (double) totalTurnaroundTime / processes.length);
        System.out.println("");
    }

    private static void printResults(Process[] processes) {
        System.out.println("Process ID" + "\t" + "Burst Time" + "\t" + "Priority" + "\t" + "Waiting Time" + "\t" + "Turnaround Time");
        for (Process process : processes) {
            System.out.println(process.id + "\t\t" + process.burstTime + "\t\t" + process.priority + "\t\t" + process.waitingTime +
            "\t\t" + process.turnaroundTime);
        }
    }
}