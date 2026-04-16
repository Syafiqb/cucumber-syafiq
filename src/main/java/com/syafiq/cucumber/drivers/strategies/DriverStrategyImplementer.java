package com.syafiq.cucumber.drivers.strategies;


public class DriverStrategyImplementer {

    public static DriverStrategy chooseStrategy(String strategy) {
        String baseStrategy = strategy.toLowerCase();
        if (baseStrategy.contains("chrome")) {
            return new Chrome();
        } else if (baseStrategy.contains("firefox")) {
            return new Safari();
        } else {
            return null;
        }
    }
}
