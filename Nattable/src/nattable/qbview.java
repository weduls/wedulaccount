package nattable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.command.StructuralRefreshCommand;
import org.eclipse.nebula.widgets.nattable.config.ConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.DefaultNatTableStyleConfiguration;
import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultCornerDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultRowHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.CornerLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.DefaultRowHeaderDataLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.GridLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.RowHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.config.DefaultColumnHeaderStyleConfiguration;
import org.eclipse.nebula.widgets.nattable.layer.stack.DefaultBodyLayerStack;
import org.eclipse.nebula.widgets.nattable.painter.cell.BackgroundPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.TextPainter;
import org.eclipse.nebula.widgets.nattable.reorder.command.ColumnReorderCommand;
import org.eclipse.nebula.widgets.nattable.resize.command.ColumnResizeCommand;
import org.eclipse.nebula.widgets.nattable.selection.config.DefaultSelectionStyleConfiguration;
import org.eclipse.nebula.widgets.nattable.sort.config.SingleClickSortConfiguration;
import org.eclipse.nebula.widgets.nattable.sort.painter.SortableHeaderTextPainter;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.ui.menu.HeaderMenuConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;

public class qbview extends ViewPart {
	public static final String ID = "NattableTest.views.qbview";
	public static NatTable natTable;
	public static List<String> columns = new ArrayList<String>();
	public static List<Object> values = new ArrayList<Object>();

	public qbview() {
		// TODO Auto-generated constructor stub
		super();
	}

	public void createPartControl(final Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns=1;
		gridLayout.makeColumnsEqualWidth = true;
		
		parent.setLayout(gridLayout);
		
		createCalendar(parent);	
		createNattable(parent);
				
		
	}
	
	public void createNattable(Composite parent){
		ConfigRegistry configRegistry = new ConfigRegistry();

		// create the body layer stack
		IDataProvider bodyDataProvider = new ListDataProvider(this.values, new MyColumnPropertyAccessor());
		final DataLayer bodyDataLayer = new DataLayer(bodyDataProvider);
		DefaultBodyLayerStack bodyLayerStack = new DefaultBodyLayerStack(bodyDataLayer);

		// create the column header layer stack
		IDataProvider columnHeaderDataProvider = new SimpleColumnHeaderDataProvider();
		ILayer columnHeaderLayer = new ColumnHeaderLayer(new DataLayer(columnHeaderDataProvider), bodyLayerStack.getViewportLayer(), bodyLayerStack.getSelectionLayer());
		
		// create the row header layer stack
		IDataProvider rowHeaderDataProvider = new DefaultRowHeaderDataProvider(bodyDataProvider);
		ILayer rowHeaderLayer = new RowHeaderLayer(
				new DefaultRowHeaderDataLayer(new DefaultRowHeaderDataProvider(bodyDataProvider)), bodyLayerStack.getViewportLayer(), bodyLayerStack.getSelectionLayer());

		// create the corner layer stack
		ILayer cornerLayer = new CornerLayer(new DataLayer(new DefaultCornerDataProvider(columnHeaderDataProvider, rowHeaderDataProvider)), rowHeaderLayer, columnHeaderLayer);

		// create the grid layer composed with the prior created layer stacks
		GridLayer gridLayer = new GridLayer(bodyLayerStack, columnHeaderLayer, rowHeaderLayer, cornerLayer);
		/*InitializeAutoResizeColumnsCommand command = new InitializeAutoResizeColumnsCommand(
				gridLayer, 0, configRegistry, new GCFactory(natTable));
		gridLayer.doCommand(command);*/
		
		
		
		//createDatacalendar(parent);
		
		natTable = new NatTable(parent, gridLayer, false);
		natTable.setConfigRegistry(configRegistry);
		natTable.addConfiguration(new DefaultNatTableStyleConfiguration());
		natTable.addConfiguration(new HeaderMenuConfiguration(natTable));
		natTable.addConfiguration(getColumnHeaderStyleConfig());
		natTable.addConfiguration(new SingleClickSortConfiguration());
		natTable.addConfiguration(getSelectionStyleConfig());
		natTable.configure();
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(natTable);
	}
	
	public DefaultSelectionStyleConfiguration getSelectionStyleConfig() {
		DefaultSelectionStyleConfiguration configuration = new DefaultSelectionStyleConfiguration();
		configSelectionStyleConfiguration(configuration);
		return configuration;
	}

