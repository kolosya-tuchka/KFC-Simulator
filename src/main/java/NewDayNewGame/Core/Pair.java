package NewDayNewGame.Core;

import java.io.Serializable;

public class Pair<T1, T2> implements Serializable {
    public T1 first;
    public T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pair<?, ?>)) {
            return false;
        }
        Pair<T1, T2> pair = (Pair<T1, T2>) (other);

        return (pair.first.equals(first) && pair.second.equals(second)) || (pair.first.equals(second) && pair.second.equals(first));
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
