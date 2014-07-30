package cn.jxl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

public class ImportCA {

	// 当上传文件超过限制时设定的临时文件位置
	private String tempPath = "C:\\TEMP";

	// 获取的上传请求
	private HttpServletRequest fileuploadRequest = null;

	// 设置最多只允许在内存中存储的数据,单位:字节
	private int sizeThreshold = 4096;

	// 设置允许用户上传文件大小,单位:字节(共10M)
	private long sizeMax = 10485760;

	private InputStream is = null;

	private StringBuffer sb = new StringBuffer();

	public ImportCA() {
	}

	public ImportCA(String tempPath) {
		this.tempPath = tempPath;
	}

	public ImportCA(HttpServletRequest fileuploadRequest) {
		this.fileuploadRequest = fileuploadRequest;
	}

	public ImportCA(String tempPath, HttpServletRequest fileuploadRequest) {
		this.tempPath = tempPath;
		this.fileuploadRequest = fileuploadRequest;
	}

	/**
	 * @Title: getFile
	 * @Description: 获取文件
	 * @return
	 * @throws Exception
	 * @return boolean
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	public String getFile() throws Exception {
		// 如果没有临时目录，则创建它
		if (!(new File(tempPath).isDirectory())) {
			try {
				new File(tempPath).mkdirs();
			} catch (SecurityException e) {
				System.out.println("can not make security direcoty");
			}
		}

		DiskFileUpload fu = new DiskFileUpload();

		// 设置最多只允许在内存中存储的数据,单位:字节,超出缓存到临时文件
		fu.setSizeThreshold(sizeThreshold);

		// 设置允许用户上传文件大小,单位:字节（10M）
		fu.setSizeMax(sizeMax);

		// 设置一旦文件大小超过sizeThreshold的值时数据存放在硬盘的目录
		fu.setRepositoryPath(tempPath);

		Iterator iter = null;
		// 读取上传信息
		try {
			List fileItems = fu.parseRequest(fileuploadRequest);
			// 处理每个上传的文件
			iter = fileItems.iterator();
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			// 忽略其他不是文件域的所有表单信息
			if (!item.isFormField()) {
				// 上传文件信息
				String name = item.getName();
				if ((name == null) || name.equals("") && item.getSize() == 0) {
					continue;
				}
				try {
					is = item.getInputStream();
					item.delete();
					// 导入数据
					return doImport();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (is != null) {
							is.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "导入失败";
	}

	/**
	 * @Title: doImport
	 * @Description: 导入数据
	 * @return
	 * @return boolean
	 * @throws
	 */
	public String doImport() {
		try {
			// workbook（excel)
			Workbook wb = Workbook.getWorkbook(is);
			if (wb == null)
				return "导入文件异常，请检查文件数据";
			// sheet（工作表）
			Sheet[] sheet = wb.getSheets();
			// 处理数据
			if (sheet != null && sheet.length > 0) {
				for (int i = 0; i < sheet.length; i++) {
					// 登陆名索引,证书编号索引
					int m = 0, n = 0;
					// 取第一行
					Cell[] fristcells = sheet[i].getRow(0);
					for (int j = 0; j < fristcells.length; j++) {
						if ("登录名".equals(fristcells[j].getContents().trim())) {
							m = j;
						} else if ("证书编号".equals(fristcells[j].getContents()
								.trim())) {
							n = j;
						}
					}
					if (m == 0 || n == 0) {
						sb.append("第一行必须存在<登录名>和<证书编号>两列，请仔细检查");
					}
					// 行
					int lenght = sheet[i].getRows();
					if (lenght > 0) {
						int o = 0, p = 0, q = 0;
						String flag = "";
						for (int j = 1; j < lenght; j++) {
							Cell[] cells = sheet[i].getRow(j);
							for (int k = 0; k < cells.length; k++) {
								System.out.print(cells[k].getContents().trim()
										+ "--");
							}
							System.out.println();
						}
						return sb.toString();
					}
				}
			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "导入失败";
	}

}
