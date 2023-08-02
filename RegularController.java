import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

/*
 * This class acts as a bridge between the Vending Machine's GUIs and its logic and data
 */
public class RegularController{
	
	private MainMenu menu;
	private RegularView vendView;
	private RegularModel vendModel;
	private Maintenance maintenance;
	
	/**
	 * This is the constructor for regular vending machine controller
	 * @param menu Main menu view
	 * @param vendView regular vending machine view
	 * @param vendModel model for regular vending machine
	 * @param maintenance view for maintenance
	 */
	public RegularController(MainMenu menu, RegularView vendView, RegularModel vendModel, Maintenance maintenance){
		this.menu = menu;
		this.vendView = vendView;
		this.vendModel = vendModel;
		this.maintenance = maintenance;
		
		initiateMaintenanceActionListeners();
		initiateVendViewActionListeners();
	}
	
	/**
	 * initiates the listeners for maintenance
	 */
	public void initiateMaintenanceActionListeners() {
		this.maintenance.setMaintenanceBackBtnActionListenener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hideMaintenance();
				menu.revealMainMenu();
			}
		});
		
		this.maintenance.setRestockItemsConfirmBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printReceipt();
				int index = maintenance.getRestockItemsCBSelectedIndex();
				if(index==12) {
					for(int i=0; i<vendModel.getNumItems(); i++) {
						vendModel.addNewItemInstance(i, maintenance.getRestockItemCount());
						vendModel.getItemRecord().get(i).addTotalRestock(maintenance.getRestockItemCount());
						if(i!=10 && i!=11) {
							if(vendModel.getSingleItemArrayList(i).size()>0)
								vendView.enableBtn(i);
						}
						maintenance.setRestockItemsCurStockLabel(String.format("Current Stock: %d", vendModel.getSingleItemArrayList(i).size()));
					}
				}
				else{
					vendModel.addNewItemInstance(index, maintenance.getRestockItemCount());
					vendModel.getItemRecord().get(index).addTotalRestock(maintenance.getRestockItemCount());
					if(index!=10 && index!=11)
						if(vendModel.getSingleItemArrayList(index).size()>0)
							vendView.enableBtn(index);
					maintenance.setRestockItemsCurStockLabel(String.format("Current Stock: %d", vendModel.getSingleItemArrayList(index).size()));
				}
				for(int k = 0; k < vendModel.getNumItems(); k++){
					vendModel.getItemRecord().get(k).setStartingStock(vendModel.getSingleItemArrayList(k).size());
                }
				maintenance.incrementTimesRestocked();
				maintenance.resetRestockItemsCounterDisplay();
				System.out.println(vendModel.getSingleItemArrayList(index));
			}
		});
		
		this.maintenance.setRestockItemsCBActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maintenance.setRestockItemsCurStockLabel(String.format("Current Stock: %d", vendModel.getSingleItemArrayList(maintenance.getRestockItemsCBSelectedIndex()).size()));
			}
		});
		
		this.maintenance.setTFKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            //String value = maintenance.getChangePriceTF().getText();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
	            	maintenance.getChangePriceTF().setEditable(true);
	            } else {
	            	maintenance.getChangePriceTF().setEditable(false);
	                System.out.println("* Enter only numeric digits(0-9)");
	            }
	         }
	      });
		
		this.maintenance.setChangePriceConfirmBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vendModel.updateItemsPrice(maintenance.getChangePriceCBSelectedIndex(), Integer.parseInt(maintenance.getChangePriceTF().getText()));
				maintenance.resetChangePriceTextField();
				maintenance.setChangePriceCurPriceLabel(String.format("Current Price: $%d", vendModel.getItemRecord().get(maintenance.getChangePriceCBSelectedIndex()).getPrice()));
				System.out.println(maintenance.getChangePriceCBSelectedIndex());
				System.out.println(maintenance.getChangePriceTF().getText());
			}
		});
		
		this.maintenance.setChangePriceCBActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maintenance.setChangePriceCurPriceLabel(String.format("Current Price: $%d", vendModel.getItemRecord().get(maintenance.getChangePriceCBSelectedIndex()).getPrice()));
			}
		});
		
		this.maintenance.setRestockChangeConfirmBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = maintenance.getRestockChangeCBSelectedIndex();
				vendModel.setChangeStockIndex(index, vendModel.getChangeStock()[index]+maintenance.getRestockChangeCount());
				maintenance.resetRestockChangeCounterDisplay();
				maintenance.setRestockChangeCurStockLabel(String.format("Current Stock: %d", vendModel.getChangeStock()[maintenance.getRestockChangeCBSelectedIndex()]));
			}
		});
		
		this.maintenance.setRestockChangeCBActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maintenance.setRestockChangeCurStockLabel(String.format("Current Stock: %d", vendModel.getChangeStock()[maintenance.getRestockChangeCBSelectedIndex()]));
			}
		});
		
		this.maintenance.setCollectEarningsBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maintenance.setCollectEarningsText(String.format("$%d was collected", vendModel.collectEarnings()));
				ActionListener listener = new ActionListener(){
			        public void actionPerformed(ActionEvent event){
			        	maintenance.setCollectEarningsText("");
			        }
			    };
				Timer timer = new Timer(2000, listener);
			    timer.setRepeats(false);
			    timer.start();
			}
		});
		
		this.maintenance.setPrintRecieptBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maintenance.showReceiptArea();
				maintenance.lockMaintenance();
				maintenance.setReceiptTextArea(printReceipt());
			}
		});
		
		this.maintenance.setReceiptBackBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maintenance.hideReceiptArea();
				maintenance.unlockMaintenance();
				maintenance.setReceiptTextArea("");
			}
		});
	}
	
	/**
	 * This handles the choosing of items in regular vending machine
	 * @param i index of item
	 * @return action listener for item button
	 */
	private ActionListener doItemActions(int i) {
		ActionListener al = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkValidTransac(i)) {
					vendView.disableBackBtn();
					vendView.disableDispenseChangeBtn();
					vendView.coverItems();
					vendView.coverDenominations();
					vendView.showItemDetailComponents();;
					
					vendView.displayItemDetails(vendModel.getSingleItemArrayList(i), vendModel.getItemRecord().get(i));
					
					vendView.changeItemConfirmBtnActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(i==12) {
								for(int j=0; j<vendModel.getNumItems(); j++) {
									if(vendModel.getSingleItemArrayList(j).size()-1==0 && j!=10 && j!=11)
										vendView.disableBtn(j);
									vendModel.removeItem(j);
									vendModel.addTransaction(vendModel.getItemRecord().get(j), maintenance.getTimesRestocked());
								}
							}
							else {
								if(vendModel.getSingleItemArrayList(i).size()-1==0)
									vendView.disableBtn(i);
								vendModel.removeItem(i);
								vendModel.addTransaction(vendModel.getItemRecord().get(i), maintenance.getTimesRestocked());
							}
							vendView.initiateItemLabels(vendModel.getWholeItemArrayList(), vendModel.getItemRecord());
							vendView.hideItemDetailComponents();
							vendView.showIdleTextLabel(vendView.getTotalCashInserted()-vendModel.getItemRecord().get(i).getPrice(), vendModel.getItemRecord().get(i).getName());
							
							ActionListener listener = new ActionListener(){
						        public void actionPerformed(ActionEvent event){
						        	vendModel.addEarnings(i);
						        	vendView.enableBackBtn();
						        	vendView.enableDispenseChangeBtn();
						        	vendView.removeItemsCover();
									vendView.removeDenominationsCover();
									vendView.setTotalCashInserted(0);
									vendView.displayAmountLabelText();
									vendModel.updateChangeStock();
									vendView.resetDisplayItemDetails();
									vendView.getItemsConfirmPanel().setVisible(false);
									vendView.hideIdleTextLabel();
									vendModel.displayChangeStock();
						        }
						    };
							Timer timer = new Timer(2000, listener);
						    timer.setRepeats(false);
						    timer.start();
						}
					});
				}
				else{
					System.out.println("Invalid Transaction");
				}
				vendModel.displayChangeStock();
			}
		};
		return al;
	}
	
	/**
	 * This compiles all the sales, starting stock, and ending stock of all items of the previous restocking
	 * @return String that contains all the information about sales and stock
	 */
	public String printReceipt()
    {
		String text = "";
        boolean isCurrentTransaction = false;
        int numItems = this.vendModel.getNumItems();
        int totalSales[] = new int[numItems];
        int endStock[] = new int[numItems];
        for(int i=0; i<numItems; i++) {
        	totalSales[i]=0;
        	endStock[i]=0;
        }
        
        for (int i = 0; i < this.vendModel.getTransactions().size(); i++) 
        {
            //System.out.println(transactions.get(i).getItem().getName()); //Testing
            isCurrentTransaction = false;
            if (this.vendModel.getTransactions().get(i).getTimesRestocked() == this.maintenance.getTimesRestocked()) {
                isCurrentTransaction = true;
            }
            if (isCurrentTransaction == true) {
                int k = 0;
                while(!this.vendModel.getTransactions().get(i).getItem().getName().equals(this.vendModel.getItemRecord().get(k).getName())){
                    k++;
                }
                if(this.vendModel.getTransactions().get(i).getItem().getName().equals(this.vendModel.getItemRecord().get(k).getName())){
                        totalSales[k]++;
                } 
            }
        }
        
        //formatted receipt
        for (int i = 0; i < numItems; i++) {
            endStock[i] = this.vendModel.getItemRecord().get(i).getStartingStock() - totalSales[i];
        }
        text += String.format("%22s\n", "Vending Receipt:");
        text += "S-StartingStock\nE-EndingStock\nT-TotalSales\nR-TotalRestocked\n";
        text += String.format("%-16s %-2s %-2s %-2s %-2s\n", "Name:", "S", "E", "T", "R");
        //System.out.println("S-StartingStock\nE-EndingStock\nT-TotalSales\nR-TotalRestocked");
        //System.out.println(String.format("%-16s %-2s %-2s %-2s %-2s", "Name:", "S", "E", "T", "R"));
        for(int i = 0; i < numItems; i++)
        {
        	text += String.format("%-16s %-2d %-2d %-2d %-2d\n", this.vendModel.getItemRecord().get(i).getName(),
            		this.vendModel.getItemRecord().get(i).getStartingStock(),endStock[i], totalSales[i], this.vendModel.getItemRecord().get(i).getTotalRestocked());
            //System.out.println(String.format("%-16s %-2d %-2d %-2d %-2d", this.vendModel.getItemRecord().get(i).getName(),
            //		this.vendModel.getItemRecord().get(i).getStartingStock(),endStock[i], totalSales[i], this.vendModel.getItemRecord().get(i).getTotalRestocked()));
        }
        System.out.println(text);
        return text;
    }
	
	/**
	 * This checks if the transaction is valid
	 * @param i index of item purchase
	 * @return boolean that determines if it is a valid transaction
	 */
	public boolean checkValidTransac(int i) {
		boolean valid = true;
		
		valid = vendModel.checkChange(vendView.getTotalCashInserted(), i);
		if(!vendModel.checkItemStock() && i==12) {
			System.out.println("Not enough ingredients...");
			valid = false;
		}
		
		if(vendModel.getSingleItemArrayList(i).get(0).getPrice()>vendView.getTotalCashInserted() && valid) {
			System.out.println("You do not have enough money for that...");
			valid = false;
		}
			
		
		return valid;
	}
	
	/**
	 * This handles the cash inserted by user
	 * @param i index of the denomination in arraylist
	 * @return action listener of the cash button
	 */
	private ActionListener doDenominationActions(int i) {
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vendView.setTotalCashInserted(vendView.getTotalCashInserted() + Integer.parseInt(vendView.getDenominationButtons()[i].getText()));
				//vendModel.addChangeStock(i);
				vendView.displayAmountLabelText();
			}
		};
		return al;
	}
	
	/**
	 * This shows the regular vending machine
	 */
	public void revealRegVend() {
		this.vendView.getRegVendFrame().setVisible(true);
	}
	/**
	 * This hides the regular vending machine
	 */
	public void hideRegVend() {
		this.vendView.getRegVendFrame().setVisible(false);
	}
	
	/**
	 * This shows maintenance frame
	 */
	public void revealMaintenance() {
		this.maintenance.getMTFrame().setVisible(true);
	}
	/**
	 * This hides maintenance frame
	 */
	public void hideMaintenance() {
		this.maintenance.getMTFrame().setVisible(false);
	}
	
	/**
	 * This initiates action listeners for the regular vending machine
	 */
	private void initiateVendViewActionListeners() {
		this.vendView.setBackButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vendView.setTotalCashInserted(0);
				vendView.displayAmountLabelText();
				//vendModel.revertChangeStock();
				//vendModel.resetTempNewHoldingChangeStock();
				hideRegVend();
				menu.revealMainMenu();
			}
		});
		
		this.vendView.setDispenseChangeBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vendView.setTotalCashInserted(0);
				vendView.displayAmountLabelText();
				//vendModel.revertChangeStock();
				//vendModel.resetTempNewHoldingChangeStock();
				vendModel.displayChangeStock();
				//vendModel.displayTempNewHoldingChangeStock();
			}
		});
		
		for(int i=0; i<this.vendModel.getNumItems(); i++){
			if(i!=10 && i!=11)
				this.vendView.setAddButtonActionListener(i, doItemActions(i));
		}
		
		for(int i=0; i<6; i++) {
			this.vendView.setDenominationsBtnActionListener(i, doDenominationActions(i));
		}
		
		this.vendView.setItemCancelBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vendView.enableBackBtn();
				vendView.enableDispenseChangeBtn();
				vendView.removeItemsCover();
				vendView.removeDenominationsCover();
				vendView.getItemsConfirmPanel().setVisible(false);
				vendView.resetDisplayItemDetails();
			}
		});
	}
}
