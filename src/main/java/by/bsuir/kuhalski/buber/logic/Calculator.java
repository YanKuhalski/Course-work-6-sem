package by.bsuir.kuhalski.buber.logic;

import by.bsuir.kuhalski.buber.model.Discount;
import by.bsuir.kuhalski.buber.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:tariffs.properties")
public class Calculator {
    @Autowired
    private Environment environment;

    private static final String TARIFF = "tariff";
    private static final String CALL_TARIFF = "call.tariff";
    private static final String DRIVER_TARIFF = "driver.tariff";

    public double calculateCost(Trip trip) {
        double price = new Double(environment.getProperty(CALL_TARIFF));
        int start = trip.getStartRegion().getValue();
        int end = trip.getEndRegion().getValue();
        double discount =trip.getDiscount().getValue().doubleValue();
        price = price + Math.abs(start - end) * (new Double(environment.getProperty(TARIFF)) * discount);
        return price;
    }

    public Double calculateProfit(Trip trip) {
        double driverTariff = new Double(environment.getProperty(DRIVER_TARIFF));
        double profit = calculateCost(trip)*driverTariff;
        return profit;
    }
}
