package cn.jxl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;

public class Jxlutil {

	private static final Logger LOG = Logger.getLogger(Jxlutil.class);

	/**
	 * 將向量導出excel
	 * 
	 * @param os
	 *            輸出流 content 數據集合向量 columnData 內容合并序號集合 columnName 表頭合并序號集合
	 * @return void
	 */
	public static void export(OutputStream os, Vector content,
			Integer[] columnData, Map<String, Integer[]> columnName) {
		WritableWorkbook workbook = null;
		Vector inner = null;
		String value = "";// 存放在cell中的文本值
		try {
			workbook = Workbook.createWorkbook(os);
			// workbook = Workbook.createWorkbook(new File("D://a.xls"));
			// 创建工作薄
			WritableSheet worksheet = workbook.createSheet("record", 0);// 创建第一个工作表，name:工作表名称
			Label label = null;// 用于写入文本内容到工作表中去
			// 开始写入内容
			for (int i = 0; i < content.size(); i++) {
				inner = (Vector) content.get(i);// 获取一条记录
				for (int j = 0; j < inner.size(); j++) {
					value = (String) inner.get(j);
					label = new Label(j, i, value);
					worksheet.addCell(label);
				}
			}
			// mergerColumn(worksheet, columnName);
			// mergerRows(worksheet, columnData);
			workbook.write();
			// workbook.close();
		} catch (Exception e) {
			LOG.error(e.toString());
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					LOG.error(e.toString());
				}
			}
		}
	}

	/**
	 * 合并excel sheet中的同列相同內容的行
	 * 
	 * @param worksheet
	 *            工作表對象 columnData 內容合并序號集合
	 * @return void
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public static void mergerRows(WritableSheet worksheet, Integer[] columnData)
			throws RowsExceededException, WriteException {
		if (worksheet == null || columnData == null || columnData.length <= 0) {
			return;
		}
		int rows = worksheet.getRows(); // 獲取工作表中的行數
		int columns = worksheet.getColumns(); // 獲取工作表中的列表
		if (rows <= 1 || columns <= 0) {
			return;
		}
		for (Integer column : columnData) {
			int col = column.intValue();
			// 去除無效的列
			if (col >= columns) {
				continue;
			}
			int beginRow = 0;
			while (beginRow < rows) {
				int row = getMaxSameContent(worksheet, beginRow, col, rows);
				if (row != -1) {
					worksheet.mergeCells(col, beginRow, col, row);
					beginRow = row + 1;
				} else {
					beginRow++;
				}
			}
		}

	}

	/**
	 * 獲取excel sheet中的同列中相同內容的最大行位置
	 * 
	 * @param worksheet
	 *            工作表對象 columnData 內容合并序號集合
	 * @return void
	 */
	public static int getMaxSameContent(WritableSheet worksheet, int beginRow,
			int beginColumn, int rows) {
		String cellValue = worksheet.getCell(beginColumn, beginRow)
				.getContents().toString();
		int i = beginRow + 1;
		for (; i < rows; i++) {
			String value = worksheet.getCell(beginColumn, i).getContents()
					.toString();
			if (value == null || "".equals(value) || !value.equals(cellValue)) {
				break;
			}
		}
		if (i == beginRow + 1) {
			return -1;
		} else {
			return i - 1;
		}
	}

	/**
	 * 合并表頭
	 * 
	 * @param worksheet
	 *            工作表對象 columnName 表頭合并序號集合
	 * @return void
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public static void mergerColumn(WritableSheet worksheet,
			Map<String, Integer[]> columnName) throws RowsExceededException,
			WriteException {
		if (worksheet == null || columnName == null || columnName.size() <= 0) {
			return;
		}
		int rows = worksheet.getRows(); // 獲取工作表中的行數
		int columns = worksheet.getColumns(); // 獲取工作表中的列表
		if (rows <= 0 || columns <= 1) {
			return;
		}
		/**
		 * 復制表頭
		 */
		worksheet.insertRow(0);
		copyRow(worksheet, 1, 0);
		/**
		 * 記錄不需要進行合并的表頭
		 */
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < columns; i++) {
			list.add(i);
		}
		/**
		 * 合并業務層的表頭
		 */
		Iterator<String> it = columnName.keySet().iterator();
		while (it.hasNext()) {
			String name = it.next();
			Integer[] column = columnName.get(name);
			if (column == null || column.length <= 1) {
				return;
			}
			int beginColumn = column[0];
			int endColumn = column[0];
			list.remove(column[0]);
			for (int i = 1; i < column.length; i++) {
				if (column[i] < beginColumn) {
					beginColumn = column[i];
				}
				if (column[i] > endColumn) {
					endColumn = column[i];
				}
				list.remove(column[i]);
			}
			Label label = new Label(beginColumn, 0, name);
			worksheet.addCell(label);
			worksheet.mergeCells(beginColumn, 0, endColumn, 0);
		}
		/**
		 * 合關兩行表頭中名稱一致的表頭
		 */
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			worksheet.mergeCells(list.get(i), 0, list.get(i), 1);
		}
	}

	/**
	 * 復制行
	 * 
	 * @param worksheet
	 *            工作表對象 fromRow 被復制行 toRow 復制行
	 * @return void
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public static void copyRow(WritableSheet worksheet, int fromRow, int toRow)
			throws RowsExceededException, WriteException {
		if (worksheet == null) {
			return;
		}
		int columns = worksheet.getColumns(); // 獲取工作表中的列表
		if (columns <= 0) {
			return;
		}
		for (int i = 0; i < columns; i++) {
			String value = worksheet.getCell(i, fromRow).getContents()
					.toString();
			Label label = new Label(i, toRow, value);
			worksheet.addCell(label);
		}
	}

	/** 
	 * @Title: getSheets 
	 * @Description: 从文件读取工作表
	 * @param file
	 * @return 
	 * @return Sheet[] 
	 * @throws 
	 * @author XiMing.Fu
	 */
	public Sheet[] getSheets(File file) {
		Workbook wb = null;
		try {
			wb = Workbook.getWorkbook(file);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet[] sheet = wb.getSheets();
		return sheet;
	}

}
