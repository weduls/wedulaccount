package TreeViewer;

import java.util.ArrayList;
import java.util.List;

public class TreeNode{
	 private String name;
	 private String type;
	 private List children = new ArrayList();
	 private TreeNode parent;
	 
	 public TreeNode(String n, String type){
		 this.name = n;
		 this.type = type;
	 }
	 
	 protected Object getParent(){
		 return parent;
	 }
	 
	 public TreeNode addChild(TreeNode child)
	 {
		 children.add(child);
		 child.parent = this;
		 return this;
	 }
	 
	 /*public static boolean isParent(Object obj){
		 if(((TreeNode)obj).getStatus() == 0)
			 return true;
		 else 
			 return false;
	 }*/
	 
	 public String getType(){
		 return type;
	 }
	 
	 public String getName(){
		 return name;
	 }
	 
	 public List getChildren(){
		 return children;
	 }
	 
	 public String toString(){
		 return name;
	 }
}