import java.util.ArrayList;

/**
 * This class is the model of the special vending machine
 */
public class SpecialModel extends RegularModel{
    
	private ArrayList<TransactionList> transactionList;
	private final int numItems = 12;
	int payment[];

	/**
	 * This is the constructor of the special vending machine
	 */
    SpecialModel()
    {
		super();
		this.transactionList = new ArrayList<TransactionList>();
		this.payment = new int[6];
		resetPayment();
    }

	/**
	 * This class gets the names of all the products
	 * @return string array of all the names of products
	 */
	@Override
	public String[] getStringedItemRecord() {
		String[] temp = new String[numItems];
		for(int i=0; i<numItems; i++)
			temp[i] = itemRecord.get(i).getName();
		return temp;
	}

	/**
	 * This method checks whether there is enough stock for what the user wants
	 * @param buyItem the quantity the user wants
	 * @param i the index of the product
	 * @return boolean value to determine if stock is sufficient
	 */
	public boolean sufficientStock(int buyItem, int i)
	{
		boolean valid = true;
		if (buyItem > getWholeItemArrayList().get(i).size()-1) {
			valid = false;
		}
		return valid;
	}

	/**
	 * This updates the index array of cash the user inserted
	 * @param i index of the cash inserted by the user to the machine
	 */
	public void updatePayment(int i)
	{
		this.payment[i] += 1;
		for (int j = 0; j < 6; j++) {
			System.out.println(this.payment[j]);
		}
	}

	/**
	 * This resets the cash inserted in the machine
	 */
	public void resetPayment()
	{
		for (int i = 0; i < 6; i++) {
			this.payment[i] = 0;
		}
	}

	/**
	 * This computes for the total price of a transaction
	 * @param buyList This is the list of items the user is purchasing
	 * @return the price of the transaction
	 */
	public int computeTotalPrice(int[] buyList)
	{
		int totalPrice = 0;
		for (int i = 0; i < buyList.length; i++) {
			totalPrice += (buyList[i] * getItemRecord().get(i).getPrice());
		}
		return totalPrice;
	}

	/**
	 * This removes the item in the array list when the transaction was valid
	 * @param buyList the items the user purchased
	 */
	public void proceedTransaction(int[] buyList)
	{
		for (int i = 0; i < buyList.length; i++) {
			for (int j = 0; j < buyList[i]; j++) {
				removeItem(i);
			}
		}
	}

	/**
	 * Getter for the specific cash denomination inserted by user
	 * @param i index of the cash inserted array
	 * @return integer of how many was inserted
	 */
	public int getPayment(int i)
	{
		return this.payment[i];
	}

	/**
	 * This checks whether there is sufficient change in the machine
	 * @param userTotalCash the total cash inserted in the machine
	 * @param totalPrice the price of the transaction
	 * @return boolean value that determines whether transaction is valid
	 */
	@Override
	public boolean checkChange(int userTotalCash, int totalPrice)
	{
		int excess = userTotalCash - totalPrice; //temporary user's change
        boolean valid = true;
        
		for(int i=0; i<6; i++)
            System.out.println(changeDenominations[i] + " " + changeStock[i]);

        for(int i=0; i<6; i++)
            tempChangeStock[i]=changeStock[i];
        
        if(excess > 0){
            for(int i = 0; i < 6; i++){
                //subtracts available change of vending machine from user's change
                while(excess>=changeDenominations[i] && tempChangeStock[i]>0 && excess>0){
                    excess -= changeDenominations[i];
                    tempChangeStock[i]-=1;
                }
            }
        }
        //if there is still change after all the machine runs out of change, transaction will not proceed
        if(excess > 0){
            valid = false;
            System.out.println("No Change");
        }else
		{
			updateChangeStock();
			for (int index = 0; index < 6; index++) {
				System.out.println(changeDenominations[index] + " " + changeStock[index]);
			}
		}
        
        return valid;
	}

	/**
	 * This saves the transaction in the transactionList array
	 * @param buyList the items bought in 1 trasaction
	 * @param timesRestocked this checks when the last restock this was purchased
	 */
	public void saveTransaction(int[] buyList, int timesRestocked)
	{
		ArrayList<Item> temp = new ArrayList<Item>();
		
		for (int i = 0; i < buyList.length; i++) {
			for (int index = 0; index < buyList[i]; index++) {
				temp.add(itemRecord.get(i));
				//transactionList.get(i).add(new Transactions(itemRecord.get(i), timesRestocked));
			}
		}
		transactionList.add(new TransactionList(temp, timesRestocked));
	}

	/**
	 * This checks if the transaction contains standalone items
	 * @param buyList the items bought needed for the transaction
	 * @return boolean value to check if the transaction has standalone items
	 */
	public boolean checkStandAlone(int[] buyList)
	{
		boolean valid = true;
		int hasStandAlone = 0;

		if (buyList[10] > 0 || buyList[11] > 0) {
			for (int i = 0; i < 10; i++) {
				if(buyList[i] > 0){
					hasStandAlone++;
				}
			}

			if (hasStandAlone > 0) {
				valid = true;
			}else{
				valid = false;
			}
		}
			
		return valid;
	}

	/**
	 * This gets the receipt and the cooking instructions
	 * @param buyList the items bought by user
	 * @param totalPayment payment of the user
	 * @param totalPrice price of the items
	 * @return String of the receipt and cooking instructions
	 */
	public String getCookingInstruction(int[] buyList, int totalPayment, int totalPrice)
	{
		String instruction = "";
		String toppings = "";
		boolean isHaloHalo;

		for (int i = 0; i < buyList.length; i++) {
			if (buyList[i] > 0) {
				toppings += String.format("%2d%-14s%5s\n",buyList[i], itemRecord.get(i).getName(), buyList[i] * itemRecord.get(i).getPrice());
			}
		}

		if(buyList[10] > 0 || buyList[11] > 0)
		{
			instruction += String.format("%20s\n", "Vending Receipt: ");
			instruction += "1 Custom Halo Halo\n";
			instruction += toppings;
			isHaloHalo = true;
		}else
		{
			instruction += String.format("%-20s\n", "Vending Receipt: ");
			instruction += "Toppings\n";
			instruction += toppings;
			isHaloHalo = false;
		}

		instruction += String.format("%-20s%5d\n", "Total Price: ", totalPrice);
		instruction += String.format("%-20s%5d\n", "Total Payment: ", totalPayment);
		instruction += String.format("%-15s%5d\n\n", "Total Change: ", totalPayment - totalPrice);

		if (isHaloHalo) {
			if (buyList[10] > 0) {
				instruction += String.format("%-30s\n","Shaving the " + itemRecord.get(10).getName());
			}
			if (buyList[11] > 0) {
				instruction += String.format("%-30s\n","Pouring the " + itemRecord.get(11).getName());
			}

			for (int i = 0; i < 10; i++) {
				if (buyList[i] > 0) {
					instruction += String.format("%-30s\n","Adding the " + itemRecord.get(i).getName());
				}
			}
		}else
		{
			for (int i = 0; i < 10; i++) {
				if (buyList[i] > 0) {
					instruction += String.format("%-30s\n","Dispensing the " + itemRecord.get(i).getName());
				}
			}
		}
		return instruction;
	}

	/**
	 * getter for the arraylist of transactionlist
	 * @return the arraylist of transactionlist
	 */
	public ArrayList<TransactionList> getTransactionList() {
		return transactionList;
	}
}
