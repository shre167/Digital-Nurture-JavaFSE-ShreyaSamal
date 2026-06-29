import java.util.ArrayList;
import java.util.List;

public class ObserverPatternExample {
    interface Observer {
        void update(String stockSymbol, double price);
    }

    interface Stock {
        void registerObserver(Observer observer);
        void deregisterObserver(Observer observer);
        void notifyObservers();
    }

    static class StockMarket implements Stock {
        private final List<Observer> observers = new ArrayList<>();
        private String symbol;
        private double price;

        public StockMarket(String symbol) {
            this.symbol = symbol;
        }

        public void setPrice(double price) {
            this.price = price;
            notifyObservers();
        }

        @Override
        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void deregisterObserver(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(symbol, price);
            }
        }
    }

    static class MobileApp implements Observer {
        @Override
        public void update(String stockSymbol, double price) {
            System.out.println("MobileApp received update: " + stockSymbol + " is now $" + price);
        }
    }

    static class WebApp implements Observer {
        @Override
        public void update(String stockSymbol, double price) {
            System.out.println("WebApp received update: " + stockSymbol + " is now $" + price);
        }
    }

    public static void main(String[] args) {
        StockMarket appleStock = new StockMarket("AAPL");
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        appleStock.registerObserver(mobileApp);
        appleStock.registerObserver(webApp);

        appleStock.setPrice(173.45);
        appleStock.setPrice(175.10);
    }
}
