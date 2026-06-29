import java.util.ArrayList;
import java.util.List;

public class FinancialForecasting {
    public static List<Double> forecastRecursive(List<Double> historicalRevenue, int monthsToForecast) {
        if (historicalRevenue == null || historicalRevenue.size() < 2 || monthsToForecast < 1) {
            return List.of();
        }

        double averageGrowthRate = calculateAverageGrowthRate(historicalRevenue);
        List<Double> forecast = new ArrayList<>();
        double lastRevenue = historicalRevenue.get(historicalRevenue.size() - 1);
        forecastRecursiveHelper(lastRevenue, averageGrowthRate, monthsToForecast, forecast);
        return forecast;
    }

    private static void forecastRecursiveHelper(double currentRevenue, double growthRate, int monthsLeft, List<Double> result) {
        if (monthsLeft == 0) {
            return;
        }
        double nextRevenue = currentRevenue * (1 + growthRate);
        result.add(nextRevenue);
        forecastRecursiveHelper(nextRevenue, growthRate, monthsLeft - 1, result);
    }

    private static double calculateAverageGrowthRate(List<Double> historicalRevenue) {
        double totalGrowth = 0;
        for (int i = 1; i < historicalRevenue.size(); i++) {
            double previous = historicalRevenue.get(i - 1);
            double current = historicalRevenue.get(i);
            totalGrowth += (current - previous) / previous;
        }
        return totalGrowth / (historicalRevenue.size() - 1);
    }

    public static void main(String[] args) {
        List<Double> historicalRevenue = List.of(9500.0, 9800.0, 10100.0, 10450.0, 10800.0, 11300.0);
        List<Double> forecast = forecastRecursive(historicalRevenue, 3);

        System.out.println("Historical revenue:");
        for (int i = 0; i < historicalRevenue.size(); i++) {
            System.out.printf("Month %d: $%.2f%n", i + 1, historicalRevenue.get(i));
        }

        System.out.println("\nRecursive forecast for next months:");
        for (int i = 0; i < forecast.size(); i++) {
            System.out.printf("Month %d forecast: $%.2f%n", historicalRevenue.size() + i + 1, forecast.get(i));
        }
    }
}
