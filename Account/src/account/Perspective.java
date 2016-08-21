package account;

import nattable.qbview;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import TreeViewer.catagoryView;


public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView(catagoryView.ID, false, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		layout.addStandaloneView(qbview.ID, false, IPageLayout.RIGHT, 0.5f, layout.getEditorArea());
	}
}
