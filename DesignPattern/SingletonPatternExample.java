public class SingletonPatternExample {
    static class Logger {
        private static Logger instance;
        private Logger() {
        }

        public static synchronized Logger getInstance() {
            if (instance == null) {
                instance = new Logger();
            }
            return instance;
        }

        public void log(String message) {
            System.out.println("[Logger] " + message);
        }
    }

    public static void main(String[] args) {
        Logger firstLogger = Logger.getInstance();
        Logger secondLogger = Logger.getInstance();

        firstLogger.log("Starting application...");
        secondLogger.log("Continuing application execution...");

        System.out.println("Same Logger instance? " + (firstLogger == secondLogger));
    }
}
