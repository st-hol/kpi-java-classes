package ua.kpi.studying.starbuzzWithSizes.condiments;

import ua.kpi.studying.starbuzzWithSizes.beverages.Beverage;
import ua.kpi.studying.starbuzzWithSizes.condiments.CondimentDecorator;

public class Whip extends CondimentDecorator {
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}
 
	public double cost() {
		return beverage.cost() + .10;
	}
}
