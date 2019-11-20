package ua.kpi.studying.coffeefm;

import java.util.Observable;

import ua.kpi.studying.starbuzzWithSizes.beverages.Beverage;


/**
 * @deprecated Observable
 */
@Deprecated
public abstract class BeverageStore extends Observable {

    abstract Beverage makeBeverage(String item);

    public Beverage orderBeverage(String type) {
        Beverage beverage = makeBeverage(type);
        notifyThatDrinkIsBeingPrepared();
        return beverage;
    }

    //////obsrv/////
    public void notifyThatDrinkIsBeingPrepared() {
        setChanged();
        notifyObservers();
    }
}
