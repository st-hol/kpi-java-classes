package ua.kpi.studying.starbuzzWithSizes.condiments;

import ua.kpi.studying.starbuzzWithSizes.beverages.Beverage;
import ua.kpi.studying.starbuzzWithSizes.condiments.CondimentDecorator;

public class Mocha extends CondimentDecorator {
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}
 
	public double cost() {
		return beverage.cost() + .20;
	}
}
