import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/*
 * This class handles the data and GUI/components of the Main Menu
 */
public class MainMenu{

	private JFrame menuFrame;
	
	private final int FRAMEWIDTH = 420, PANELHEIGHT = 36;
	
	private JRadioButton regularRB, specialRB;
	private JRadioButton featuresRB, maintenanceRB;
	private ButtonGroup createRBGroup, testRBGroup;
	private JButton createTypeConfirm;
	private JButton exitButton, confirmTestButton;
	
	private String vendSelection, testSelection;
	private boolean vendExists = false;;

	/**
	 * MainMenu constructor in charge of instantiating the GUI and its components for the Main Menu
	 */
	public MainMenu() {
		vendSelection = null;
		testSelection = null;
		
		//attributes of the main menu frame
		this.menuFrame = new JFrame("Main Menu");
		this.menuFrame.setVisible(true);
		this.menuFrame.setSize(FRAMEWIDTH,420);
		this.menuFrame.setTitle("Main Menu");
		this.menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.menuFrame.setResizable(false);
		this.menuFrame.setLocationRelativeTo(null);
		this.menuFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 60));
		
		JPanel createPnl, testPnl, confirmPnl;
		
		//contents of the create panel housing the options to create the type of VM
		JLabel createLabel = new JLabel("Create VM:");
		createLabel.setFont(new Font("", Font.PLAIN, 18));
		
		this.regularRB = new JRadioButton("Regular");
		this.regularRB.setFocusable(false);
		
		this.specialRB = new JRadioButton("Special");
		this.specialRB.setFocusable(false);
		
		this.createRBGroup = new ButtonGroup();
		this.createRBGroup.add(regularRB);
		this.createRBGroup.add(specialRB);
		
		this.createTypeConfirm = new JButton("Create");
		this.createTypeConfirm.setPreferredSize(new Dimension(100,30));
		this.createTypeConfirm.setFocusable(false);
		
		createPnl = new JPanel(new FlowLayout());
		createPnl.setPreferredSize(new Dimension(FRAMEWIDTH, PANELHEIGHT));
		//createPnl.setBackground(Color.RED);
		createPnl.add(createLabel);
		createPnl.add(regularRB);
		createPnl.add(specialRB);
		createPnl.add(createTypeConfirm);
		
		//contents of the test panel housing the options to run which test for the VM
		JLabel testLabel = new JLabel("Test Type: ");
		testLabel.setFont(new Font("", Font.PLAIN, 18));
		
		this.featuresRB = new JRadioButton("Features");
		this.featuresRB.setFocusable(false);
		
		this.maintenanceRB = new JRadioButton("Maintenance");
		this.maintenanceRB.setFocusable(false);
		
		this.testRBGroup = new ButtonGroup();
		this.testRBGroup.add(featuresRB);
		this.testRBGroup.add(maintenanceRB);
		
		testPnl = new JPanel(new FlowLayout());
		testPnl.setPreferredSize(new Dimension(FRAMEWIDTH, PANELHEIGHT));
		//testPnl.setBackground(Color.GREEN);
		testPnl.add(testLabel);
		testPnl.add(featuresRB);
		testPnl.add(maintenanceRB);
		
		//housing third layer including the option to exit program and confirm which type of test to run
		this.exitButton = new JButton("Exit");
		this.exitButton.setFocusable(false);
		this.exitButton.setPreferredSize(new Dimension(100,50));
		this.exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		this.confirmTestButton = new JButton("Test");
		this.confirmTestButton.setFocusable(false);
		this.confirmTestButton.setPreferredSize(new Dimension(100,50));
		
		confirmPnl = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
		confirmPnl.setPreferredSize(new Dimension(300, 50));
		//confirmPnl.setBackground(Color.BLUE);
		confirmPnl.add(exitButton);
		confirmPnl.add(confirmTestButton);
		
		
		this.menuFrame.add(createPnl);
		this.menuFrame.add(testPnl);
		this.menuFrame.add(confirmPnl);
		this.menuFrame.setVisible(true);
	}

	/**
	 * Assigns an action to the button for creating a vending machine type
	 * @param action
	 */
	public void setCreateTypeConfirmBtnActionListener(ActionListener action) {
		createTypeConfirm.addActionListener(action);
	}
	
	/**
	 * Assigns an action to the button for confirming type of test
	 * @param action
	 */
	public void setConfirmTestBtnActionListener(ActionListener action) {
		confirmTestButton.addActionListener(action);
	}

	/**
	 * Reveals the main menu
	 */
	public void revealMainMenu() {
		this.menuFrame.setVisible(true);
	}
	/**
	 * HIdes the main menu
	 */
	public void hideMainMenu() {
		this.menuFrame.setVisible(false);
	}
	
	/**
	 * Returns the frame of main menu
	 * @return menuFrame
	 */
	public JFrame getMenuFrame() {
		return menuFrame;
	}
	/**
	 * Returns the RadioButton assigned to the regular vending type choice
	 * @return regularRB
	 */
	public JRadioButton getRegularRB(){
		return regularRB;
	}
	/**
	 * Returns the RadioButton assigned to the special vending type choice
	 * @return specialRB
	 */
	public JRadioButton getSpecialButton(){
		return specialRB;
	}
	/**
	 * Returns the RadioButton assigned to the testing the features of the VM
	 * @return featuresRB
	 */
	public JRadioButton getFeaturesRB(){
		return featuresRB;
	}
	/**
	 * Returns the RadioButton assigned to the testing the maintenance of the VM
	 * @return maintenanceRB
	 */
	public JRadioButton getMainTRB(){
		return maintenanceRB;
	}
	/**
	 * Returns the RadioButtonGroup assigned to the regular vending type choice
	 * @return createRBGroup
	 */
	public ButtonGroup getCreateRBGroup(){
		return createRBGroup;
	}
	/**
	 * Returns the RadioButtonGroup assigned to the testing the maintenance of the VM
	 * @return
	 */
	public ButtonGroup getTestRBGroup(){
		return testRBGroup;
	}
	/**
	 * Returns the Button assigned to creating a type of VM
	 * @return createTypeConfirm
	 */
	public JButton getCreateTypeConfirm(){
		return createTypeConfirm;
	}
	/**
	 * returns the button ending the program
	 * @return exitButton
	 */
	public JButton getExiButton(){
		return exitButton;
	}
	/**
	 * Returns the button assigned to choosing which type of test to run on VM
	 * @return confirmTestButton
	 */
	public JButton getConfirmTestButton(){
		return confirmTestButton;
	}
	/**
	 * Returns the selected option for the type of VM created
	 * @return vendSelection
	 */
	public String getVendSelection(){
		return vendSelection;
	}
	/**
	 * Returns the selected option for the tpye of test to do
	 * @return testSelection
	 */
	public String getTestSelection(){
		return testSelection;
	}
	/**
	 * Returns the boolean value to check if a VM currently exists
	 * @return vendExists
	 */
	public boolean getVendExists(){
		return vendExists;
	}
	/**
	 * Sets the current type of VM created
	 * @param newVendSelec
	 */
	public void setVendSelection(String newVendSelec){
		this.vendSelection = newVendSelec;
	}
	/**
	 * Sets the current test to run
	 * @param newTestSelec
	 */
	public void setTestSelection(String newTestSelec){
		this.testSelection = newTestSelec;
	}
	/**
	 * Sets the boolean value of checking if a VM currently exists
	 * @param newVendExists
	 */
	public void setVendExists(boolean newVendExists){
		this.vendExists = newVendExists;
	}

}
