package OS_LAB_10_24_24;

import java.util.LinkedList;
import java.util.Queue;

class Process {
    int id;
    int burstTime;
    int remainingTime;
    int waitingTime;
    int turnaroundTime;

    public Process(int id, int burstTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        int timeQuantum = 2;
        Process[] processes = {
            new Process(1, 10),
            new Process(2, 4),
            new Process(3, 5),
            new Process(4, 3)
        };

        // Calculate waiting and turnaround times
        calculateTimes(processes, timeQuantum);

        // Print results
        printResults(processes);
    }

    private static void calculateTimes(Process[] processes, int timeQuantum) {
        Queue<Process> queue = new LinkedList<>();
        for (Process process : processes) {
            queue.add(process);
        }

        int currentTime = 0;
        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();
            if (currentProcess.remainingTime > timeQuantum) {
                currentTime += timeQuantum;
                currentProcess.remainingTime -= timeQuantum;
                queue.add(currentProcess);
            } else {
                currentTime += currentProcess.remainingTime;
                currentProcess.remainingTime = 0;
                currentProcess.turnaroundTime = currentTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
            }
        }
    }

    private static void printResults(Process[] processes) {
        System.out.println("Process ID\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Process process : processes) {
            System.out.println(process.id + "\t\t" + process.burstTime + "\t\t" + process.waitingTime + "\t\t" + process.turnaroundTime);
        }

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        for (Process process : processes) {
            totalWaitingTime += process.waitingTime;
            totalTurnaroundTime += process.turnaroundTime;
        }

        System.out.println("\nAverage Waiting Time: " + totalWaitingTime / processes.length);
        System.out.println("\nAverage Turnaround Time: " + totalTurnaroundTime / processes.length);
        System.out.println();
    }
}