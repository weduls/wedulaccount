package dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tableViewer.ModelProvider;
import account.Message;
import cUtil.CommonUtil;

public class inputEditpurchaseCatagory extends Dialog {
	private Shell shell;
	private Composite composite;
	private MessageBox messageBox;
	
	public inputEditpurchaseCatagory(Shell parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		this.shell = newShell;
		this.shell.setText(Message.modify);
	}
	
	protected void createButtonsForButtonBar(final Composite parent)
	{ 
	  GridLayout layout = (GridLayout)parent.getLayout();
	  layout.marginHeight = 0;
	}
	
	protected Control createDialogArea(Composite parent){
		composite = new Composite(parent, SWT.MAX);
		messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
		
		GridLayout gridLayout = new GridLayout(1, true);
		gridLayout.numColumns = 1;
		composite.setLayout(gridLayout);
		
		//group layout
		GridLayout gridlayout_group = new GridLayout(2, false);
		
		//group layoutdata
		GridData common_gridData = new GridData(GridData.FILL, GridData.FILL, true, true);
		
		composite.setLayoutData(common_gridData);
		
		//Button GridData
		GridData gridData_button = new GridData();
		gridData_button.horizontalSpan = 2;
		gridData_button.horizontalAlignment = GridData.END;
		gridData_button.widthHint = 75;
		
		
		
		//purchaseCatagory Group
		final Group group_add = new Group(composite, SWT.NORMAL);
		group_add.setLayout(gridlayout_group);
		group_add.setText(Message.modify);
		group_add.setLayoutData(common_gridData);
		
		Label addpurchaseLabel = new Label(group_add, SWT.NORMAL); 
		addpurchaseLabel.setText(Message.addpurchaseLabel);
		final Text addpurchaseText = new Text(group_add, SWT.BORDER);
		
		Button addpurchaseButton = new Button(group_add, SWT.BUTTON1);
		addpurchaseButton.setText(Message.modify);
		addpurchaseButton.setLayoutData(gridData_button);
		
		addpurchaseButton.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event e) {
		    	  String Object;
		    	  Object = (String)CommonUtil.subStr((String)addpurchaseCatagory.list_table.getSelection().toString(), 1, addpurchaseCatagory.list_table.getSelection().toString().length());
		    	  Object = (String)CommonUtil.subStr((String)Object, 0, Object.length()-1);
		    	  addpurchaseCatagory.model.INSTANCE.EditTablename(Object, addpurchaseText.getText());
		    	  shell.dispose();
		     }  
		});
		
		return composite;
	}
}
