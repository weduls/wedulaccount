package dialog;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import commonUtil.CommonUtil;

import tableViewer.ModelProvider;
import tableViewer.TableNode;
import account.Application;
import account.IImageKeys;
import account.Message;

public class addpurchaseCatagory extends Dialog{
	private FileWriter fw;
	private Shell shell;
	private Composite composite;
	private Image columnImage;
	private MessageBox messageBox;
	public static TableViewer list_table;
	public static ModelProvider model;
	
public addpurchaseCatagory(Shell parent) {
	super(parent);
	// TODO Auto-generated constructor stub
}
protected void configureShell(Shell newShell) {
	super.configureShell(newShell);
	this.shell = newShell;
	this.shell.setText(Message.addpurchaseCatagory);
}

protected void createButtonsForButtonBar(final Composite parent)
{ 
  GridLayout layout = (GridLayout)parent.getLayout();
  layout.marginHeight = 0;
}

protected Control createDialogArea(Composite parent){
	composite = new Composite(parent, SWT.MAX | SWT.RESIZE);
	shell.layout(true, true);
	
	messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
	//Content Image
	columnImage = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,IImageKeys.Allview).createImage();
	
	//전체 composite layout
	GridLayout gridLayout = new GridLayout(1, true);
	gridLayout.numColumns = 1;
	composite.setLayout(gridLayout);
	
	//group layout
	GridLayout gridlayout_group = new GridLayout(2, false);
	
	//group layoutdata
	GridData common_gridData = new GridData(GridData.FILL, GridData.FILL, true, true);
	
	composite.setLayoutData(common_gridData);
	
	//Button GridData
	GridData gridData_button = new GridData();
	gridData_button.horizontalSpan = 2;
	gridData_button.horizontalAlignment = GridData.END;
	gridData_button.widthHint = 75;
	
	
	
	//purchaseCatagory Group
	final Group group_add = new Group(composite, SWT.NORMAL);
	group_add.setLayout(gridlayout_group);
	group_add.setText(Message.addpurchaseCatagory);
	group_add.setLayoutData(common_gridData);
	
	Label addpurchaseLabel = new Label(group_add, SWT.NORMAL); 
	addpurchaseLabel.setText(Message.addpurchaseLabel);
	final Text addpurchaseText = new Text(group_add, SWT.BORDER);
	
	Button addpurchaseButton = new Button(group_add, SWT.BUTTON1);
	addpurchaseButton.setText(Message.add);
	addpurchaseButton.setLayoutData(gridData_button);
	
	addpurchaseButton.addListener(SWT.Selection, new Listener() {
	      public void handleEvent(Event e) {
	    	  try {
	    		fw = new FileWriter(Application.purchaseCatagory.getPath(),true);
				fw.write(addpurchaseText.getText() + "~");
				fw.flush();
				fw.close();
				model.INSTANCE.refresh();
				list_table.refresh();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }  
	});
		
	//purchaseCatagory List Group
	final Group group_list = new Group(composite, SWT.NORMAL);
	group_list.setLayout(gridlayout_group);
	group_list.setText(Message.addpurchaseCatagoryList);
	group_list.setLayoutData(common_gridData);
	
	
	createTable(group_list);
	
	Button ModifypurchaseButton = new Button(group_list, SWT.BUTTON1);
	ModifypurchaseButton.setText(Message.modify);
	ModifypurchaseButton.addListener(SWT.Selection, new Listener() {
	      public void handleEvent(Event e) {
	    	  if(!list_table.getSelection().toString().equals("<empty selection>")){
	    		  new inputEditpurchaseCatagory(getShell()).open();
	    		  list_table.refresh();
	    	  }else{
	       		  messageBox.setMessage("수정 하고자 하는 리스트를 선택하세요.");
	       		  messageBox.open();
	    		 }
	      }
	});
	    
	Button RemovepurchaseButton = new Button(group_list, SWT.BUTTON1);
	RemovepurchaseButton.setText(Message.remove);
	RemovepurchaseButton.addListener(SWT.Selection, new Listener(){
		 public void handleEvent(Event e) {
			 if(!list_table.getSelection().toString().equals("<empty selection>")){
			 String Object;
			 Object = (String)CommonUtil.subStr((String)list_table.getSelection().toString(), 1, list_table.getSelection().toString().length());
	    	 Object = (String)CommonUtil.subStr((String)Object, 0, Object.length()-1);
	    	 model.INSTANCE.RemoveTablename(Object);
	    	 list_table.refresh();
	    	 
		 }else{
   		  messageBox.setMessage("삭제하고자 하는 리스트를 선택하세요.");
   		  messageBox.open();
		 }
		 }
	});
	
	GridDataFactory.fillDefaults().grab(true, true).applyTo(group_add);
	GridDataFactory.fillDefaults().grab(true, true).applyTo(group_list);
	
	return composite;
	}

	@Override
	protected boolean isResizable()  {
		return true;
	}
	
	public void createTable(Composite parent){
		//TableViewer
		list_table = new TableViewer(parent, SWT.BORDER);
		
		//GridData
		GridData Table_grid = new GridData();
		Table_grid.horizontalSpan=2;
		Table_grid.horizontalAlignment = GridData.FILL;
		Table_grid.verticalAlignment = GridData.FILL;
		Table_grid.grabExcessHorizontalSpace=true;
		Table_grid.grabExcessVerticalSpace=true;
		list_table.getControl().setLayoutData(Table_grid);
		
		list_table.getTable().setLinesVisible(true);
		list_table.getTable().setHeaderVisible(true);
		
		//TableViewer Column
		TableViewerColumn tc = createTableViewerColumn(list_table, Message.addpurchaseCatagoryList, 150, 0);
		list_table.setContentProvider(new ArrayContentProvider());

		//tableColumnLable Provider
		tc.setLabelProvider(new ColumnLabelProvider(){
			@Override
		      public String getText(Object element) {
		        TableNode node = (TableNode) element;
		        return node.getTableName();
		      }
			@Override
		      public Image getImage(Object element) {
				return columnImage;
		      }
		});
		
		// contentProvider
		list_table.setInput(model.INSTANCE.getTablename());
	}
	
	//Column add
	private TableViewerColumn createTableViewerColumn(TableViewer viewer, String title, int bound, final int colNumber) {
	    final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    final TableColumn column = viewerColumn.getColumn();
	    column.setText(title);
	    column.setWidth(bound);
	    column.setResizable(true);
	    column.setMoveable(true);
	    return viewerColumn;
	}
}
