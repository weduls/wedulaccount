package dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import account.Message;

public class addContent extends Dialog{

private Shell shell;
private Composite composite;
private Label Label_date;
private Label Label_purchaseCatagory;
private Label Label_AccountBigCatagory;
private Label Label_AccountSmallCatagory;
private Label Label_Money;
private Label Label_type;
private Label Label_balance;
private Label Label_content;

	public addContent(Shell parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		this.shell = newShell;
		this.shell.setText(Message.addContent);
	}
	
	protected Control createDialogArea(Composite parent){
		composite = new Composite(parent, SWT.MAX | SWT.RESIZE);
		shell.layout(true, true);
		
		//Composite GridLayout
		GridLayout gridlayout = new GridLayout(1, true);
		
		//group layout
		GridLayout gridlayout_group = new GridLayout(4, true);
		
		return composite;
	}
}
