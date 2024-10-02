import java.util.Scanner;

public class Time {
    private short hours;
    private short minutes;
    private short seconds;

    public Time() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    public Time(short hours, short minutes, short seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        normalizeTime();
    }

    public Time(String timeStr) {
        String[] parts = timeStr.split(":");
        this.hours = Short.parseShort(parts[0]);
        this.minutes = Short.parseShort(parts[1]);
        this.seconds = Short.parseShort(parts[2]);
        normalizeTime();
    }

    public Time(int totalSeconds) {
        this.hours = (short) (totalSeconds / 3600);
        this.minutes = (short) ((totalSeconds % 3600) / 60);
        this.seconds = (short) (totalSeconds % 60);
    }

    public int toSeconds() {
        return hours * 3600 + minutes * 60 + seconds;
    }

    public int toMinutes() {
        return Math.round(toSeconds() / 60.0f);
    }

    public void addSeconds(int sec) {
        int totalSeconds = this.toSeconds() + sec;
        this.hours = (short) (totalSeconds / 3600);
        this.minutes = (short) ((totalSeconds % 3600) / 60);
        this.seconds = (short) (totalSeconds % 60);
        normalizeTime();
    }

    public void subtractSeconds(int sec) {
        int totalSeconds = this.toSeconds() - sec;
        if (totalSeconds < 0) totalSeconds = 0;
        this.hours = (short) (totalSeconds / 3600);
        this.minutes = (short) ((totalSeconds % 3600) / 60);
        this.seconds = (short) (totalSeconds % 60);
        normalizeTime();
    }

    public int differenceInSeconds(Time other) {
        return Math.abs(this.toSeconds() - other.toSeconds());
    }

    public boolean equals(Time other) {
        return this.hours == other.hours && this.minutes == other.minutes && this.seconds == other.seconds;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void normalizeTime() {
        if (this.seconds >= 60) {
            this.minutes += this.seconds / 60;
            this.seconds = (short) (this.seconds % 60);
        }
        if (this.minutes >= 60) {
            this.hours += this.minutes / 60;
            this.minutes = (short) (this.minutes % 60);
        }
    }

    public static Time inputTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть час у форматі (години хвилини секунди):");
        short hours = scanner.nextShort();
        short minutes = scanner.nextShort();
        short seconds = scanner.nextShort();
        return new Time(hours, minutes, seconds);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Ввести час вручну");
        System.out.println("2. Ввести час у вигляді рядка (формат ГГ:ХХ:СС)");
        System.out.println("3. Ввести час у секундах");
        System.out.print("Зробіть свій вибір: ");
        int choice = scanner.nextInt();
        Time t1 = null;

        switch (choice) {
            case 1:
                t1 = inputTime();
                break;
            case 2:
                System.out.print("Введіть час у форматі ГГ:ХХ:СС: ");
                String timeStr = scanner.next();
                t1 = new Time(timeStr);
                break;
            case 3:
                System.out.print("Введіть час у секундах: ");
                int totalSeconds = scanner.nextInt();
                t1 = new Time(totalSeconds);
                break;
            default:
                System.out.println("Неправильний вибір.");
                return;
        }

        System.out.println("Час: " + t1);

        System.out.println("1. Додати секунди");
        System.out.println("2. Відняти секунди");
        System.out.println("3. Перевести в секунди");
        System.out.println("4. Перевести в хвилини");
        System.out.println("5. Порівняти з іншим часом");
        System.out.println("Зробіть свій вибір: ");
        int operation = scanner.nextInt();

        switch (operation) {
            case 1:
                System.out.print("Скільки секунд додати? ");
                int secondsToAdd = scanner.nextInt();
                t1.addSeconds(secondsToAdd);
                System.out.println("Оновлений час: " + t1);
                break;
            case 2:
                System.out.print("Скільки секунд відняти? ");
                int secondsToSubtract = scanner.nextInt();
                t1.subtractSeconds(secondsToSubtract);
                System.out.println("Оновлений час: " + t1);
                break;
            case 3:
                System.out.println("Час у секундах: " + t1.toSeconds());
                break;
            case 4:
                System.out.println("Час у хвилинах: " + t1.toMinutes());
                break;
            case 5:
                System.out.println("Введіть інший час для порівняння.");
                Time t2 = inputTime();
                if (t1.equals(t2)) {
                    System.out.println("Часи рівні.");
                } else {
                    System.out.println("Часи не рівні.");
                }
                break;
            default:
                System.out.println("Неправильний вибір.");
                break;
        }

        scanner.close();
    }
}
