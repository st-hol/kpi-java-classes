package ua.kpi.studying.starbuzzWithSizes.condiments;

import ua.kpi.studying.starbuzzWithSizes.beverages.Beverage;

public abstract class CondimentDecorator extends Beverage {
	public Beverage beverage;
	public abstract String getDescription();
	
	public Size getSize() {
		return beverage.getSize();
	}
}
