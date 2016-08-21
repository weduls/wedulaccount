package tableViewer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TableNode {
	 private String name;
	 private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	 
	  public void addPropertyChangeListener(String propertyName,
		      PropertyChangeListener listener) {
		    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}
	 
	 public TableNode(String name){
		 this.name = name;
	 }
	 
	 public String getTableName(){
		 return name;
	 }
	 
	 public void setTableName(String name) {
		    propertyChangeSupport.firePropertyChange("name", this.name,
		        this.name = name);
	}
	 
	 @Override
	  public String toString() {
	    return name;
	  }
}
