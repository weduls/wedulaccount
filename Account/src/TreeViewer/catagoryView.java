package TreeViewer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import account.Application;
import account.GetTransactionInfo;
import account.Message;

import commonUtil.CommonUtil;

	/*
	 * 왼쪽의 결재 컨텐츠를 출력하는 트리를 생성하는 클래스
	 * 
	 */
public class catagoryView extends ViewPart {
	public static final String ID = "AccoutBook.views.catagory";
	private Map<String , List> map = new HashMap<String, List>();
	public static List<TreeNode> TreeList;
	public static List<String> parent, allView, wallet, card, bank;
	public static List<String> wallet_default, card_default, bank_default;
	public static TreeViewer treeViewer;
	public static TreeNode root;

	public static String Object;
	private String object;
	public static BufferedReader in;
	private String tail, Prev;
	
	public catagoryView() {
		super();// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);
		
		//LabelProvider 설정
		treeViewer.setLabelProvider(new TreeLabelProvider(){
		});
		
		//ContentProvider 설정
		treeViewer.setContentProvider(new TreeContentProvider() {
		});
		
		//Data 추가
		treeViewer.setInput(getRootNode());
		
		//Listener
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			  @Override
			  public void doubleClick(DoubleClickEvent event) {
			    TreeViewer viewer = (TreeViewer) event.getViewer();
			    IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection(); 
			    Object selectedNode = thisSelection.getFirstElement(); 
			    if(viewer.getExpandedState(selectedNode))
			    	viewer.collapseToLevel(selectedNode, 1);
			    else
			    	viewer.expandToLevel(selectedNode, 1);
			    	
			   /* viewer.setExpandedState(selectedNode,
			        !viewer.getExpandedState(selectedNode));*/
			  }
		}); 
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event){
				//select Object Name 
				String SelectionObject = event.getSelection().toString();
				SelectionObject = (String)CommonUtil.subStr((String)SelectionObject, 1,  SelectionObject.length());//SelectionObject.substring(1);
				SelectionObject = (String)CommonUtil.subStr((String)SelectionObject, 0,  SelectionObject.length()-1);
				if(SelectionObject.equals(Message.allView) || SelectionObject.equals(Message.wallet) || SelectionObject.equals(Message.bank) || SelectionObject.equals(Message.sinyoungcard))
					GetTransactionInfo.selectionHeaderView(SelectionObject);
				else
					GetTransactionInfo.selectionView(SelectionObject);
			}
		});
		
	}
	
	public TreeNode getRootNode() {
		setRootMap();
		setContentsMap();
		TreeList = new LinkedList<TreeNode>();
		root = new TreeNode("root","");
		
		//Listener
		
		TreeNode temp;
		
		parent = map.get("parent");
		
		Iterator<String> itr_parent = parent.iterator();
		Iterator<String> itr_bank = bank.iterator();
		Iterator<String> itr_card = card.iterator();
		Iterator<String> itr_allView = allView.iterator();
		Iterator<String> itr_wallet = wallet.iterator();
		while(itr_parent.hasNext()){
			Object = itr_parent.next();
			temp = new TreeNode(Object, Object);
			TreeList.add(temp);
			root.addChild(temp);
			
			switch(Object){
			case "전체보기":
				while(itr_allView.hasNext()){
					object=itr_allView.next();
					temp.addChild(new TreeNode(object,object));
				}
				break;
			
			case "지갑":
				while(itr_wallet.hasNext()){
					object=itr_wallet.next();
					temp.addChild(new TreeNode(object,object));
				}
				break;
				
			case "카드":
				while(itr_card.hasNext()){
					object=itr_card.next();
					temp.addChild(new TreeNode(object,object));
				}
				break;
				
			case "은행":
				while(itr_bank.hasNext()){
					object=itr_bank.next();
					temp.addChild(new TreeNode(object,object));
				}
				break;
			}
		}
		return root;
	}
	
	public void setRootMap(){
		try {
			in = new BufferedReader(new FileReader(Application.TreeViewerRoot.getPath()));
			parent = new LinkedList();
			try {
				while((Object = in.readLine())!= null){
					parent.add(Object);
				}
				map.put("parent", parent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setContentsMap(){
		try {
			in = new BufferedReader(new FileReader(Application.TreeViewerContents.getPath()));
			allView = new LinkedList();
			card = new LinkedList();
			bank = new LinkedList();
			wallet = new LinkedList();		
			
			try {
				while((Object = in.readLine())!= null){
					StringTokenizer st = new StringTokenizer(Object,"~");
					Prev = st.nextToken();
					tail = st.nextToken();
					switch(Prev){
					case "전체보기":
						allView.add(tail);
						break;
					
					case "지갑":
						wallet.add(tail);
						break;
						
					case "카드":
						card.add(tail);
						break;
						
					case "은행":
						bank.add(tail);
						break;
						
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * rokki
	 * 2016. 8. 21.
	 * Account
	 * 
	 * 트리에 결재 컨텐츠를 등록할때 사용하는 되는 Default 내용을 생성하는 부분
	 * 
	 */
	public static void setDefaultContent() {
		try {
			in = new BufferedReader(new FileReader(Application.DefaultContentList.getPath()));
			wallet_default = new LinkedList<String>();
			card_default = new LinkedList<String>();
			bank_default = new LinkedList<String>();
			String tempObject;
			int type = 0;
			try {
				while ((tempObject = in.readLine()) != null) {
					if(tempObject.compareTo(Message.sinyoungcard) == 0){
						type = 1;
						tempObject = in.readLine();
					}
					else if(tempObject.compareTo(Message.wallet) == 0){
						type = 0;
						tempObject = in.readLine();
					}
					else if(tempObject.compareTo(Message.bank) == 0){
						type = 2;
						tempObject = in.readLine();
					}
					
					if(type == 0 && tempObject != null)
						wallet_default.add(tempObject);
					else if(type == 1 && tempObject != null)
						card_default.add(tempObject);
					else if(type == 2 && tempObject != null)
						bank_default.add(tempObject);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		treeViewer.getControl().setFocus();
	}
}
