public class DecoratorPatternExample {
    interface Notifier {
        void send(String message);
    }

    static class EmailNotifier implements Notifier {
        @Override
        public void send(String message) {
            System.out.println("Email sent: " + message);
        }
    }

    abstract static class NotifierDecorator implements Notifier {
        protected final Notifier notifier;

        protected NotifierDecorator(Notifier notifier) {
            this.notifier = notifier;
        }
    }

    static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            notifier.send(message);
            System.out.println("SMS sent: " + message);
        }
    }

    static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            notifier.send(message);
            System.out.println("Slack message sent: " + message);
        }
    }

    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        Notifier smsNotifier = new SMSNotifierDecorator(notifier);
        Notifier slackSmsNotifier = new SlackNotifierDecorator(smsNotifier);

        slackSmsNotifier.send("System update completed.");
    }
}
