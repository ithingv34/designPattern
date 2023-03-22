import java.util.Calendar;

public class SingletonTest {
    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
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
