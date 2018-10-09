package Main.Operation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * use array to implement a history of chess moves
 */
public class Record {
    private ArrayList<Object[]> record;

    public Record() {
        this.record = new ArrayList<>();
    }

    public void writeRecord(Object[] strArr) {
        record.add(strArr);
    }

    public Object[] lastMove() {
        return record.get(record.size() - 1);
    }

    public void removeLast() {
        record.remove(record.size() - 1);
    }

    public int size() {
        return record.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object[] strArr: record) {
            sb.append(Arrays.asList(strArr).toString()).append(" ");
        }
        return sb.toString();
    }
}
