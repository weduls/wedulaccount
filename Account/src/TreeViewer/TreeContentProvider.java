package TreeViewer;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TreeContentProvider implements ITreeContentProvider{
	public Object[] getChildren(Object parentElement) {
		return ((TreeNode) parentElement).getChildren().toArray();
	}

	public Object getParent(Object element) {
		return ((TreeNode) element).getParent();
	}

	public boolean hasChildren(Object element) {
		return ((TreeNode) element).getChildren().size() > 0;
	}

	public Object[] getElements(Object inputElement) {
		return ((TreeNode) inputElement).getChildren().toArray();
	}

	public void dispose() {
	}
	
	@Override
	public void inputChanged(Viewer viewer, Object oldInput,
			Object newInput) {
		// TODO Auto-generated method stub
		
	}
}
