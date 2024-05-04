package Objects;

import Exceptions.InvalidSnackException;


public abstract class Snack {

    protected String snackID;
    protected String name;
    protected int basePrice;

    public Snack(String snackID, String name, int basePrice) throws InvalidSnackException {
        this.snackID = snackID;
        this.name = name;
        this.basePrice = basePrice;
    }

    public abstract int calculatePrice();

    public String getSnackID() {
        return snackID;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }

}
