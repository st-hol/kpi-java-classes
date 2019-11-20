package ua.kpi.studying.starbuzzWithSizes.beverages;

import ua.kpi.studying.starbuzzWithSizes.beverages.Beverage;

public class Espresso extends Beverage {
  
	public Espresso() {
		description = "Espresso";
	}
  
	public double cost() {
		return 1.99;
	}
}

