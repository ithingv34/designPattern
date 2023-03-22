import java.util.Calendar;

public class SingletonTest {
    public static void main(String[] args) {
        // Create multiple threads that call the getInstance() method
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    Singleton singleton = Singleton.getInstance();
                    System.out.println("Thread " + Thread.currentThread().getId() + " Singleton instance hash code: " + singleton.hashCode());
                }
            });
            thread.start();
        }
    }
}
