package action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import account.Application;
import account.IImageKeys;
import account.Message;
import dialog.addAccountCatagory;

public class addAccountContentAction extends Action {
	private final IWorkbenchWindow window;

	public final static String ID = "Account.addDialog";
	
	public addAccountContentAction(IWorkbenchWindow window){
		this.window = window;
		setId(ID);
		setText("&가계부 입력");
		setToolTipText(Message.inputContent);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.input));
	}
	public void run() {
	}
}
