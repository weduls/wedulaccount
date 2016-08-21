package account;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class TreeLabelProvider implements ILabelProvider {
	private List listeners;
	private Image wallet;
	private Image card;
	private Image allview;
	private Image bank;
	private Image hana;
	private Image kb;
	private Image kiup;
	private Image nonghyup;
	private Image saemaul;
	private Image sc;
	private Image sinhan;
	private Image suhyup;
	private Image ucheguk;
	private Image uri;
	private Image normal; 
	private Image etc;
	
	public TreeLabelProvider(){
		listeners = new ArrayList();
		wallet = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.Wallet).createImage(); //image Ãß°¡
		card = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.Card).createImage();
		allview = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.Allview).createImage(); 
		bank = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.Bank).createImage(); 
		hana = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.hana).createImage();
		kb = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.kb).createImage();
		kiup = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.kiup).createImage();
		nonghyup = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.nonghyup).createImage();
		saemaul = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.saemaul).createImage();
		sc = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.sc).createImage();
		sinhan = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.sinhan).createImage();
		suhyup = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.suhyup).createImage();
		ucheguk = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.ucheguk).createImage();
		uri = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.uri).createImage();
		normal = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.normal).createImage();
		etc = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.etc).createImage();
	}
	
	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		listeners.add(listener);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		listeners.remove(listener);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		String type = ((TreeNode) element).getType();
		if (type.compareTo(Message.wallet) == 0 || type.compareTo(Message.Mywallet)== 0) {
			return wallet;
		} else if (type.compareTo(Message.sinyoungcard) == 0) {
			return card;
		} else if (type.compareTo(Message.allView) == 0) {
			return allview;
		} else if (type.compareTo(Message.bank) == 0){
			return bank;
		}else if (type.compareTo(Message.hanabank) == 0){
			return hana;
		}else if (type.compareTo(Message.kbbank) == 0){
			return kb;
		}else if (type.compareTo(Message.kiupbank) == 0){
			return kiup;
		}else if (type.compareTo(Message.nonghyup) == 0){
			return nonghyup;
		}else if (type.compareTo(Message.saemayul) == 0){
			return saemaul;
		}else if (type.compareTo(Message.scbank) == 0){
			return sc;
		}else if (type.compareTo(Message.sinhanbank) == 0){
			return sinhan;
		}else if (type.compareTo(Message.suhyup) == 0){
			return suhyup;
		}else if (type.compareTo(Message.yuchaeguk) == 0){
			return ucheguk;
		}else if (type.compareTo(Message.woribank) == 0){
			return uri;
		}else if (type.compareTo(Message.selfWrite) == 0){
			return normal;
		}
		else {
			return etc;
		}
	}

	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		String name = ((TreeNode) element).getName();
		return name;
	}

}
