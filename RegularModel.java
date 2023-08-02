import java.util.ArrayList;

/*
 * This class handles the data, logic, and components of the RVM
 */
public class RegularModel {
	protected final int numItems = 13;
	protected ArrayList<ArrayList<Item>> itemArray; 
	protected ArrayList<Item> itemRecord;
	protected int[] changeDenominations = {100, 50, 20, 10, 5, 1};
	protected int[] changeStock;
	protected int[] tempChangeStock;
	protected ArrayList<Transactions> transactions;
	protected int earnings;
	
	/**
	 * RegularModel Constructor instantiating the data of items, change, transactions, and earnings
	 */
	public RegularModel(){
		itemArray = new ArrayList<ArrayList<Item>>(numItems);
		itemRecord = new ArrayList<Item>(numItems);
		changeStock = new int[6];
		tempChangeStock = new int[6];
		//tempNewHoldingChangeStock = new int[6];
		transactions = new ArrayList<Transactions>();
		earnings = 0;
		initializeChangeStock();
		addItemArrayLists();
		createVendItems();
		copyItemsToRecord();
	}
	
	/**
	 * Initializes the starting stock of change denominations
	 */
	protected void initializeChangeStock() {
		for(int i=0; i<changeStock.length; i++) {
			changeStock[i]=5;
		}
	}
	
	/**
	 * Increments the stock of a denomination
	 * @param index of stock of change to increment
	 */
	public void addChangeStock(int index) {
		changeStock[index]++;
		//tempNewHoldingChangeStock[index]++;
	}
	
	/**
	 * Testing purposes
	 */
	public void displayChangeStock() {
		for(int i=0; i<changeStock.length; i++)
			System.out.println(String.format("%d: %d", changeDenominations[i], changeStock[i]));
	}
	
	/**
	 * Copies items to an item record holding an the data of each item type
	 */
	protected void copyItemsToRecord() {
		for(int i=0; i<numItems; i++)
			this.itemRecord.add(this.itemArray.get(i).get(0));
	}
	
	/**
	 * Adds the item types to the item array with an initial stock
	 */
	public void createVendItems() {
		for(int i=0; i<numItems; i++)
			for(int j=0; j<5; j++)
				this.itemArray.get(i).add(addNewItemType(i));
	}
	
	/**
	 * Creates a new instance of an item and adds to the item array
	 * @param index of item type
	 * @param n - number of times to be added
	 */
	public void addNewItemInstance(int index, int n) {
		for(int i=0; i<n; i++)
			itemArray.get(index).add(addNewItemType(index));
		updateItemsPrice(index, this.itemRecord.get(index).getPrice());
	}
	
	/**
	 * Returns the type of item to be created
	 * @param i
	 * @return the type of item
	 */
	protected Item addNewItemType(int i) {
		Item temp = null;
		switch(i) {
		case 0: temp = new BananaItem("Banana", 110, 75, 5);
			break;
		case 1: temp = new LankaItem("Langka", 150, 30, 5);
			break;
		case 2: temp = new LecheFlanItem("Leche Flan", 180, 45, 5);
			break;
		case 3: temp = new MunggoItem("Monggo Beans", 70, 80, 5);
			break;
		case 4: temp = new NataItem("Nata", 200, 35, 5);
			break;
		case 5: temp = new PinipigItem("Pinipig", 90, 75, 5);
			break;
		case 6: temp = new RedSagoItem("Red Sago", 65, 20, 5);
			break;
		case 7: temp = new SabaItem("Saba", 120, 60, 5);
			break;
		case 8: temp = new UbeHalayaItem("Ube Halaya", 80, 5, 5);
			break;
		case 9: temp = new UbeIceCreamItem("Ube Ice Cream", 120, 100, 5);
			break;
		case 10: temp = new IceItem("Ice", 0, 5, 5);
			break;
		case 11: temp = new MilkItem("Milk", 40, 30, 5);
			break;
		case 12: temp = new HaloHaloItem("Halo Halo", 260, 160, 5);
		}
		return temp;
	}
	
	/**
	 * Initializes ArrayLists of items for the item array
	 */
	protected void addItemArrayLists() {
		for(int i=0; i<numItems; i++) {
			this.itemArray.add(new ArrayList<Item>());
		}
	}
	
	/**
	 * Returns a single ArrayList of items provided the index
	 * @param index of item
	 * @return item arrayList of specific type
	 */
	public ArrayList<Item> getSingleItemArrayList(int index){
		return itemArray.get(index);
	}
	
	/**
	 * Returns the entire item array holding all the types of items
	 * @return itemArray
	 */
	public ArrayList<ArrayList<Item>> getWholeItemArrayList(){
		return itemArray;
	}
	
	/**
	 * Returns the itemRecord holding an instance of each item
	 * @return itemRecord
	 */
	public ArrayList<Item> getItemRecord(){
		return itemRecord;
	}
	
