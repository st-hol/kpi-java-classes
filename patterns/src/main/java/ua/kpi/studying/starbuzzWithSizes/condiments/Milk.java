package ua.kpi.studying.starbuzzWithSizes.condiments;

import ua.kpi.studying.starbuzzWithSizes.beverages.Beverage;
import ua.kpi.studying.starbuzzWithSizes.condiments.CondimentDecorator;

public class Milk extends CondimentDecorator {
	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}

	public double cost() {
		return beverage.cost() + .10;
	}
}
