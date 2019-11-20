package ua.kpi.studying.starbuzzWithSizes.beverages;

import ua.kpi.studying.starbuzzWithSizes.beverages.Beverage;

public class Decaf extends Beverage {
	public Decaf() {
		description = "Decaf Coffee";
	}
 
	public double cost() {
		return 1.05;
	}
}

