package lab5.client.data;

import java.util.Objects;

import lab5.client.exceptions.IncorrectData;

/**
 * X-Y coordinates of Space Marine.
 */
public class Coordinates {
    private double x;
    private Long y; //Поле не может быть null

    public Coordinates(double x, Long y) throws IncorrectData {
        setX(x);
        setY(y);
    }

    public Coordinates() {};

    /**
     * @return Y-coordinate.
     */
    public Long getY() {
        return y;
    }

    /**
     * @return X-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Set Y-coordinate.
     * @param y Y-coordinate.
     * @throws IncorrectData
     */
    public void setY(Long y) throws IncorrectData {
        if (y == null) {
            throw new IncorrectData();
        }
        this.y = y;
    }

    /**
     * Set Y-coordinate.
     * @param x X-coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Coordinates: X - " + x + " | Y - " + y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }
}