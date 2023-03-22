import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObjectPoolPatternTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        MyObject object = ObjectPoolPattern.getInstance().checkIn();
                        System.out.println("Object " + object.getId() + " checked out by thread " + Thread.currentThread().getId());
                        Thread.sleep(1000);
                        ObjectPoolPattern.getInstance().checkOut(object);
                        System.out.println("Object " + object.getId() + " checked in by thread " + Thread.currentThread().getId());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
