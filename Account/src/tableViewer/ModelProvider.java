package tableViewer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import account.Application;

public enum ModelProvider {
INSTANCE;

	  private static BufferedReader in;
	  private List<TableNode> Node;
	  private String object;
	  private FileWriter fw;

	  private ModelProvider()  {
		  Node = new ArrayList<TableNode>();
		  try {
			setTablename();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

	  public List<TableNode> getTablename() {
	    return Node;
	  }
	  
	  public void refresh(){
		  Node.clear();
		  try {
			setTablename();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  public void setTablename() throws FileNotFoundException{
		  try {
			Node.clear();
			in = new BufferedReader(new FileReader(Application.purchaseCatagory.getPath()));
			while((object = in.readLine()) != null){
				StringTokenizer st = new StringTokenizer(object, "~");
				while(st.hasMoreTokens()){
					Node.add(new TableNode(st.nextToken()));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  public void EditTablename(String before, String after){
		  try {
			fw = new FileWriter(Application.purchaseCatagory.getPath(), false);
			List<TableNode> temp = Node;
			for(TableNode tmp : temp){
				if(before.equals(tmp.getTableName())){
					fw.write(after + "~");
				}else
				{
					fw.write(tmp.getTableName() + "~");
				}
			}
			fw.flush();
			fw.close();
			refresh();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  public void RemoveTablename(String str){
		  try {
				fw = new FileWriter(Application.purchaseCatagory.getPath(), false);
				List<TableNode> temp = Node;
				for(TableNode tmp : temp){
					if(!str.equals(tmp.getTableName()))
					{
						fw.write(tmp.getTableName() + "~");
					}
				}
				fw.flush();
				fw.close();
				refresh();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
} 


