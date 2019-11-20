package ua.kpi.studying;

import ua.kpi.studying.coffeefm.BeverageStore;
import ua.kpi.studying.coffeefm.MyBeverageStore;
import lombok.extern.slf4j.Slf4j;
import ua.kpi.studying.obsrvr.CurrentConditionsDisplay;
import ua.kpi.studying.starbuzzWithSizes.beverages.Beverage;

@Slf4j
public class Main {

    public static void main(String[] args) {

        BeverageStore myBeverageStore = new MyBeverageStore();
        CurrentConditionsDisplay display = new CurrentConditionsDisplay(myBeverageStore);

        Beverage beverage = myBeverageStore.orderBeverage("Espresso");
        System.out.println("Client ordered a " + beverage.getDescription() + "\n");


        beverage = myBeverageStore.orderBeverage("DarkRoast");
        System.out.println("Client ordered a " + beverage.getDescription() + "\n");

        beverage = myBeverageStore.orderBeverage("HouseBlend");
        System.out.println("Client ordered a " + beverage.getDescription() + "\n");

    }

}
