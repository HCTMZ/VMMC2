/**
 * This class handles the details of a certain product
 */

public abstract class Item {
    private String name;
    private int calories;
    private int price;
    private int startingStock;
    private int totalRestocked;

    /**
     * @param name Name of product
     * @param calories Calories of the product
     * @param price Price of item
     * @param stock used for starting and ending stock
     * @param slotCode location of the product in the vending machine
     */
    public Item(String name, int calories, int price, int stock){
        this.name=name;
        this.calories=calories;
        this.price=price;
        this.startingStock = stock;
        this.totalRestocked = stock;
    }

    /**
     * This adds to the total number of times a product has been restocked
     * @param newStock the new value of stock
     */
    public void addTotalRestock(int newStock){
        this.totalRestocked += newStock;
    }

    /**
     * This gets the name of the product
     * @return name of product
     */
    public String getName(){
        return this.name;
    }

    /**
     * This gets the calories of product
     * @return calories of product
     */
    public int getCalories(){
        return calories;
    }

    /**
     * This gets the price of product
     * @return price of product
     */
    public int getPrice(){
        return this.price;
    }

    /**
     * This gets the stock before restocking
     * @return the stock before restocking
     */
    public int getStartingStock(){
        return this.startingStock;
    }

    /**
     * This gets the total number of times a product has been restocked
     * @return total times product has been restocked
     */
    public int getTotalRestocked(){
        return this.totalRestocked;
    }
    
    /**
     * This sets the new price of item
     * @param newPrice new price of item
     */
    public void setPrice(int newPrice)
    {
        this.price = newPrice;
    }

    /**
     * This sets the starting stock of the item
     * @param newStock the stock total added after restocking
     */
    public void setStartingStock(int newStock)
    {
        this.startingStock = newStock;
    }

    /**
     * This returns a string with the product details
     * @return the string with product details
     */
    public String toString(){
        return this.name + " " + this.calories + "kCal $" + this.price;
    }
}
