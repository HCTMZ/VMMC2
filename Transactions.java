/**
 * This class handles the details of a single Transaction
 */
public class Transactions{
    private Item item;
    private int timesRestocked;
    
    /**
     * This is the constructor for Transactions
     * @param item item of the transaction
     * @param timesRestocked this determines whether the transaction happened during the previous restocking
     */
    public Transactions(Item item, int timesRestocked){
        this.item = item;
        this.timesRestocked = timesRestocked;
    }
   
    /**
     * This gets the item of a single transaction
     * @return item of a transaction
     */
    public Item getItem() {
        return item;
    }

    /**
     * This adds to the total number of times a product has been restocked
     * @return the number of times an item has been restocked
     */
    public int getTimesRestocked() {
        return timesRestocked;
    }
}