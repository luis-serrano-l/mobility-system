package src.model;

public class Location {
    private double x;
    private double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distanceTo(Location other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location other = (Location) obj;
        return Double.compare(other.x, x) == 0 && Double.compare(other.y, y) == 0;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }

    @Override
    public int hashCode() {
        return 31 * (int)x + (int)y;
    }
} 