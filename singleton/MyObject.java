public class MyObject {
    private static int count = 0;
    private int id;

    public MyObject() {
        count++;
        id = count;
    }

    public int getId() {
        return id;
    }
}
