package Objects;

import Exceptions.InvalidSnackException;


public abstract class Snack {

    protected String snackID;
    protected String name;
    protected int basePrice;

    public Snack(String snackID, String name, int basePrice) throws InvalidSnackException {
//        if (basePrice > 0) {
//            this.snackID = snackID;
//            this.name = name;
//            this.basePrice = basePrice;
//
//        } else {
//            throw new InvalidSnackException("Invalid snack price");
//        }

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

    public void setSnackID(String snackID) {
        this.snackID = snackID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
}
