package NewDayNewGame.Core;

import org.jetbrains.annotations.NotNull;

public class Vector2 {
    public double x, y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector) {
        if (vector == null) {
            return;
        }
        this.x = vector.x;
        this.y = vector.y;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2 normalized() {
        double len = magnitude();
        if (len == 0) {
            return new Vector2(0, 0);
        }
        return new Vector2(x / len, y / len);
    }

    public Vector2 multiply(double scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    public Vector2 add(Vector2 vector) {
        return new Vector2(this.x + vector.x, this.y + vector.y);
    }

    public Vector2 sub(Vector2 vector) {
        return new Vector2(this.x - vector.x, this.y - vector.y);
    }

    public double distance(@NotNull Vector2 vector) {
        return Math.sqrt(Math.pow(this.x - vector.x, 2) + Math.pow(this.y - vector.y, 2));
    }

    public static double distance(@NotNull Vector2 first, @NotNull Vector2 second) {
        return Math.sqrt(Math.pow(first.x - second.x, 2) + Math.pow(first.y - second.y, 2));
    }

    public double dot(@NotNull Vector2 vector) {
        return vector.x * this.x + vector.y * this.y;
    }

    public static double dot(@NotNull Vector2 first, @NotNull Vector2 second) {
        return first.x * second.x + first.y * second.y;
    }

    public Vector2 scale(@NotNull Vector2 vector) {
        return new Vector2(this.x * vector.x, this.y * vector.y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
