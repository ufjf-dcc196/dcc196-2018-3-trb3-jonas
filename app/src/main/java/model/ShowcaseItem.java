package model;

public class ShowcaseItem {
    private int showcaseItemId;
    private int candyId;
    private int quantity;

    public ShowcaseItem(int showcaseItemId, int candyId, int quantity) {
        this.showcaseItemId = showcaseItemId;
        this.candyId = candyId;
        this.quantity = quantity;
    }

    public int getShowcaseItemId() {
        return showcaseItemId;
    }

    public void setShowcaseItemId(int showcaseItemId) {
        this.showcaseItemId = showcaseItemId;
    }

    public int getCandyId() {
        return candyId;
    }

    public void setCandyId(int candyId) {
        this.candyId = candyId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
