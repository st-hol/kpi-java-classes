package ua.kpi.studying.obsrvr;

import java.util.Observable;
import java.util.Observer;

import ua.kpi.studying.coffeefm.BeverageStore;
import ua.kpi.studying.starbuzzWithSizes.beverages.Beverage;

/**
 * @deprecated Observer
 */
@Deprecated
public class CurrentConditionsDisplay implements Observer {

	Observable observable;
	
	public CurrentConditionsDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof BeverageStore) {
//			BeverageStore beverageStore = (BeverageStore)obs;
			System.out.println("OBSERVED: drink was prepared");
			display();
		}
	}
	
	public void display() {
	}
}
