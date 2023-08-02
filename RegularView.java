import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * This class handles the data and components/GUI of the features of the VM
 */
public class RegularView {
	private JFrame regVendFrame;
	private JPanel itemsPanel;
	private JPanel itemPnl1, itemPnl2, itemPnl3, itemPnl4, itemPnl5;
	private JPanel customItemPanel;
	private JPanel insertCashPanel;
	private JPanel amountPanel;
	private JPanel denominationsPanel;
	private JPanel itemsConfirmPanel;
	
	private JLabel[] itemLabels;
	private JLabel[] itemDetailLabels;
	private JLabel specialItemLblLeft;
	private JLabel amountLabel;
	private JLabel itemsCover;
	private JLabel denominationsCover;
	private JLabel idleText;
	
	private JButton[] itemButtons;
	private JButton[] denominationBtns;
	private JButton backButton;
	private JButton itemCancelBtn, itemConfirmBtn;
	private JButton dispenseChangeBtn;
	
	private ImageIcon bananaIcon = new ImageIcon("assets/Banana-Single.png"), lankaIcon = new ImageIcon("assets/Lanka.png"), flanIcon = new ImageIcon("assets/Leche-Flan.png"),
			munggoIcon = new ImageIcon("assets/Munggo beans.png"), nataIcon = new ImageIcon("assets/Nata.png"), pinipigIcon = new ImageIcon("assets/Pinipig.png"),
			redSagoIcon = new ImageIcon("assets/Red Sago.png"), SabaIcon = new ImageIcon("assets/Saba.png"), halayaIcon = new ImageIcon("assets/Ube Halaya.png"),
			ubeicecreamIcon = new ImageIcon("assets/Ube Ice Cream.png");
	private ImageIcon denomGarage = new ImageIcon("assets/denomGarage.png");
	private ImageIcon itemsGarage = new ImageIcon("assets/ItemsGarage.png");
	private ImageIcon haloHalo = new ImageIcon("assets/HaloHalo.png");
	private ImageIcon arrowLeft = new ImageIcon("assets/ArrowLeft.png");
	
	private Border border = BorderFactory.createLineBorder(Color.black, 2);
	
	private int totalCashInserted = 0;
	
	private final double scalar = 0.85;
	private int BTNWIDTH = (int) (160*scalar),
			BTNHEIGHT = (int) (150*scalar), 
			BTNICONWIDTH = (int) (185*scalar);

	private int PNLWIDTH = (int) (600*scalar),
			PNLHEIGHT = (int) (175*scalar);
	
	private int Size100 = (int) (100*scalar), Size80 = (int) (80*scalar), Size60 = (int) (60*scalar), Size250 = (int) (250*scalar), Size1050 = (int) (1050*scalar), Size300 = (int) (300*scalar),
			Size625 = (int) (625*scalar), Size105 = (int) (105*scalar), Size50 = (int) (50*scalar), Size25 = (int) (25*scalar), Size36 = (int) (36*scalar), Size240 = (int) (240*scalar),
			Size200 = (int) (200*scalar), Size120 = (int) (120*scalar), Size125 = (int) (125*scalar), Size15 = (int) (15*scalar), Size9 = (int) (9*scalar), Size5 = (int) (5*scalar), Size16 = (int) (16*scalar);
	private int frameWidth = (int) (1000*scalar), frameHeight = (int) (1060*scalar);
	
