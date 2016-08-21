package action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import account.Application;
import account.IImageKeys;
import account.Message;
import account.catagoryView;
import dialog.addAccountCatagory;

public class AddAccountCatagoryAction extends Action {
	private final IWorkbenchWindow window;

	public final static String ID = "Account.addDialog";
	
	public AddAccountCatagoryAction(IWorkbenchWindow window){
		this.window = window;
		setId(ID);
		setText("&결재 항목 추가");
		setToolTipText(Message.addAccount);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.AddDialog));
	}
	public void run() {
		addAccountCatagory AddDialog = new addAccountCatagory(window.getShell());
		int code = AddDialog.open();
	}
}
