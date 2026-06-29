public class AdapterPatternExample {
    interface PaymentProcessor {
        void processPayment(double amount);
    }

    static class StripeGateway {
        public void makePayment(double amount) {
            System.out.println("Stripe processing payment of $" + amount);
        }
    }

    static class PayPalGateway {
        public void sendPayment(double amount) {
            System.out.println("PayPal processing payment of $" + amount);
        }
    }

    static class StripeAdapter implements PaymentProcessor {
        private final StripeGateway stripeGateway;

        public StripeAdapter(StripeGateway stripeGateway) {
            this.stripeGateway = stripeGateway;
        }

        @Override
        public void processPayment(double amount) {
            stripeGateway.makePayment(amount);
        }
    }

    static class PayPalAdapter implements PaymentProcessor {
        private final PayPalGateway payPalGateway;

        public PayPalAdapter(PayPalGateway payPalGateway) {
            this.payPalGateway = payPalGateway;
        }

        @Override
        public void processPayment(double amount) {
            payPalGateway.sendPayment(amount);
        }
    }

    public static void main(String[] args) {
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        PaymentProcessor paypal = new PayPalAdapter(new PayPalGateway());

        stripe.processPayment(250.00);
        paypal.processPayment(99.99);
    }
}
