class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

public class UseCase14 {

    static class PassengerBogie {
        String type;
        int capacity;

        PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.type = type;
            this.capacity = capacity;
        }

        public String toString() {
            return type + " (" + capacity + " seats)";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        try {
            PassengerBogie b1 = new PassengerBogie("Sleeper", 72);
            PassengerBogie b2 = new PassengerBogie("AC Chair", 64);

            System.out.println("Valid Bogies Created:");
            System.out.println("  " + b1);
            System.out.println("  " + b2);

            // Invalid cases
            PassengerBogie b3 = new PassengerBogie("First Class", 0);

        } catch (InvalidCapacityException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        try {
            PassengerBogie b4 = new PassengerBogie("Luxury", -10);
        } catch (InvalidCapacityException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        System.out.println("System continues safely...");
    }
}
