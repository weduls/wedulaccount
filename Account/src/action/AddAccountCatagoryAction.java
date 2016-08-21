package action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import account.Application;
import account.IImageKeys;
import account.Message;
import dialog.addAccountCatagory;

// Add 계좌, 카드, 내 지갑 추가 액션 
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
		AddDialog.open();
	}
}
