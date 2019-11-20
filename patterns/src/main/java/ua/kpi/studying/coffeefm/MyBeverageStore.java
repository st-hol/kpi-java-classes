package ua.kpi.studying.coffeefm;

import ua.kpi.studying.starbuzzWithSizes.beverages.Beverage;
import ua.kpi.studying.starbuzzWithSizes.beverages.DarkRoast;
import ua.kpi.studying.starbuzzWithSizes.beverages.Espresso;
import ua.kpi.studying.starbuzzWithSizes.beverages.HouseBlend;
import ua.kpi.studying.starbuzzWithSizes.condiments.Mocha;
import ua.kpi.studying.starbuzzWithSizes.condiments.Soy;
import ua.kpi.studying.starbuzzWithSizes.condiments.Whip;

public class MyBeverageStore extends BeverageStore {

	Beverage makeBeverage(String item) {
		switch (item) {
			case "Espresso":
				return new Espresso();
			case "DarkRoast":
				return new Whip(new Mocha(new Mocha(new DarkRoast())));
			case "HouseBlend":
				return new Whip(new Mocha(new Soy(new HouseBlend()) {{
					setSize(Size.VENTI);
				}}));
			default:
				return null;
		}
	}
}
