import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class acts as a parent controller for the regular and special VM controllers
 */
public class VendingMachineController {
	private MainMenu menu;
	private RegularView vendView;
	private SpecialGui specialVendView;
	private RegularModel vendModel;
	private SpecialModel specModel;
	private Maintenance maintenance;
	private RegularController regControl;
	private SpecialController specControl;
	
	/*
	 * VendingMachineController constructor instantiating the main meny as well as its actions
	 */
	public VendingMachineController() {
		this.menu = new MainMenu();
		
		this.menu.setCreateTypeConfirmBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createTypeBtnActions();
			}
		});
		
		this.menu.setConfirmTestBtnActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmTestBtnActions();
			}
		});
	}
	
	/**
	 * Actions for the type of VM to create
	 */
	private void createTypeBtnActions() {
		//if selected RadioButton is the regular VM
		if(this.menu.getCreateRBGroup().getSelection()==this.menu.getRegularRB().getModel()) {
			//creates instances of the regular MVC and its maintenance
			this.vendView = new RegularView();
			this.vendModel = new RegularModel();
			this.maintenance = new Maintenance(this.vendModel);
			regControl = new RegularController(menu, vendView, vendModel, maintenance);
			this.deleteSpecialVend();
			this.menu.setVendSelection("Regular");
			this.menu.setVendExists(true);
			System.out.println(this.menu.getVendSelection());
		}
		//if selected RadioButton is the special VM
		else if(this.menu.getCreateRBGroup().getSelection()==this.menu.getSpecialButton().getModel()) {
			//creates instances of the special MVC and its maintenance
			this.menu.setVendSelection("Special");
			this.menu.setVendExists(true);
			this.specialVendView = new SpecialGui();
			this.specModel = new SpecialModel();
			this.maintenance = new Maintenance(this.specModel);
			this.specControl = new SpecialController(menu, specialVendView, specModel, maintenance);
			specControl.initiateMaintenanceActionListeners();
			this.deleteRegularVend();
			System.out.println(this.menu.getVendSelection());
		}
			
		else if(this.menu.getCreateRBGroup().getSelection()==null)
			System.out.println("Please Choose an Option");
	}
	
	/**
	 * Actions for the type of test to run provided a VM currently exists
	 */
	private void confirmTestBtnActions() {
		//Decide on whether to allow each type to coexist or be mutually exclusive
		if(this.menu.getVendExists()) {
			//if the regular VM is selected the user decides to run its features
			if(this.menu.getTestRBGroup().getSelection()==this.menu.getFeaturesRB().getModel() && this.menu.getCreateRBGroup().getSelection()==this.menu.getRegularRB().getModel()) {
				if(this.menu.getVendSelection().equals("Special")){
					System.out.println("You have not created a Regular Vending Machine yet");
				}
				else{
					//hides main menu, initializes item labels, and reveals the RVM Gui
					this.menu.setTestSelection("Regular Features");
					System.out.println(this.menu.getTestSelection());
					this.menu.hideMainMenu();
					this.vendView.initiateItemLabels(this.vendModel.getWholeItemArrayList(), this.vendModel.getItemRecord());
					this.regControl.revealRegVend();
				}
			}
			//if the regular VM is selected and the user decides to run its maintenance
			else if(this.menu.getTestRBGroup().getSelection()==this.menu.getMainTRB().getModel() &&  this.menu.getCreateRBGroup().getSelection()==this.menu.getRegularRB().getModel()) {
				if(this.menu.getVendSelection().equals("Special")){
					System.out.println("You have not created a Regular Vending Machine yet");
				}
				else{
					this.menu.setTestSelection("Regular Maintenance");
					System.out.println(this.menu.getTestSelection());
					this.maintenance.setRestockItemsCurStockLabel(String.format("Current Stock: %d", vendModel.getSingleItemArrayList(maintenance.getRestockItemsCBSelectedIndex()).size()));
					this.maintenance.setChangePriceCurPriceLabel(String.format("Current Price: $%d", vendModel.getItemRecord().get(maintenance.getChangePriceCBSelectedIndex()).getPrice()));
					this.maintenance.setRestockChangeCurStockLabel(String.format("Current Stock: %d", vendModel.getChangeStock()[maintenance.getRestockChangeCBSelectedIndex()]));
					this.menu.hideMainMenu();
					this.regControl.revealMaintenance();
				}
			}
			//if the special VM is selected the user decides to run its features
			else if(this.menu.getTestRBGroup().getSelection()==this.menu.getFeaturesRB().getModel() && this.menu.getCreateRBGroup().getSelection()==this.menu.getSpecialButton().getModel()) {
				if(this.menu.getVendSelection().equals("Regular")){
					System.out.println("You have not created a Special Vending Machine yet");
				}
				else{
					this.menu.hideMainMenu();
					this.specialVendView.showSpecialGUI();
					this.menu.setTestSelection("Special Features");
					//specControl.initiateMaintenanceActionListeners();
					System.out.println(this.menu.getTestSelection());
				}
			}
				//if the special VM is selected and the user decides to run its maintenance	
			else if(this.menu.getTestRBGroup().getSelection()==this.menu.getMainTRB().getModel() &&  this.menu.getCreateRBGroup().getSelection()==this.menu.getSpecialButton().getModel()) {
				if(this.menu.getVendSelection().equals("Regular")){
					System.out.println("You have not created a Special Vending Machine yet");
				}
				else{
					this.maintenance.setRestockItemsCurStockLabel(String.format("Current Stock: %d", specModel.getSingleItemArrayList(maintenance.getRestockItemsCBSelectedIndex()).size()));
					this.maintenance.setChangePriceCurPriceLabel(String.format("Current Price: $%d", specModel.getItemRecord().get(maintenance.getChangePriceCBSelectedIndex()).getPrice()));
					this.maintenance.setRestockChangeCurStockLabel(String.format("Current Stock: %d", specModel.getChangeStock()[maintenance.getRestockChangeCBSelectedIndex()]));
					this.menu.hideMainMenu();
					this.specControl.revealMaintenance();
					this.menu.setTestSelection("Special Maintenance");
					System.out.println(this.menu.getTestSelection());
				}
			}
				
			else if(this.menu.getTestRBGroup().getSelection()==null)
				System.out.println("Please Choose an Option");
		}
		else System.out.println("Please Create a Vending Machine");
	}

	/*
	* Resets data of RVM
	*/
	private void deleteRegularVend(){
		this.vendView = null;
		this.vendModel = null;
		this.regControl = null;
	}

	/*
	* Resets data of SVM
	*/
	private void deleteSpecialVend(){
		this.specialVendView = null;
		this.specModel = null;
		this.specControl = null;
	}
}
