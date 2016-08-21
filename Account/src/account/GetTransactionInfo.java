package account;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import nattable.qbview;

public class GetTransactionInfo {
	public static Map<String, String> valueRow;
	public static String Object;
	public static BufferedReader in;
	private FileWriter fw;

	
	public static void selectionView(String str){
		try {
			int count=0;
			in = new BufferedReader(new FileReader(Application.Content.getPath()));
			try {
				while((Object = in.readLine()) != null){
					valueRow = new HashMap<String, String>();
					StringTokenizer st = new StringTokenizer(Object, "~");
					count=0;
					while(st.hasMoreTokens()){
						valueRow.put(qbview.columns[count], st.nextToken());
						count++;
					}
					qbview.values.add(valueRow);
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
	 * @param str
	 * @param st
	 * @return
	 */
	public static boolean CheckStingtokenizer(String str, StringTokenizer st){
		while(st.hasMoreTokens())
		{
			if(st.nextToken().equals(str))
				return true;
		}
		return false;
	}
	
	/**
	 * @param str
	 * 
	 * 선택된 트리뷰어 데이터 출력
	 * 
	 */
	public static void selectionHeaderView(String str){
		if(str.equals(Message.allView)){
			allView();
			qbview.changeData();
		}
		else{
			try {
				qbview.values.clear();
				int i=0;
				in = new BufferedReader(new FileReader(Application.Content.getPath()));
				try {
					while((Object = in.readLine()) != null){
						valueRow = new HashMap<String, String>();
						StringTokenizer st = new StringTokenizer(Object, "~");
						StringTokenizer Copy_st = new StringTokenizer(Object, "~");
						if (CheckStingtokenizer(str, Copy_st)) {
							i = 0;
							while (st.hasMoreTokens()) {
								valueRow.put(qbview.columns[i],	st.nextToken());
								i++;
							}
							qbview.values.add(valueRow);
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
			qbview.changeData();
		}
	}
	
	/**
	 * 트리 뷰어에서 전체 데이터 보여주기
	 */
	public static void allView(){
		try {
			qbview.values.clear();
			int i=0;
			in = new BufferedReader(new FileReader(Application.Content.getPath()));
			try {
				while((Object = in.readLine()) != null){
					valueRow = new HashMap<String, String>();
					StringTokenizer st = new StringTokenizer(Object, "~");
					i=0;
					while(st.hasMoreTokens()){
						valueRow.put(qbview.columns[i], st.nextToken());
						i++;
					}
					qbview.values.add(valueRow);
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
}
