package src.model.locations;

public class Location {
    private double x;
    private double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getY() {
        return (int) y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distanceTo(Location other) {
        double distanceX = this.x - other.getX();
        double distanceY = this.y - other.getY();
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }
}