	public void configSelectionStyleConfiguration(DefaultSelectionStyleConfiguration configuration) {
		Font font = new Font(Display.getCurrent(),"Arial", 10, SWT.BOLD);
		configuration.selectedHeaderFont = font;
		configuration.selectionFont = font;
	}

	
	public DefaultColumnHeaderStyleConfiguration getColumnHeaderStyleConfig() {
		DefaultColumnHeaderStyleConfiguration configuration = new DefaultColumnHeaderStyleConfiguration();
		configColumnHeaderStyleConfiguration(configuration);
		return configuration;
	}

	public void configColumnHeaderStyleConfiguration(DefaultColumnHeaderStyleConfiguration configuration) {
		configuration.hAlign = HorizontalAlignmentEnum.CENTER;
		configuration.renderGridLines = true;
		TextPainter textPainter = new TextPainter(false, true, 3, true);
		ICellPainter newPainter = new SortableHeaderTextPainter(new BackgroundPainter(textPainter));
		configuration.cellPainter = newPainter;
	}


	
	public void createCalendar(Composite parent){
		
		final Label label, label2;
		final Button Findbutton;
		final CDateTime cdt_Start, cdt_End;
		final Button todayButton;
		
		GridLayout gridlayout = new GridLayout();
		//gridlayout.makeColumnsEqualWidth = true;
		gridlayout.numColumns=8;
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace=true;
		
		/*RowLayout Rowlayout = new RowLayout();
		Rowlayout.wrap = true;
		Rowlayout.pack = true;*/
		final Group group = new Group(parent, SWT.NORMAL);
		group.setLayout(gridlayout);
		group.setText(Message.searchLabel);
		label = new Label(group, SWT.NORMAL);
		label.setText(Message.selectDay);
		cdt_Start = new CDateTime(group, CDT.COMPACT | CDT.BUTTON_RIGHT| CDT.DROP_DOWN );
		cdt_Start.setLayoutData(gridData);
		cdt_Start.setSelection(new Date());	
		cdt_Start.setPattern("YY'/'MM'/'dd");
		label2 = new Label(group, SWT.NORMAL);
		label2.setText(" ~ ");
		cdt_End = new CDateTime(group, CDT.COMPACT | CDT.BUTTON_RIGHT| CDT.DROP_DOWN );
		cdt_End.setLayoutData(gridData);
		cdt_End.setSelection(new Date());	
		cdt_End.setPattern("YY'/'MM'/'dd");
		Findbutton = new Button(group, SWT.BUTTON1);
		Findbutton.setText(Message.search);
		todayButton = new Button(group, SWT.BUTTON1);
		todayButton.setText(Message.today);
		todayButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				cdt_Start.setSelection(new Date());
				cdt_End.setSelection(new Date());
			}
		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(group);
	}
	
	public static void changeData(){
		natTable.doCommand(new StructuralRefreshCommand());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	class SimpleColumnHeaderDataProvider implements IDataProvider { //

		@Override
		public Object getDataValue(int columnIndex, int rowIndex) { // 컬럼의
			return columns.get(columnIndex); //$NON-NLS-1$
		}

		@Override
		public void setDataValue(int columnIndex, int rowIndex, Object newValue) { // 구현안됨
			throw new UnsupportedOperationException();
		}

		@Override
		public int getColumnCount() { // 컬럼 사이즈를 나타내는 부분
			return qbview.this.columns.size();
		}

		@Override
		public int getRowCount() {// 구현안됨
			return 1;
		}

	}

	class MyColumnPropertyAccessor implements
            IColumnPropertyAccessor<Map> {

        public Object getDataValue(Map rowObject,
                int columnIndex) {
            return rowObject.get(getColumnProperty(columnIndex));
        }

        public void setDataValue(Map rowObject,
                int columnIndex, Object newValue) {
            rowObject.put(getColumnProperty(columnIndex), newValue.toString());
        }

        @Override
        public int getColumnCount() {
            return qbview.this.columns.size();
        }

        @Override
        public String getColumnProperty(int columnIndex) {
            return (String) qbview.this.columns.get(columnIndex);
        }

        @Override
        public int getColumnIndex(String propertyName) {
            return qbview.this.columns.indexOf(propertyName);
        }
    }
}