	/**
	 * Returns the ArrayList of Transactions
	 * @return transactions
	 */
	public ArrayList<Transactions> getTransactions(){
		return this.transactions;
	}
	
	/**
	 * Adds a new Transaction
	 * @param item
	 * @param timesRestocked - number of times restocked
	 */
	public void addTransaction(Item item, int timesRestocked) {
		this.transactions.add(new Transactions(item, timesRestocked));
	}
	
	/**
	 * Returns an array of Strings comprised of names of each item for the ComboBoxes in Maintenance
	 * @return String array of item names
	 */
	public String[] getStringedItemRecord() {
		String[] temp = new String[numItems];
		for(int i=0; i<numItems; i++)
			temp[i] = itemRecord.get(i).getName();
		return temp;
	}
	
	/**
	 * Returns an array of Strings comprised of denominations for the ComboBoxes in Maintenance
	 * @return String array of denominations
	 */
	public String[] getStringedChangeDenominations() {
		String[] temp = new String[changeDenominations.length];
		for(int i=0; i<changeDenominations.length; i++)
			temp[i] = String.valueOf(changeDenominations[i]);
		return temp;
	}
	
	/**
	 * Returns the stock of change
	 * @return changeStock
	 */
	public int[] getChangeStock() {
		return this.changeStock;
	}
	
	/**
	 * Returns the number of items that exist in the VM
	 * @return numItems
	 */
	public int getNumItems() {
		return this.numItems;
	}
	
	/**
	 * Checks to see if any item is at 0 stock
	 * @return boolean value whether any item has 0 stock or not
	 */
	public boolean checkItemStock() {
		boolean check = true;
		for(ArrayList<Item> items : this.itemArray) {
			if(items.size()==0)
				check=false;
		}
		return check;
	}
	
	/**
	 * Removes an item from the item array
	 * @param i - index of item to be removed
	 */
	public void removeItem(int i) {
		this.itemArray.get(i).remove(0);
	}
	
	/**
	 * Locates the index of an item
	 * @param name - name of an item
	 * @return index of the item to be located
	 */
	public int locateItem(String name) {
		int i;
		for(i=0; i<numItems; i++)
			if(name.equals(itemRecord.get(i).getName()))
				break;
		return i;
	}
	
	/**
	 * Updates the prices of an item
	 * @param index of item's prices to be updated
	 * @param newPrice
	 */
	public void updateItemsPrice(int index, int newPrice) {
		for(int i=0; i<this.itemArray.get(index).size(); i++) {
			itemArray.get(index).get(i).setPrice(newPrice);
		}
		itemRecord.get(index).setPrice(newPrice);
	}
	
	/**
	 * Checks if the change available is valid for transaction to proceed
	 * @param userTotalCash
	 * @param itemIndex
	 * @return boolean value whether change is enough or not
	 */
	public boolean checkChange(int userTotalCash, int itemIndex) {
		int excess = userTotalCash - itemRecord.get(itemIndex).getPrice(); //temporary user's change
        boolean valid = true;
        
        //copies change stock for temporary usage
        for(int i=0; i<changeStock.length; i++)
            tempChangeStock[i]=this.changeStock[i];
        
        //subtracts all denominations from highest to lowest until excess is 0
        if(excess > 0){
            for(int i = 0; i < changeStock.length; i++){
                //subtracts available change of RVM from user's change
                while(excess>=changeDenominations[i] && tempChangeStock[i]>0 && excess>0){
                    excess -= changeDenominations[i];
                    tempChangeStock[i]-=1;
                }
            }
        }
        //if there is still change after all the machine runs out of change, transaction will not proceed
        if(excess > 0){
            valid = false;
            System.out.println("No Change...");
        }
        
        return valid;
	}

	/**
	 * Updates the changeStock if a transaction is made
	 */
	public void updateChangeStock() {
		for(int i=0; i<changeStock.length; i++) 
        	this.changeStock[i]=tempChangeStock[i];
	}
	
	/**
	 * Adds the earnings based on item bought
	 * @param itemIndex	
	 */
	public void addEarnings(int itemIndex) {
		this.earnings += this.itemRecord.get(itemIndex).getPrice();
	}
	
	/**
	 * Adds the earnings based on amount provided
	 * @param money - to be added
	 */
	public void addDirectEarnings(int amount) {
		this.earnings += amount;
	}
	
	/**
	 * Collects and resets current earnings
	 * @return total
	 */
	public int collectEarnings() {
		int total = this.earnings;
		this.earnings=0;
				
		return total;
	}
	
	/**
	 * Sets the stock of a denomination
	 * @param i - index of stock of denomination
	 * @param newChangeStock
	 */
	public void setChangeStockIndex(int i, int newChangeStock) {
		this.changeStock[i] = newChangeStock;
	}
}	