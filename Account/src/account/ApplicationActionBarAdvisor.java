package account;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import action.AddAccountCatagoryAction;
import action.addAccountContentAction;
import action.addpurchaseCatagoryAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IWorkbenchAction exitAction;
	private AddAccountCatagoryAction AddAccountCatagory;
	private addAccountContentAction addAccountContent;
	private addpurchaseCatagoryAction addpurchaseCatagory;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		AddAccountCatagory = new AddAccountCatagoryAction(window);
		register(AddAccountCatagory);
		addAccountContent = new addAccountContentAction(window);
		register(addAccountContent);
		addpurchaseCatagory = new addpurchaseCatagoryAction(window);
		register(addpurchaseCatagory);
    }

    //Add Menu Context Manager
    protected void fillMenuBar(IMenuManager menuBar) {
    	MenuManager QueryBrower = new MenuManager("&파일", Message.titlefile);
    	MenuManager wedulAccount = new MenuManager("&위들 가계부", Message.titleAction);
    	wedulAccount.add(AddAccountCatagory);
    	wedulAccount.add(addpurchaseCatagory);
    	wedulAccount.add(new Separator());
    	wedulAccount.add(addAccountContent);
    	QueryBrower.add(exitAction);
    	menuBar.add(QueryBrower);
    	menuBar.add(wedulAccount);
    }
    
    //Add ToolBar Content
    protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
		coolBar.add(toolbar);
		toolbar.add(AddAccountCatagory);
		toolbar.add(addpurchaseCatagory);
		toolbar.add(new Separator());
		toolbar.add(addAccountContent);
	}
}
