import java.util.ArrayList;
import java.util.List;

// 한정된 숫자의 객체를 유지하는 패턴
//
public class ObjectPoolPattern {
    private static final int MAX_OBJECTS = 2;
    private static ObjectPoolPattern instance = null;
    private final List<MyObject> idleObjects; // 현재 체크아웃할 수 있는 개체
    private final List<MyObject> busyObjects; // 현재 사용 중인 개체

    private ObjectPoolPattern() {
        idleObjects = new ArrayList<>(MAX_OBJECTS);
        busyObjects = new ArrayList<>(MAX_OBJECTS);
        for (int i = 0; i < MAX_OBJECTS; i++) {
            idleObjects.add(new MyObject());
        }
    }

    public synchronized static ObjectPoolPattern getInstance() {
        if (instance == null) {
            return new ObjectPoolPattern();
        }
        return instance;
    }

    // busyObjects 리스트에서 지정된 객체를 제거하고 idleObjects에 추가
    public synchronized void checkOut(MyObject object) {
        if (busyObjects.remove(object)) {
            idleObjects.add(object);
        }
    }

    // idleObjects 리스트에서 사용 가능한 객체를 검색하여 busyObject 리스트에 추가
    // 사용 가능한 객체가 없으면 예외 발생
    public synchronized MyObject checkIn() throws Exception {
        if (idleObjects.isEmpty()) {
            throw new Exception("모든 개체가 사용 중입니다.");
        } else {
            MyObject object = idleObjects.remove(0);
            busyObjects.add(object);
            return object;
        }
    }

}
