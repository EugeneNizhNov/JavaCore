//Задача 2: Работяга

package JavaCore.Lambda.Task2;

public class Main {
    public static void main(String[] args) {
        OnTaskDoneListener listener = System.out::println;
        OnTaskErrorListener listenerError = System.out::println;
        Worker worker = new Worker(listener, listenerError);
        worker.start();
    }
}
