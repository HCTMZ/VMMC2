import java.util.ArrayList;

public class TransactionList {
        private ArrayList<Item> item;
        private int timesRestocked;
        
        /**
         * This is the constructor for transactionList
         * @param item array of items on a single transaction
         * @param timesRestocked this determines whether the transaction happened during the previous restocking
         */
        public TransactionList(ArrayList<Item> item, int timesRestocked){
            this.item = item;
            this.timesRestocked = timesRestocked;
        }
       
        /**
         * This gets the item of a single transaction
         * @return item of a transaction
         */
        public ArrayList<Item> getItem() {
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
