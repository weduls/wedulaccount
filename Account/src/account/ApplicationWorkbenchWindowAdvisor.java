package account;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	public static Image statusImage;
	public static IStatusLineManager statusline;
	
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    //preWindowOpen – 각창이 열릴 때 호출됨 (상태바, 메뉴바 등을 설정)
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(1000, 600));
        configurer.setShowCoolBar(true);
        //configurer.setShowStatusLine(true);
        configurer.setTitle(Message.title); //$NON-NLS-1$
    }
    
    // postWindowOpen - 창이 열린 후 각종 위젯의 리스너 등을 연결하는 데 사용함 
    public void postWindowOpen() {
		statusImage = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.title).createImage(); //image 추가
		statusline = getWindowConfigurer().getActionBarConfigurer().getStatusLineManager();
		statusline.setMessage(statusImage, "위들 가계부");
    }
    
    //initialize - workbench(어플리케이션)을 초기화 하는 작업을 진행할 때 사용된다.
    public void initialize(IWorkbenchConfigurer configurer){
    	configurer.setSaveAndRestore(true);
    }
    
    public static void statusChange(Image statusImage, String string){
    	statusline.setMessage(statusImage, string);
    }
}
