package dialog;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import TreeViewer.TreeNode;
import TreeViewer.catagoryView;
import account.Application;
import account.Message;


public class addAccountCatagory extends Dialog {
	private Composite composite;
	private Iterator<String> itr;
	private Combo type_combo;
	private String Object;
	private Shell shell;
	private Label addContent_label;
	private Combo addContent_combo;
	private Label addContent_inputlabel;
	private Text inputText;
	private Label addBalance;
	private Text inputBalance;
	private FileWriter fw;
	
	public addAccountCatagory(Shell parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		try {
			fw = new FileWriter(Application.TreeViewerContents.getPath(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.shell = newShell;
		this.shell.setText(Message.addAccountCatagory);
	}
	
	protected Control createDialogArea(Composite parent){
		composite = new Composite(parent, SWT.None);
		catagoryView.setDefaultContent();
		
		//전체 composite layout
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		//gridLayout.makeColumnsEqualWidth = true;
		composite.setLayout(gridLayout);
		
		//group layout
		GridLayout gridlayout_group = new GridLayout();
		gridlayout_group.numColumns = 2;
		
		//combo box layout
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace=true;
		
		final Group group = new Group(composite, SWT.NORMAL);
		group.setLayout(gridlayout_group);
		group.setText(Message.addAccount);
		Label type_label = new Label(group, SWT.NORMAL);
		type_label.setText(Message.ChoseType);
		type_combo = new Combo(group, SWT.BORDER);
		type_combo.setLayoutData(gridData);
		type_comboContents();

		//type_combo listener
		type_combo.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		          if (type_combo.getText().compareTo(Message.wallet) == 0) {
		        	  createWallet(group);
		          } else if(type_combo.getText().compareTo(Message.sinyoungcard) == 0){
		        	  createCard(group);
		          } else if(type_combo.getText().compareTo(Message.bank) == 0){
		        	  createBank(group);  
		          }
		          //createBalance(group);
		          composite.pack();
		          shell.pack();
		        }
		 });
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(group);
		
		return composite;
	}
	
	protected void okPressed(){
		try {
			fw.write(type_combo.getText()+"~");
			if (addContent_combo.getText().compareTo(Message.selfWrite) == 0) {
				fw.write(inputText.getText()+"~");
			} else {
				fw.write(addContent_combo.getText()+"~");
			}
			fw.write(inputBalance.getText()+"\n");
			
			fw.flush();
			fw.close();
			
			switch(type_combo.getText()){
			case "지갑":
				if (addContent_combo.getText().compareTo(Message.selfWrite) == 0) 
					catagoryView.TreeList.get(1).addChild(new TreeNode(inputText.getText(),Message.selfWrite));
				else
					catagoryView.TreeList.get(1).addChild(new TreeNode(addContent_combo.getText(),addContent_combo.getText()));
				break;
			case "카드":
				if (addContent_combo.getText().compareTo(Message.selfWrite) == 0) 
					catagoryView.TreeList.get(2).addChild(new TreeNode(inputText.getText(),Message.selfWrite));
				else
					catagoryView.TreeList.get(2).addChild(new TreeNode(addContent_combo.getText(),addContent_combo.getText()));
				break;
			case "은행":
				if (addContent_combo.getText().compareTo(Message.selfWrite) == 0) 
					catagoryView.TreeList.get(3).addChild(new TreeNode(inputText.getText(),Message.selfWrite));
				else
					catagoryView.TreeList.get(3).addChild(new TreeNode(addContent_combo.getText(),addContent_combo.getText()));
				break;
			}
			catagoryView.treeViewer.refresh(true);
			
			shell.dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void type_comboContents(){
		itr = catagoryView.parent.iterator();
		while(itr.hasNext()){
			Object = itr.next();
			if(!Object.equals(Message.allView)){
				type_combo.add(Object);
			}
		}
		type_combo.setText("-------------------");
	}
	
	public void createWallet(Composite composite){
		if(composite instanceof Group){
			//label
			if(addContent_label != null)
				addContent_label.dispose();
			
			addContent_label = new Label(composite, SWT.NORMAL);
			addContent_label.setText(Message.ChoseWallet);
			
			//combobox
			if(addContent_combo != null)
				addContent_combo.dispose();
			addContent_combo = new Combo(composite, SWT.NORMAL);
			
			itr = catagoryView.wallet_default.iterator();
			while(itr.hasNext()){
				Object = itr.next();
				addContent_combo.add(Object);
			}
			addContent_combo.add(Message.selfWrite);
			addContent_combo.select(0);
			
			addContent_combo.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if(addContent_combo.getText().compareTo(Message.selfWrite)==0)
					{
						inputText.setEditable(true);
						inputText.setFocus();
						
					}
				}
			});
			createTextBox(composite);
			
			composite.pack();
			composite.layout(true);
		}
	}
	
	public void createCard(Composite composite){
		if(composite instanceof Group){
			//label
			if(addContent_label != null)
				addContent_label.dispose();
			
			addContent_label = new Label(composite, SWT.NORMAL);
			addContent_label.setText(Message.ChoseSinyoungCard);
			
			//combobax
			if(addContent_combo != null)
				addContent_combo.dispose();
			addContent_combo = new Combo(composite, SWT.NORMAL);
			
			itr = catagoryView.card_default.iterator();
			while(itr.hasNext()){
				Object = itr.next();
				addContent_combo.add(Object);
			}
			addContent_combo.add(Message.selfWrite);
			addContent_combo.select(0);
			
			addContent_combo.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if(addContent_combo.getText().compareTo(Message.selfWrite)==0)
					{
						inputText.setEditable(true);
						inputText.setFocus();
					}
				}
			});
			
			createTextBox(composite);
			
			composite.pack();
			composite.layout(true);
		}
	}
	
	public void createBank(Composite composite){
		if(composite instanceof Group){
			//label
			if(addContent_label != null)
				addContent_label.dispose();
			
			addContent_label = new Label(composite, SWT.NORMAL);
			addContent_label.setText(Message.ChoseBank);
			
			//combobax
			if(addContent_combo != null)
				addContent_combo.dispose();
			addContent_combo = new Combo(composite, SWT.NORMAL);
			
			itr = catagoryView.bank_default.iterator();
			while(itr.hasNext()){
				Object = itr.next();
				addContent_combo.add(Object);
			}
			addContent_combo.add(Message.selfWrite);
			addContent_combo.select(0);
			
			addContent_combo.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if(addContent_combo.getText().compareTo(Message.selfWrite)==0)
					{
						inputText.setEditable(true);
						inputText.setFocus();
					}
				}
			});
			
			createTextBox(composite);
			composite.pack();
			composite.layout(true);
		}
	}
	
	public void createTextBox(Composite composite){
		if(composite instanceof Group){
			if(addContent_inputlabel != null)
				addContent_inputlabel.dispose();
			addContent_inputlabel = new Label(composite, SWT.NORMAL);
			addContent_inputlabel.setText(Message.SelfWrite2);
			
			if(inputText != null)
				inputText.dispose();
			inputText = new Text(composite,SWT.NORMAL);
			inputText.setEditable(false);
			
			if(addBalance != null)
				addBalance.dispose();
			if(inputBalance != null)
				inputBalance.dispose();
			
			addBalance = new Label(composite, SWT.NORMAL);
			addBalance.setText(Message.Balance);
			
			inputBalance = new Text(composite,SWT.NORMAL);
		}
	}
}