	/**
	 * RegularView Constructor for initializing the components of the frame mainly the item displays, and inserting cash, as well as confirming purchase and returning to the main menu
	 */
	public RegularView(){
		//attributes of the vending machine frame
		this.regVendFrame = new JFrame("Regular Vending Machine");
		this.regVendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.regVendFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.regVendFrame.setSize(frameWidth, frameHeight);
		this.regVendFrame.setLocationRelativeTo(null);
		
		//attributes of the buttons to return to the main menu as well as dispensing the cash of the user
		backButton = new JButton(resizeImage(arrowLeft, Size80, Size60));
		backButton.setPreferredSize(new Dimension(Size100,Size60));
		backButton.setContentAreaFilled(false); 
		backButton.setFocusable(false);
		
		dispenseChangeBtn = new JButton("<html>Dispense<br/>Change</html>");
		dispenseChangeBtn.setFont(new Font("", Font.PLAIN, this.Size16));
		dispenseChangeBtn.setPreferredSize(new Dimension(Size100,Size100));
		dispenseChangeBtn.setContentAreaFilled(false); 
		dispenseChangeBtn.setFocusable(false);
		
		JPanel backPanel = new JPanel(new FlowLayout());
		backPanel.setPreferredSize(new Dimension(Size100, PNLHEIGHT*6));
		backPanel.setBackground(Color.CYAN);
		backPanel.add(backButton);
		backPanel.add(dispenseChangeBtn);
		
		
		itemLabels = new JLabel[13];
		itemDetailLabels = new JLabel[4];
		itemButtons = new JButton[13];
		denominationBtns = new JButton[6];
		
		//Initializes the item buttons and labels of the VM Frame
		createButtonsAndLabels();
		
		//Initializes the item panels
		createItemPanels();
		
		//Initializes the components of the special item
		createSpecialItem();
		
		//Initializes the components of inserting cash
		createInsertCashPanel();
		
		//attributes of the cover for when a transaction is being made
		itemsCover = new JLabel(resizeImage(itemsGarage, PNLWIDTH, PNLHEIGHT*6));
		itemsCover.setPreferredSize(new Dimension(PNLWIDTH, PNLHEIGHT*6));
		itemsCover.setVisible(false);
		
		itemsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		itemsPanel.setPreferredSize(new Dimension(PNLWIDTH, PNLHEIGHT*6));
		itemsPanel.add(itemsCover);
		itemsPanel.add(itemPnl1);
		itemsPanel.add(itemPnl2);
		itemsPanel.add(itemPnl3);
		itemsPanel.add(itemPnl4);
		itemsPanel.add(itemPnl5);
		itemsPanel.add(customItemPanel);
		
		
		insertCashPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, Size25));
		insertCashPanel.setPreferredSize(new Dimension(Size250, Size1050));
		insertCashPanel.setBackground(Color.BLACK);
		insertCashPanel.add(amountPanel);
		insertCashPanel.add(denominationsPanel);
		insertCashPanel.add(itemsConfirmPanel);
		
		
		this.regVendFrame.add(backPanel);
		this.regVendFrame.add(itemsPanel);
		this.regVendFrame.add(insertCashPanel);
		this.regVendFrame.setResizable(false);
	}
	
	/**
	 * Assigns an action to the button for dispensing change
	 * @param actionListener
	 */
	public void setDispenseChangeBtnActionListener(ActionListener actionListener) {
		dispenseChangeBtn.addActionListener(actionListener);
	}
	
	/**
	 * Assigns an action to an item type button
	 * @param index - index of an item
	 * @param actionListener
	 */
	public void setAddButtonActionListener(int index, ActionListener actionListener) {
		itemButtons[index].addActionListener(actionListener);
	}
	
	/**
	 * Assigns an action to the button for returning to the main menu
	 * @param actionListener
	 */
	public void setBackButtonActionListener(ActionListener actionListener) {
		backButton.addActionListener(actionListener);
	}
	
	/**
	 * Assigns an action to a denomination button
	 * @param index of denomination
	 * @param actionListener
	 */
	public void setDenominationsBtnActionListener(int index, ActionListener actionListener) {
		denominationBtns[index].addActionListener(actionListener);
	}
	
	/**
	 * Assigns an action to the button for canceling a transaction
	 * @param actionListener
	 */
	public void setItemCancelBtnActionListener(ActionListener actionListener) {
		itemCancelBtn.addActionListener(actionListener);
	}
	
	/**
	 * Changes the current action listener for confirming a transaction
	 * @param actionListener
	 */
	public void changeItemConfirmBtnActionListener(ActionListener actionListener) {
		if(itemConfirmBtn.getActionListeners().length==0)
			itemConfirmBtn.addActionListener(actionListener);
		else {
			ActionListener oldAction = itemConfirmBtn.getActionListeners()[0];
			itemConfirmBtn.removeActionListener(oldAction);
			itemConfirmBtn.addActionListener(actionListener);
		}
	}
	
	/**
	 * Resizes the image to fit components
	 * @param icon - image
	 * @param w - width
	 * @param h - height
	 * @return the new resized image
	 */
	private ImageIcon resizeImage(ImageIcon icon, int w, int h){
		Image newimg = icon.getImage().getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
	
	/**
	 * Initializes the labels of each item
	 * @param itemArr - item array holding all items
	 * @param itemRecord - item record holding an instance of each item
	 */
	public void initiateItemLabels(ArrayList<ArrayList<Item>> itemArr, ArrayList<Item> itemRecord) {
		for(int i=0; i<13; i++) {
			if(i!=10 && i!=11) {
				if(i==12) this.itemLabels[i].setText(String.format("<html>Qnt:%d<br/>$%d</html>", itemArr.get(i).size(), itemRecord.get(i).getPrice()));
				else this.itemLabels[i].setText(String.format("%s Qnt:%d $%d", itemRecord.get(i).getName(), itemArr.get(i).size(), itemRecord.get(i).getPrice()));
			}
		}
	}
	
	/**
	 * Disables the button to return to the main menu
	 */
	public void disableBackBtn() {
		this.backButton.setEnabled(false);
	}
	
	/**
	 * Enables the button to return to the main menu
	 */
	public void enableBackBtn() {
		this.backButton.setEnabled(true);
	}
	
	/**
	 * Disables the button of an item
	 * @param i - index of an item
	 */
	public void disableBtn(int i) {
		itemButtons[i].setEnabled(false);
	}
	
	/**
	 * Enables the button of an item
	 * @param i - index of an item
	 */
	public void enableBtn(int i) {
		itemButtons[i].setEnabled(true);
	}
	
	/**
	 * Covers the items during a transaction
	 */
	public void coverItems() {
		itemsCover.setVisible(true);
	}
	
	/**
	 * Uncovers the items after a transaction
	 */
	public void removeItemsCover() {
		itemsCover.setVisible(false);
	}
	
	/**
	 * Covers the insert cash panel during a transaction
	 */
	public void coverDenominations() {
		denominationsCover.setVisible(true);
	}
	
	/**
	 * Uncovers the insert cash panel after a transaction
	 */
	public void removeDenominationsCover() {
		denominationsCover.setVisible(false);
	}
	
	/**
	 * Displays the details of an item upon choosing one
	 * @param itemArr - item array holding all items
	 * @param itemRecord - item record holding an instance of each item
	 */
	public void displayItemDetails(ArrayList<Item> itemArr, Item itemRecord) {
		itemDetailLabels[0].setText(String.format("<html>Name:<br/>%s</html>", itemRecord.getName()));
		itemDetailLabels[1].setText(String.format("Price: %d",itemRecord.getPrice()));
		itemDetailLabels[2].setText(String.format("Qnt: %d", itemArr.size()));
		itemDetailLabels[3].setText(String.format("Cal.: %d", itemRecord.getCalories()));
	}
	
	/**
	 * Disables the button for dispensing change
	 */
	public void disableDispenseChangeBtn() {
		dispenseChangeBtn.setEnabled(false);
	}
	
	/**
	 * Enables the button for dispensing change
	 */
	public void enableDispenseChangeBtn() {
		dispenseChangeBtn.setEnabled(true);
	}
		
	/**
	 * Resets the details of an item upon choosing one
	 */
	public void resetDisplayItemDetails() {
		for(JLabel label : itemDetailLabels)
			label.setText("");
	}
	
	/**
	 * Displays the idle text for when dispensing change
	 * @param change
	 */
	public void showIdleTextLabel(int change, String name) {
		idleText.setText(String.format("<html>Dispensing Change: %d<br/>Dispensing %s</html>", change, name));
		
		idleText.setVisible(true);
	}
	
	/**
	 * Hides the idle text
	 */
	public void hideIdleTextLabel() {
		idleText.setVisible(false);
	}
	
	/**
	 * Hides the components of the item details upon choosing an item
	 */
	public void hideItemDetailComponents() {
		for(int i=0; i<4; i++)
			itemDetailLabels[i].setVisible(false);
		itemConfirmBtn.setVisible(false);
		itemCancelBtn.setVisible(false);
	}
	
	/**
	 * Reveals the components of the item details upon choosing an item
	 */
	public void showItemDetailComponents() {
		itemsConfirmPanel.setVisible(true);
		for(int i=0; i<4; i++)
			itemDetailLabels[i].setVisible(true);
		itemConfirmBtn.setVisible(true);
		itemCancelBtn.setVisible(true);
	}
	
	/**
	 * Initializes the components of the insert cash panel
	 */
	private void createInsertCashPanel() {
		//amount of total cash inserted
		amountLabel = new JLabel(String.valueOf(totalCashInserted));
		amountLabel.setFont(new Font("", Font.PLAIN, Size36));
		
		amountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, Size50, Size25));
		amountPanel.setPreferredSize(new Dimension(Size300, Size100));
		amountPanel.setBackground(Color.PINK);
		amountPanel.add(amountLabel);
		
		//cover for when transaction is curently in place
		denominationsCover = new JLabel(resizeImage(denomGarage, Size250, Size625));
		denominationsCover.setPreferredSize(new Dimension(Size250, Size625));
		denominationsCover.setVisible(false);
		
		denominationBtns[0] = new JButton("100");
		denominationBtns[0].setPreferredSize(new Dimension(Size250, Size105));
		denominationBtns[0].setFocusable(false);
		
		denominationBtns[1] = new JButton("50");
		denominationBtns[1].setPreferredSize(new Dimension(Size250, Size105));
		denominationBtns[1].setFocusable(false);
		
		denominationBtns[2] = new JButton("20");
		denominationBtns[2].setPreferredSize(new Dimension(Size250, Size105));
		denominationBtns[2].setFocusable(false);
		
		denominationBtns[3] = new JButton("10");
		denominationBtns[3].setPreferredSize(new Dimension(Size250, Size105));
		denominationBtns[3].setFocusable(false);
		
		denominationBtns[4] = new JButton("5");
		denominationBtns[4].setPreferredSize(new Dimension(Size250, Size105));
		denominationBtns[4].setFocusable(false);
		
		denominationBtns[5] = new JButton("1");
		denominationBtns[5].setPreferredSize(new Dimension(Size250, Size105));
		denominationBtns[5].setFocusable(false);
		
		//Components of confirming, canceling, and item details
		itemCancelBtn = new JButton("Cancel");
		itemCancelBtn.setPreferredSize(new Dimension(Size100, Size50));
		itemCancelBtn.setFocusable(false);
		
		itemConfirmBtn = new JButton("Buy");
		itemConfirmBtn.setPreferredSize(new Dimension(Size100, Size50));
		itemConfirmBtn.setFocusable(false);
		
		itemsConfirmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, Size9, Size5));
		itemsConfirmPanel.setPreferredSize(new Dimension(Size240, Size200));
		itemsConfirmPanel.setBackground(Color.WHITE);
		
		idleText = new JLabel("Dispensing Change: $Size200");
		idleText.setPreferredSize(new Dimension(Size200, Size120));
		idleText.setFont(new Font("", Font.ITALIC, Size16));
		idleText.setVisible(false);
		
		itemsConfirmPanel.add(idleText);
		
		itemDetailLabels[0] = new JLabel();
		itemDetailLabels[1] = new JLabel();
		itemDetailLabels[2] = new JLabel();
		itemDetailLabels[3] = new JLabel();
		for(JLabel label : itemDetailLabels) {
			label.setPreferredSize(new Dimension(Size100, Size60));
			itemsConfirmPanel.add(label);
		}
		
		itemsConfirmPanel.add(itemCancelBtn);
		itemsConfirmPanel.add(itemConfirmBtn);
		itemsConfirmPanel.setVisible(false);
		
		//Overall denominations panel
		denominationsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		denominationsPanel.setPreferredSize(new Dimension(Size250, Size625));
		denominationsPanel.setBackground(Color.WHITE);
		denominationsPanel.add(denominationsCover);
		denominationsPanel.add(denominationBtns[0]);
		denominationsPanel.add(denominationBtns[1]);
		denominationsPanel.add(denominationBtns[2]);
		denominationsPanel.add(denominationBtns[3]);
		denominationsPanel.add(denominationBtns[4]);
		denominationsPanel.add(denominationBtns[5]);
	}
	
	/**
	 * Initializes the components of the Special Item *Halo Halo*
	 */
	private void createSpecialItem() {
		//Name of Special Item
		specialItemLblLeft = new JLabel("<html>HALO<br/>HALO</html>");
		specialItemLblLeft.setFont(new Font("Times New Roman", Font.ITALIC, Size36));
		
		itemButtons[12] = new JButton(resizeImage(haloHalo, BTNWIDTH, BTNHEIGHT));
		itemButtons[12].setText("Halo-Halo");
		itemButtons[12].setPreferredSize(new Dimension(BTNWIDTH, BTNHEIGHT));
		itemButtons[12].setContentAreaFilled(false);
		
		//Quantity and Price of Special Item
		itemLabels[12] = new JLabel();
		itemLabels[12].setFont(new Font("Times New Roman", Font.ITALIC, Size36));
		
		customItemPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, Size25, 0));
		customItemPanel.setPreferredSize(new Dimension(PNLWIDTH, PNLHEIGHT));
		customItemPanel.setBackground(new Color(246, 206, 252));
		customItemPanel.setBorder(border);
		customItemPanel.add(specialItemLblLeft);
		customItemPanel.add(itemButtons[12]);
		customItemPanel.add(itemLabels[12]);
	}
	
	/**
	 * Initializes the Item Panels and adds its components
	 */
	private void createItemPanels() {
		//Item 1 and 2
		itemPnl1 = new JPanel(new FlowLayout(FlowLayout.CENTER, Size125, 0));
		itemPnl1.setPreferredSize(new Dimension(PNLWIDTH, PNLHEIGHT));
		itemPnl1.setBackground(new Color(144, 238, 144));
		itemPnl1.setBorder(border);
		itemPnl1.add(itemButtons[0]);
		itemPnl1.add(itemButtons[1]);
		itemPnl1.add(itemLabels[0]);
		itemPnl1.add(itemLabels[1]);
		
		//Item 3 and 4
		itemPnl2 = new JPanel(new FlowLayout(FlowLayout.CENTER, Size125, 0));
		itemPnl2.setPreferredSize(new Dimension(PNLWIDTH, PNLHEIGHT));
		itemPnl2.setBackground(new Color(255,114,118));
		itemPnl2.setBorder(border);
		itemPnl2.add(itemButtons[2]);
		itemPnl2.add(itemButtons[3]);
		itemPnl2.add(itemLabels[2]);
		itemPnl2.add(itemLabels[3]);
		
		//Item 5 and 6
		itemPnl3 = new JPanel(new FlowLayout(FlowLayout.CENTER, Size125, 0));
		itemPnl3.setPreferredSize(new Dimension(PNLWIDTH, PNLHEIGHT));
		itemPnl3.setBorder(border);
		itemPnl3.add(itemButtons[4]);
		itemPnl3.add(itemButtons[5]);
		itemPnl3.add(itemLabels[4]);
		itemPnl3.add(itemLabels[5]);
		
		//Item 7 and 8
		itemPnl4 = new JPanel(new FlowLayout(FlowLayout.CENTER, Size125, 0));
		itemPnl4.setPreferredSize(new Dimension(PNLWIDTH, PNLHEIGHT));
		itemPnl4.setBackground(new Color(241,235,156));
		itemPnl4.setBorder(border);
		itemPnl4.add(itemButtons[6]);
		itemPnl4.add(itemButtons[7]);
		itemPnl4.add(itemLabels[6]);
		itemPnl4.add(itemLabels[7]);
		
		//Item 9 and 10
		itemPnl5 = new JPanel(new FlowLayout(FlowLayout.CENTER, Size125, 0));
		itemPnl5.setPreferredSize(new Dimension(PNLWIDTH, PNLHEIGHT));
		itemPnl5.setBackground(new Color(251, 191, 119));
		itemPnl5.setBorder(border);
		itemPnl5.add(itemButtons[8]);
		itemPnl5.add(itemButtons[9]);
		itemPnl5.add(itemLabels[8]);
		itemPnl5.add(itemLabels[9]);
	}
	
	/**
	 * Initializes the Item Buttons and its Labels
	 */
	private void createButtonsAndLabels() {
		itemButtons[0] = new JButton(resizeImage(bananaIcon, BTNICONWIDTH, BTNHEIGHT));
		itemButtons[0].setText("Banana");
		itemButtons[0].setPreferredSize(new Dimension(BTNWIDTH,BTNHEIGHT));
		itemButtons[0].setContentAreaFilled(false);
		
		itemLabels[0] = new JLabel();
		itemLabels[0].setPreferredSize(new Dimension(BTNWIDTH,Size25));
		itemLabels[0].setFont(new Font("Arial", Font.PLAIN, Size15));
		
		itemButtons[1] = new JButton(resizeImage(lankaIcon, BTNICONWIDTH, BTNHEIGHT));
		itemButtons[1].setText("Lanka");
		itemButtons[1].setPreferredSize(new Dimension(BTNWIDTH,BTNHEIGHT));
		itemButtons[1].setContentAreaFilled(false);
		
		itemLabels[1] = new JLabel();
		itemLabels[1].setFont(new Font("Arial", Font.PLAIN, Size15));
		
		itemButtons[2] = new JButton(resizeImage(flanIcon, BTNICONWIDTH, BTNHEIGHT));
		itemButtons[2].setText("LecheFlan");
		itemButtons[2].setPreferredSize(new Dimension(BTNWIDTH,BTNHEIGHT));
		itemButtons[2].setContentAreaFilled(false);
		
		itemLabels[2] = new JLabel();
		itemLabels[2].setPreferredSize(new Dimension(BTNWIDTH,Size25));
		itemLabels[2].setFont(new Font("Arial", Font.PLAIN, Size15));
		
		itemButtons[3] = new JButton(resizeImage(munggoIcon, BTNICONWIDTH, BTNHEIGHT));
		itemButtons[3].setText("Munggo");
		itemButtons[3].setPreferredSize(new Dimension(BTNWIDTH,BTNHEIGHT));
		itemButtons[3].setContentAreaFilled(false);
		
		itemLabels[3] = new JLabel();
		itemLabels[3].setFont(new Font("Arial", Font.PLAIN, Size15));
		
		itemButtons[4] = new JButton(resizeImage(nataIcon, BTNICONWIDTH, BTNHEIGHT));
		itemButtons[4].setText("Nata de Coco");
		itemButtons[4].setPreferredSize(new Dimension(BTNWIDTH,BTNHEIGHT));
		itemButtons[4].setContentAreaFilled(false);
		
		itemLabels[4] = new JLabel();
		itemLabels[4].setPreferredSize(new Dimension(BTNWIDTH,Size25));
		itemLabels[4].setFont(new Font("Arial", Font.PLAIN, Size15));
		
		itemButtons[5] = new JButton(resizeImage(pinipigIcon, BTNICONWIDTH, BTNHEIGHT));
		itemButtons[5].setText("Pinipig");
		itemButtons[5].setPreferredSize(new Dimension(BTNWIDTH,BTNHEIGHT));
		itemButtons[5].setContentAreaFilled(false);
		
		itemLabels[5] = new JLabel();
		itemLabels[5].setFont(new Font("Arial", Font.PLAIN, Size15));
		
		itemButtons[6] = new JButton(resizeImage(redSagoIcon, BTNICONWIDTH, BTNHEIGHT));
		itemButtons[6].setText("Red Sago");
		itemButtons[6].setPreferredSize(new Dimension(BTNWIDTH,BTNHEIGHT));
		itemButtons[6].setContentAreaFilled(false);
		
		itemLabels[6] = new JLabel();
		itemLabels[6].setPreferredSize(new Dimension(BTNWIDTH,Size25));
		itemLabels[6].setFont(new Font("Arial", Font.PLAIN, Size15));
		
		itemButtons[7] = new JButton(resizeImage(SabaIcon, BTNICONWIDTH, BTNHEIGHT));
		itemButtons[7].setPreferredSize(new Dimension(BTNWIDTH,BTNHEIGHT));
		itemButtons[7].setContentAreaFilled(false);
		
		itemLabels[7] = new JLabel();
		itemLabels[7].setFont(new Font("Arial", Font.PLAIN, Size15));
		
		itemButtons[8] = new JButton(resizeImage(halayaIcon, BTNICONWIDTH, BTNHEIGHT));
		itemButtons[8].setText("Ube Halaya");
		itemButtons[8].setPreferredSize(new Dimension(BTNWIDTH,BTNHEIGHT));
		itemButtons[8].setContentAreaFilled(false);
		
		itemLabels[8] = new JLabel();
		itemLabels[8].setPreferredSize(new Dimension(BTNWIDTH,Size25));
		itemLabels[8].setFont(new Font("Arial", Font.PLAIN, Size15));
		
		itemButtons[9] = new JButton(resizeImage(ubeicecreamIcon, BTNICONWIDTH, BTNHEIGHT));
		itemButtons[9].setText("Ube IceCream");
		itemButtons[9].setPreferredSize(new Dimension(BTNWIDTH,BTNHEIGHT));
		itemButtons[9].setContentAreaFilled(false);
		
		itemLabels[9] = new JLabel();
		itemLabels[9].setFont(new Font("Arial", Font.PLAIN, Size15));
	}
	
	/**
	 * Returns the frame of the RVM
	 * @return regVendFrame
	 */
	public JFrame getRegVendFrame() {
		return regVendFrame;
	}
	
	/**
	 * Returns the panel for confirming or canceling a potential transaction
	 * @return itemsConfirmPanel
	 */
	public JPanel getItemsConfirmPanel() {
		return itemsConfirmPanel;
	}
	
	/**
	 * Returns the set of labels for displaying item details
	 * @return itemDetailLabels
	 */
	public JLabel[] getItemDetailLabels() {
		return itemDetailLabels;
	}
	
	/**
	 * Returns the array of item buttons
	 * @return itemButtons
	 */
	public JButton[] getItemButtons() {
		return itemButtons;
	}
	
	/**
	 * Returns the button for the special item
	 * @return special item button
	 */
	public JButton getSpecialItemButton() {
		return itemButtons[12];
	}
	
	/**
	 * Returns the array of denomination buttons
	 * @return denominationBtns
	 */
	public JButton[] getDenominationButtons() {
		return denominationBtns;
	}
	
	/**
	 * Returns the total cash inserted
	 * @return totalCashInserted
	 */
	public int getTotalCashInserted() {
		return totalCashInserted;
	}
	
	/**
	 * Sets the current total cash inserted
	 * @param newCash
	 */
	public void setTotalCashInserted(int newCash) {
		this.totalCashInserted = newCash;
	}
	
	/**
	 * Displays the current amount of cash inserted to the label
	 */
	public void displayAmountLabelText() {
		this.amountLabel.setText(String.valueOf(this.totalCashInserted));
	}
}
