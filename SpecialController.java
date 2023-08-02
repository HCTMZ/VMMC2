import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

/**
 * This class is the controller for the special vending machine
 */
public class SpecialController {
    private MainMenu menu;
    private SpecialGui specialGui;
    private SpecialModel specialModel;
	private Maintenance maintenance;

    private int totalUserMoney;
    private int totalPrice;

    /**
     * This is the constructor for special vending machine controller
     * @param menu Main menu view
     * @param specialGui Special vending machine view
     * @param specialModel Special vending machine model
     * @param maintenance Maintenance view
     */
    public SpecialController(MainMenu menu, SpecialGui specialGui, SpecialModel specialModel, Maintenance maintenance) {
        this.menu = menu;
        this.specialGui = specialGui;
        this.specialModel = specialModel;
        this.maintenance = maintenance;

        //adds all buttons visible in the buy menu
        setBuyButton();
    }

    /**
     * This initiates action listeners for all the buttons of maintenance
     */
    public void initiateMaintenanceActionListeners() {
		this.maintenance.setMaintenanceBackBtnActionListenener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hideMaintenance();
                updateInfoLabel();
				menu.revealMainMenu();
			}
		});
		
		this.maintenance.setRestockItemsConfirmBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printReceipt();
				int index = maintenance.getRestockItemsCBSelectedIndex();
				
				specialModel.addNewItemInstance(index, maintenance.getRestockItemCount());
				specialModel.getItemRecord().get(index).addTotalRestock(maintenance.getRestockItemCount());
				maintenance.setRestockItemsCurStockLabel(String.format("Current Stock: %d", specialModel.getSingleItemArrayList(index).size()));
				
                for(int k = 0; k < 12; k++){
					specialModel.getItemRecord().get(k).setStartingStock(specialModel.getSingleItemArrayList(k).size());
                }
				maintenance.incrementTimesRestocked();
				maintenance.resetRestockItemsCounterDisplay();
				System.out.println(specialModel.getSingleItemArrayList(index));
			}
		});
		
		this.maintenance.setRestockItemsCBActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maintenance.setRestockItemsCurStockLabel(String.format("Current Stock: %d", specialModel.getSingleItemArrayList(maintenance.getRestockItemsCBSelectedIndex()).size()));
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
				specialModel.updateItemsPrice(maintenance.getChangePriceCBSelectedIndex(), Integer.parseInt(maintenance.getChangePriceTF().getText()));
				maintenance.resetChangePriceTextField();
				maintenance.setChangePriceCurPriceLabel(String.format("Current Price: $%d", specialModel.getItemRecord().get(maintenance.getChangePriceCBSelectedIndex()).getPrice()));
				System.out.println(maintenance.getChangePriceCBSelectedIndex());
				System.out.println(maintenance.getChangePriceTF().getText());
                
			}
		});
		
		this.maintenance.setChangePriceCBActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maintenance.setChangePriceCurPriceLabel(String.format("Current Price: $%d", specialModel.getItemRecord().get(maintenance.getChangePriceCBSelectedIndex()).getPrice()));
			}
		});
		
		this.maintenance.setRestockChangeConfirmBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = maintenance.getRestockChangeCBSelectedIndex();
				specialModel.setChangeStockIndex(index, specialModel.getChangeStock()[index]+maintenance.getRestockChangeCount());
				maintenance.resetRestockChangeCounterDisplay();
				maintenance.setRestockChangeCurStockLabel(String.format("Current Stock: %d", specialModel.getChangeStock()[maintenance.getRestockChangeCBSelectedIndex()]));
			}
		});
		
		this.maintenance.setRestockChangeCBActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maintenance.setRestockChangeCurStockLabel(String.format("Current Stock: %d", specialModel.getChangeStock()[maintenance.getRestockChangeCBSelectedIndex()]));
			}
		});
		
		this.maintenance.setCollectEarningsBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(String.format("$%d was collected", specialModel.collectEarnings()));
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
     * This initiates action listener for the + buttons in special vending machine view
     * @param i index of the button in the button arraylist
     * @return action listener of specific + button
     */
    private ActionListener addToBuyList(int i)
    {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(specialModel.sufficientStock(specialGui.getBuyList(i), i)) {
                    specialGui.updateBuyList(i, specialGui.buyList[i]+1);
                    specialGui.updateItemLabel(i, specialGui.buyList[i]);
                    specialGui.setTotalPrice(specialModel.computeTotalPrice(specialGui.buyList));
                    
                    totalPrice = specialModel.computeTotalPrice(specialGui.buyList);
                    //checking
                    System.out.println(i + "= " + specialGui.buyList[i]);
                }else{
                    System.out.println("Over the current stock");
                }
            }
        };
        return action;
    }

    /**
     * This initiates action listener for the - buttons in special vending machine view
     * @param i index of the button in the arraylist
     * @return action listener of specific - button
     */
    private ActionListener removeToBuyList(int i)
    {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (specialGui.buyList[i] > 0) {
                    specialGui.updateBuyList(i, specialGui.buyList[i]-1);
                    specialGui.updateItemLabel(i, specialGui.buyList[i]);
                    specialGui.setTotalPrice(specialModel.computeTotalPrice(specialGui.buyList));
                    
                    totalPrice = specialModel.computeTotalPrice(specialGui.buyList);
                    //checking
                    System.out.println(i + "= " + specialGui.buyList[i]);
                }else{
                    System.out.println("invalid button click");
                }
            }
        };
        return action;
    }

    /**
     * This initiates action listeners for the cash buttons in the special vending machine view
     * @param i index of the cash button in the arraylist
     * @return action listener for the specific cash button
     */
    private ActionListener insertCash(int i)
    {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                specialModel.updatePayment(i);

                totalUserMoney = 0;
                for (int j = 0; j < 6; j++) {
                    totalUserMoney += (specialModel.getPayment(j) * specialModel.changeDenominations[j]);
                }
                specialGui.setTotalPayment(totalUserMoney);
            }
        };
        return action;
    }

    /**
     * This initiates action listeners for the proceed button
     * @return action listener for proceed button
     */
    private ActionListener proceedTransaction()
    {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean valid = true;
                
                System.out.println(totalUserMoney + " " + totalPrice);
                if (totalUserMoney < totalPrice) 
                {
                    valid = false;
                    System.out.println("Insufficient Cash");
                    specialGui.setIdleText("Insufficient Cash");
                }else if(!specialModel.checkChange(totalUserMoney, totalPrice))
                {
                    valid = false;
                    System.out.println("Insufficient Change");
                    specialGui.setIdleText("Insufficeint Change Please try other bills");
                }else if(!specialModel.checkStandAlone(specialGui.buyList))
                {
                    valid = false;
                    System.out.println("No stand alone items included");
                    specialGui.setIdleText("Transaction needs toppings");
                }

                if (valid) {
                    for (int i = 0; i < 12; i++) {
                        for (int j = 0; j < specialGui.buyList[i]; j++) {
                            specialModel.removeItem(i);//removes item one by one
                        }
                    }

                    specialGui.setIdleText(specialModel.getCookingInstruction(specialGui.buyList, totalUserMoney, totalPrice));
                    specialModel.saveTransaction(specialGui.buyList, maintenance.getTimesRestocked());
                    

                    for (int i = 0; i < 12; i++) {
                        System.out.println(specialModel.getItemRecord().get(i).getName() + specialModel.getWholeItemArrayList().get(i).size());
                    }
                }
                
                //resets the values for transaction
                specialGui.resetBuyList();//resets basket
                specialGui.resetItemLabel();//resets Gui basket
                specialGui.setTotalPayment(0);//reset label
                specialModel.resetPayment();//reset model payment
                specialGui.setTotalPrice(0);//reset label
                totalUserMoney = 0;
                updateInfoLabel();
            }
        };
        return action;
    }

    /**
     * This sets action listeners to all the buttons and Jlabels related to special vending machine
     */
    private void setBuyButton()
    {
        updateInfoLabel();
        // Set action listeners for plus buttons
        for (int i = 0; i < 12; i++) {
            specialGui.setPButtonActionListener(i, addToBuyList(i));
        }

        // Set action listeners for minus buttons
        for (int i = 0; i < 12; i++) {
            specialGui.setMButtonActionListener(i, removeToBuyList(i));
        }

        for (int i = 0; i < 6; i++) {
            specialGui.setCashButtonListener(i, insertCash(i));
        }
        
        specialGui.setBackButtonListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                specialGui.hideSpecialGUI();
                specialGui.resetBuyList();//resets basket
                specialGui.resetItemLabel();//resets Gui basket
                specialGui.setTotalPayment(0);//reset label
                specialModel.resetPayment();//reset model payment
                specialGui.setTotalPrice(0);//reset label
                totalUserMoney = 0;
                updateInfoLabel();
                menu.revealMainMenu();
            }

        });

        specialGui.setProceedButtonListener(proceedTransaction());
        
    }

    /**
     * This updates the information(name qnty price kcal) Jlabel about a certain product
     */
    private void updateInfoLabel()
    {
        for (int i = 0; i < 12; i++) {
            specialGui.setInfoLabel(i, specialModel.getItemRecord().get(i).getName(), specialModel.getWholeItemArrayList().get(i).size()
            , specialModel.getItemRecord().get(i).getCalories(), specialModel.getItemRecord().get(i).getPrice());
        }
    }

    /**
     * This sets the maintenance frame visible
     */
    public void revealMaintenance() {
		this.maintenance.getMTFrame().setVisible(true);
	}

    /**
     * This hides the maintenance frame
     */
    public void hideMaintenance()
    {
        this.maintenance.getMTFrame().setVisible(false);
    }

    /**
     * This compiles all the sales, starting stock, and ending stock of all items of the previous restocking
     * @return String that contains all the information about sales and stock
     */
    public String printReceipt()
    {
        String text = "";
        boolean isCurrentTransaction = false;
        int totalSales[] = new int[12];
        int endStock[] = new int[12];
        for(int i=0; i<12; i++) {
        	totalSales[i]=0;
        	endStock[i]=0;
        }
        
        for (int i = 0; i < this.specialModel.getTransactionList().size(); i++) 
        {
            isCurrentTransaction = false;
            if (this.specialModel.getTransactionList().get(i).getTimesRestocked() == this.maintenance.getTimesRestocked()) {
                isCurrentTransaction = true;
            }

            if (isCurrentTransaction) {
                int k = 0;
                for (int j = 0; j < this.specialModel.getTransactionList().get(i).getItem().size(); j++) {
                    while(!this.specialModel.getTransactionList().get(i).getItem().get(j).getName().equals(specialModel.getItemRecord().get(k).getName()))
                    {
                        k++;
                    }
                    totalSales[k]++;
                    
                }
            }
            
        }
        //formatted receipt
        for (int i = 0; i < 12; i++) {
            endStock[i] = this.specialModel.getItemRecord().get(i).getStartingStock() - totalSales[i];
        }
        text += String.format("%22s\n", "Vending Receipt:");
        text += "S-StartingStock\nE-EndingStock\nT-TotalSales\nR-TotalRestocked\n";
        text += String.format("%-16s %-2s %-2s %-2s %-2s\n", "Name:", "S", "E", "T", "R");
        for(int i = 0; i < 12; i++)
        {
        	text += String.format("%-16s %-2d %-2d %-2d %-2d\n", this.specialModel.getItemRecord().get(i).getName(),
            		this.specialModel.getItemRecord().get(i).getStartingStock(),endStock[i], totalSales[i], this.specialModel.getItemRecord().get(i).getTotalRestocked());
        }
        System.out.println(text);
        return text;
    }
}
