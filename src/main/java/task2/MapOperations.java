package task2;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public class MapOperations implements Callable<Boolean> {
    private final Map<Integer, Integer> map;
    private final long mapSize;
    private final Random rnd = new Random();
    private final boolean readWrite;

    public MapOperations(Map<Integer, Integer> map, long mapSize, boolean readWrite) {
        this.map = map;
        this.mapSize = mapSize;
        this.readWrite = readWrite;
    }

    public boolean write() {
        int value;
        for (int i = 1; i < mapSize; i++) {
            value = rnd.nextInt(100);
            map.put(i, value);
        }
        return true;
    }

    public void read() {
        for (int i = 1; i < mapSize; i++) {
            map.get(i);
        }
    }

    @Override
    public Boolean call() {
        if (readWrite) {
            write();
        } else {
            read();
        }
        return true;
    }
}
