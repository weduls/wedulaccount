package account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import nattable.qbview;

import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public static final String PLUGIN_ID = "Account";
	public static URL url = InternalPlatform.getDefault().getInstallLocation().getURL();
	public static File workspace;
	public static File Content;
	public static File TreeViewerRoot;
	public static File TreeViewerContents;
	public static File DefaultContentList;
	public static File purchaseCatagory;
	public static BufferedReader in;
	public static String Object;
	FileWriter fw;
	
	public Object start(IApplicationContext context) throws Exception {
		CreateWorkSpace();

		Display display = PlatformUI.createDisplay();
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
				return IApplication.EXIT_RESTART;
			else
				return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
	
	public void CreateWorkSpace(){
		
		qbview.columns.add("날짜");
		qbview.columns.add("지출 내역");
		qbview.columns.add("결제 항목(대분류)");
		qbview.columns.add("결제 항목(소분류)");
		qbview.columns.add("금액");
		qbview.columns.add("수익/지출");
		qbview.columns.add("잔액");
		qbview.columns.add("내용");
		
		//workspace 폴더 생성
		workspace = new File(url.toString().substring(6, url.toString().length()-1)+"/workspace");
		if(!workspace.exists()){
			workspace.mkdirs();
		}
		else
			System.out.println("Workspace is already exist");
		
		//TreeViewRoot 항목 생성을 포함하는 txt 생성
		Content = new File(workspace.getPath() + "/Content.txt");
		if(!Content.exists()){
			try {
				fw = new FileWriter(Content,true);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			System.out.println("Content is already exist");
		
		//TreeViewRoot 항목 생성을 포함하는 txt 생성
		TreeViewerRoot = new File(workspace.getPath() + "/TreeViewerRoot.txt");
		if(!TreeViewerRoot.exists()){
			try {
				fw = new FileWriter(TreeViewerRoot,true);
				fw.write(Message.allView+"\n");
				fw.write(Message.wallet+"\n");
				fw.write(Message.sinyoungcard+"\n");
				fw.write(Message.bank+"\n");
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("TreeViewerRoot is already exist");
		
		
		//TreeViewerContents 항목 생성을 포함하는 txt 생성
		TreeViewerContents = new File(workspace.getPath() + "/TreeViewerContents.txt");
		if(!TreeViewerContents.exists()){
			try {
				fw = new FileWriter(TreeViewerContents,true);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			System.out.println("TreeViewerContents is already exist");
		
		purchaseCatagory = new File(workspace.getPath() + "/purchaseCatagory.txt");
		if(!purchaseCatagory.exists()){
			try {
				fw = new FileWriter(purchaseCatagory,true);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			System.out.println("purchaseCatagory is already exist");
		
		DefaultContentList = new File(workspace.getPath() + "/DefaultContentList.txt");
		if(!DefaultContentList.exists()){
			try {
				fw = new FileWriter(DefaultContentList, true);
				fw.write(Message.sinyoungcard+"\n");
				fw.write(Message.woricard+"\n"+Message.BCcard+"\n"+Message.IBKcard+"\n"+Message.hanacard+"\n");
				fw.write(Message.bank+"\n");
				fw.write(Message.woribank+"\n"+Message.sinhanbank+"\n"+Message.hanabank+"\n"+Message.kbbank+"\n"+Message.kiupbank+"\n"+Message.nonghyup+"\n"+Message.saemayul+"\n"+Message.scbank+"\n"+Message.suhyup+"\n"+Message.yuchaeguk+"\n");
				fw.write(Message.wallet+"\n");
				fw.write(Message.Mywallet+"\n");
				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			System.out.println("DefaultCententList is already exist");
		
		getTransactionInfo.allView();		
	}
}
