import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.event.ActionListener;

/**
 * This class handles the view of the Special vending machine
 */
public class SpecialGui {

    JFrame frame;
    JButton pButton[];
    JButton mButton[];
    JLabel itemLabel[];
    JButton cashButton[];
    JButton proceedButton;
    JButton backButton;

    int buyList[]; //list of items that are to be customized

    JLabel infoLabel[]; // name price stock and calories
    JLabel totalPayLabel;
    JLabel totalPriceLabel;
    JLabel itemPic[];
    JTextArea idleText;

    /**
     * This is the constructor for the Special vending 
     */
    public SpecialGui()
    {
        frame = new JFrame();

        pButton = new JButton[12];
        mButton = new JButton[12];
        itemLabel = new JLabel[12];
        buyList = new int[12];
        cashButton = new JButton[6];
        infoLabel = new JLabel[12];
        itemPic = new JLabel[12];
        idleText = new JTextArea("Special Vend");
        
        //continue and back button
        proceedButton = new JButton("Proceed");
        backButton = new JButton("back");

        resetBuyList();
        createButtonandLabel();
        
        Border border = BorderFactory.createLineBorder(Color.black, 2);
        //1 panel each item
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panel.setBorder(border);
        panel.add(itemPic[0]);
        panel.add(infoLabel[0]);
        panel.add(mButton[0]);
        panel.add(itemLabel[0]);
        panel.add(pButton[0]);
        panel.setBounds(0, 0, 400, 140);
        panel.setBackground(Color.magenta);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panel1.setBorder(border);
        panel1.add(itemPic[1]);
        panel1.add(infoLabel[1]);
        panel1.add(mButton[1]);
        panel1.add(itemLabel[1]);
        panel1.add(pButton[1]);
        panel1.setBounds(0, 140, 400, 140);
        panel1.setBackground(Color.yellow);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panel2.setBorder(border);
        panel2.add(itemPic[2]);
        panel2.add(infoLabel[2]);
        panel2.add(mButton[2]);
        panel2.add(itemLabel[2]);
        panel2.add(pButton[2]);
        panel2.setBounds(0, 280, 400, 140);
        panel2.setBackground(Color.white);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panel3.setBorder(border);
        panel3.add(itemPic[3]);
        panel3.add(infoLabel[3]);
        panel3.add(mButton[3]);
        panel3.add(itemLabel[3]);
        panel3.add(pButton[3]);
        panel3.setBounds(0, 420, 400, 140);
        panel3.setBackground(Color.yellow);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panel4.setBorder(border);
        panel4.add(itemPic[4]);
        panel4.add(infoLabel[4]);
        panel4.add(mButton[4]);
        panel4.add(itemLabel[4]);
        panel4.add(pButton[4]);
        panel4.setBounds(0, 560, 400, 140);
        panel4.setBackground(Color.orange);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panel5.setBorder(border);
        panel5.add(itemPic[5]);
        panel5.add(infoLabel[5]);
        panel5.add(mButton[5]);
        panel5.add(itemLabel[5]);
        panel5.add(pButton[5]);
        panel5.setBounds(400, 0, 400, 140);
        panel5.setBackground(Color.orange);

        JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panel6.setBorder(border);
        panel6.add(itemPic[6]);
        panel6.add(infoLabel[6]);
        panel6.add(mButton[6]);
        panel6.add(itemLabel[6]);
        panel6.add(pButton[6]);
        panel6.setBounds(400, 140, 400, 140);
        panel6.setBackground(Color.pink);

        JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panel7.setBorder(border);
        panel7.add(itemPic[7]);
        panel7.add(infoLabel[7]);
        panel7.add(mButton[7]);
        panel7.add(itemLabel[7]);
        panel7.add(pButton[7]);
        panel7.setBounds(400, 280, 400, 140);
        panel7.setBackground(Color.lightGray);

        JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panel8.setBorder(border);
        panel8.add(itemPic[8]);
        panel8.add(infoLabel[8]);
        panel8.add(mButton[8]);
        panel8.add(itemLabel[8]);
        panel8.add(pButton[8]);
        panel8.setBounds(400, 420, 400, 140);
        panel8.setBackground(Color.gray);

        JPanel panel9 = new JPanel();
        panel9.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 0));
        panel9.setBorder(border);
        panel9.add(itemPic[9]);
        panel9.add(infoLabel[9]);
        panel9.add(mButton[9]);
        panel9.add(itemLabel[9]);
        panel9.add(pButton[9]);
        panel9.setBounds(400, 560, 400, 140);
        panel9.setBackground(Color.cyan);

        JPanel panel10 = new JPanel();
        panel10.setLayout(new FlowLayout(FlowLayout.CENTER,60, 0));
        panel10.setBorder(border);
        panel10.add(itemPic[10]);
        panel10.add(infoLabel[10]);
        panel10.add(mButton[10]);
        panel10.add(itemLabel[10]);
        panel10.add(pButton[10]);
        panel10.setBounds(0, 700, 400, 140);
        panel10.setBackground(Color.green);

        JPanel panel11 = new JPanel();
        panel11.setLayout(new FlowLayout(FlowLayout.CENTER,60, 0));
        panel11.setBorder(border);
        panel11.add(itemPic[11]);
        panel11.add(infoLabel[11]);
        panel11.add(mButton[11]);
        panel11.add(itemLabel[11]);
        panel11.add(pButton[11]);
        panel11.setBounds(400, 700, 400, 140);
        panel11.setBackground(Color.orange);

        JPanel totalCashPanel = new JPanel();
        totalCashPanel.setLayout(new GridLayout(3,2,0,0));
        totalCashPanel.add(new JLabel("Price", SwingConstants.CENTER));
        totalCashPanel.add(new JLabel("Cash", SwingConstants.CENTER));
        totalPayLabel = new JLabel("0");
        totalPayLabel.setHorizontalAlignment(JLabel.CENTER);
        totalPriceLabel = new JLabel("0");
        totalPriceLabel.setHorizontalAlignment(JLabel.CENTER);
        totalCashPanel.add(totalPriceLabel);
        totalCashPanel.add(totalPayLabel);
        totalCashPanel.add(proceedButton);
        totalCashPanel.add(backButton);
        totalCashPanel.setBounds(800, 0, 200, 200);

        JPanel cashPanel = new JPanel();
        cashPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        cashPanel.add(cashButton[0]);
        cashPanel.add(cashButton[1]);
        cashPanel.add(cashButton[2]);
        cashPanel.add(cashButton[3]);
        cashPanel.add(cashButton[4]);
        cashPanel.add(cashButton[5]);
        cashPanel.setBounds(800, 200, 200, 150);

        JPanel idleTextPanel = new JPanel();
        idleText.setForeground(Color.GREEN);
        idleText.setBackground(Color.BLACK);
        idleTextPanel.add(idleText);
        idleTextPanel.setBackground(Color.black);
        idleTextPanel.setBounds(800, 350, 200, 520);

        //settings on frame
        frame.setTitle("Special Vending Machine");
        frame.setSize(1015,870);
        frame.add(panel);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.add(panel6);
        frame.add(panel7);
        frame.add(panel8);
        frame.add(panel9);
        frame.add(panel10);
        frame.add(panel11);
        frame.add(totalCashPanel);
        frame.add(cashPanel);
        frame.add(idleTextPanel);

        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    /**
     * This instantiates the buttons and labels of the special vending machine
     */
    private void createButtonandLabel()
    {
        //plus and minus button
        //this is the amount that the user is buying
        for(int i=0; i<12; i++){
            pButton[i]= new JButton("+");
            mButton[i] = new JButton("-");
            itemLabel[i] = new JLabel("0");
            infoLabel[i] = new JLabel();
            infoLabel[i].setPreferredSize(new Dimension(280, 20));
        }

        cashButton[0] = new JButton("100");
        cashButton[1] = new JButton("50");
        cashButton[2] = new JButton("20");
        cashButton[3] = new JButton("10");
        cashButton[4] = new JButton("5");
        cashButton[5] = new JButton("1");

        for (int i = 0; i < cashButton.length; i++) {
            cashButton[i].setPreferredSize(new Dimension(100, 50));
        }

        itemPic[0] = new JLabel(resizePic(new ImageIcon("assets/Banana-Single.png"), 140, 80));
        itemPic[1] = new JLabel(resizePic(new ImageIcon("assets/Lanka.png"), 140, 80));
        itemPic[2] = new JLabel(resizePic(new ImageIcon("assets/Leche-Flan.png"), 140, 80));
        itemPic[3] = new JLabel(resizePic(new ImageIcon("assets/Munggo beans.png"), 140, 80));
        itemPic[4] = new JLabel(resizePic(new ImageIcon("assets/Nata.png"), 140, 80));
        itemPic[5] = new JLabel(resizePic(new ImageIcon("assets/Pinipig.png"), 140, 80));
        itemPic[6] = new JLabel(resizePic(new ImageIcon("assets/Red Sago.png"), 140, 80));
        itemPic[7] = new JLabel(resizePic(new ImageIcon("assets/Saba.png"), 140, 80));
        itemPic[8] = new JLabel(resizePic(new ImageIcon("assets/Ube Halaya.png"), 140, 80));
        itemPic[9] = new JLabel(resizePic(new ImageIcon("assets/Ube Ice Cream.png"), 140, 80));
        itemPic[10] = new JLabel(resizePic(new ImageIcon("assets/Ice.png"), 140, 80));
        itemPic[11] = new JLabel(resizePic(new ImageIcon("assets/Milk.png"), 140, 80));
        
        for (int i = 0; i < 12; i++) {
            itemPic[i].setPreferredSize(new Dimension(400, 80));
        }
    }

    /**
     * This sets the information(Price name calories stock) Jlabel of a certain item
     * @param i index of the item in the arraylist
     * @param name name of the item
     * @param stock quantity available
     * @param calories calories of the item
     * @param price price of the item
     */
    public void setInfoLabel(int i, String name, int stock, int calories, int price){
        infoLabel[i].setText(String.format("Name: " + name + " Qnty: " + stock + " kcal: " + calories + " price: " + price));
    }

    /**
     * Setter for the action listener on the "+" buttons
     * @param i index of the plus button in the arraylist
     * @param e Action listener for + button
     */
    public void setPButtonActionListener(int i, ActionListener e) 
    {
		pButton[i].addActionListener(e);
	}

     /**
      * Setter for the action listener on the "-" buttons
      * @param i index of the - button in the arraylist
      * @param e Action listener for - button
      */
    public void setMButtonActionListener(int i, ActionListener e) 
    {
		mButton[i].addActionListener(e);
	}

    /**
     * This updates the Jlabels of the items the user is purchasing
     * @param index index of item
     * @param quantity new quantity
     */
    public void updateItemLabel(int index, int quantity) 
    {
        itemLabel[index].setText(Integer.toString(quantity));
    }

    /**
     * This updates the values of the item the user is purchasing
     * @param index index of item
     * @param total new quantity
     */
    public void updateBuyList(int index, int total)
    {
        buyList[index] = total;//+ or - 1 only
    }

    /**
     * This updates the Jlabel ofitem information(price quantity) for special vending machine
     * @param i index of the item
     * @param info String about the latest information on the item
     */
    public void updateStockInfo(int i, String info)
    {
        infoLabel[i] = new JLabel(info);
    }

    /**
     * Getter for an index of buylist
     * @param i index of the buylist
     * @return the value of index i of buylist
     */
    public int getBuyList(int i)
    {
        return buyList[i];
    }

    /**
     * This resets the buylist values to 0
     */
    public void resetBuyList()
    {
        for (int i = 0; i < buyList.length; i++) {
            buyList[i] = 0;
        }
    }

    /**
     * This resets the Jlabel of the buylist to 0
     */
    public void resetItemLabel()
    {
        for (int i = 0; i < buyList.length; i++) {
            updateItemLabel(i, 0);
        }
    }

    /**
     * Setter for back button action listener
     * @param e Action listener of back button
     */
    public void setBackButtonListener(ActionListener e)
    {
        backButton.addActionListener(e);
    }

    /**
     * 
     * @param e
     */
    public void setProceedButtonListener(ActionListener e)
    {
        proceedButton.addActionListener(e);
    }

    /**
     * Setter for action listener of cash buttons
     * @param i index of the cash button in arraylist
     * @param e action listener of cash button
     */
    public void setCashButtonListener(int i, ActionListener e)
    {
        this.cashButton[i].addActionListener(e);
    }

    /**
     * Setter for the Jlabel of the total payment of the user
     * @param money the total payment of the user
     */
    public void setTotalPayment(int money)
    {
        totalPayLabel.setText(Integer.toString(money));
    }

    /**
     * Setter for the Jlabel of the total price of the transaction
     * @param money the new total price
     */
    public void setTotalPrice(int money)
    {
        totalPriceLabel.setText(Integer.toString(money));
    }

    /**
     * This sets the jlabel that handles print receipt, cooking instruction, and invalid transaction messages
     * @param text Text to update the Jlabel
     */
    public void setIdleText(String text)
    {
        idleText.setText(text);
    }

    /**
     * Resized the image
     * @param icon image to resize
     * @param w width of the new image
     * @param h height of the new image
     * @return the resized image
     */
    private ImageIcon resizePic(ImageIcon icon, int w, int h){
		Image newimg = icon.getImage().getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}

    /**
     * Hides the special vending machine frame
     */
    public void hideSpecialGUI()
    {
        frame.setVisible(false);
    }

    /**
     * Shows the special vending machine frame
     */
    public void showSpecialGUI()
    {
        frame.setVisible(true);
    }
}
