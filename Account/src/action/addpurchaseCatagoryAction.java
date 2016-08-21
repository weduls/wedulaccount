package action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import account.Application;
import account.IImageKeys;
import account.Message;
import dialog.addAccountCatagory;
import dialog.addpurchaseCatagory;

public class addpurchaseCatagoryAction extends Action {
	private final IWorkbenchWindow window;

	public final static String ID = "Account.addpurchaseCatagoryAction";
	
	public addpurchaseCatagoryAction(IWorkbenchWindow window){
		this.window = window;
		setId(ID);
		setText(Message.addpurchaseCatagory);
		setToolTipText(Message.addpurchaseCatagory);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.addpurchase));
	}
	public void run() {
		addpurchaseCatagory AddDialog = new addpurchaseCatagory(window.getShell());
		int code = AddDialog.open();
	}
}
