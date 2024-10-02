import java.util.Scanner;

public class Circle {
    private double radius; 
    private double centerX; 
    private double centerY; 

    public Circle(double radius, double centerX, double centerY) {
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public double circumference() {
        return 2 * Math.PI * radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public boolean isPointInside(double x, double y) {
        double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
        return distanceSquared <= Math.pow(radius, 2);
    }

    public int intersectionPoints(Circle other) {
        double distanceBetweenCenters = Math.sqrt(Math.pow(other.centerX - this.centerX, 2) +
                                                  Math.pow(other.centerY - this.centerY, 2));
        double radiusSum = this.radius + other.radius;
        double radiusDiff = Math.abs(this.radius - other.radius);

        if (distanceBetweenCenters > radiusSum || distanceBetweenCenters < radiusDiff) {
            return 0;
        }
        else if (distanceBetweenCenters == radiusSum || distanceBetweenCenters == radiusDiff) {
            return 1;
        }
 
        else {
            return 2;
        }
    }

    public static Circle inputCircle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть радіус кола: ");
        double radius = scanner.nextDouble();
        System.out.print("Введіть координати центра кола (X Y): ");
        double centerX = scanner.nextDouble();
        double centerY = scanner.nextDouble();
        return new Circle(radius, centerX, centerY);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Створіть перше коло:");
        Circle circle1 = inputCircle();

        System.out.println("Довжина кола: " + circle1.circumference());
        System.out.println("Площа круга: " + circle1.area());

        System.out.print("Введіть координати точки (X Y) для перевірки належності колу: ");
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        if (circle1.isPointInside(x, y)) {
            System.out.println("Точка належить кругу.");
        } else {
            System.out.println("Точка не належить кругу.");
        }

        System.out.println("Створіть друге коло для перевірки перетину:");
        Circle circle2 = inputCircle();
        int intersections = circle1.intersectionPoints(circle2);
        System.out.println("Кількість точок перетину між колами: " + intersections);

        scanner.close();
    }
}
