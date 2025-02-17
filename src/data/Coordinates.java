package data;
import utility.*;

public class Coordinates implements Validate{
    private double x; //Максимальное значение поля: 613
    private Double y; //Поле не может быть null

    public Coordinates(double x, Double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean validate() {
        if (x > 613) return false;
        return y != null;
    }
    @Override
    public String toString() {
        return x + ";" + y;
    }

    public double getX() {
        return x;
    }
    public Double getY() {
        return y;
    }
}
