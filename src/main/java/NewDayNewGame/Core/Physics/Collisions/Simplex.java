package NewDayNewGame.Core.Physics.Collisions;
import NewDayNewGame.Core.Vector2;
import java.util.ArrayList;

public class Simplex {
    private ArrayList<Vector2> points;

    public Simplex() {
        points = new ArrayList<>(3);
    }

    public void add(Vector2 point) {
        points.add(point);
    }

    public Vector2 calculateDirection() {
        Vector2 a = points.get(points.size() - 1);
        Vector2 aInv = a.multiply(-1);

        if (points.size() == 3) {
            Vector2 b = points.get(1);
            Vector2 c = points.get(0);

            Vector2 ab = b.sub(a);
            Vector2 ac = c.sub(a);

            Vector2 abPerp = new Vector2(ab.y, -ab.x);

            if (abPerp.dot(c) >= 0) {
                abPerp = abPerp.multiply(-1);
            }

            if (abPerp.dot(aInv) > 0) {
                points.remove(0);
                return abPerp;
            }

            Vector2 acPerp = new Vector2(ac.y, -ac.x);

            if (acPerp.dot(b) >= 0) {
                acPerp = acPerp.multiply(-1);
            }

            if (acPerp.dot(aInv) > 0) {
                points.remove(1);
                return acPerp;
            }

            return null;
        }

        Vector2 b = points.get(0);
        Vector2 ab = b.sub(a);
        Vector2 abPerp = new Vector2(ab.y, -ab.x);

        if (abPerp.dot(aInv) <= 0) {
            abPerp = abPerp.multiply(-1);
        }
        return abPerp;
    }
}
