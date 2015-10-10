package com.hankkin.WeiXinSelectImgsDemo.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
	
	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/Photo_LJ/";

	public static void saveBitmap(Bitmap bm, String picName) {
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG"); 
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}
	
	public static void delFile(String fileName){
		File file = new File(SDPATH + fileName);
		if(file.isFile()){
			file.delete();
        }
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;
		
		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); 
			else if (file.isDirectory())
				deleteDir(); 
		}
		dir.delete();
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	/**
	 * 初始化图片路径
	 *
	 * @return
	 */
	public static String iniFilePath(Activity act) {
		String filepath = null;
		String path = null;
		File fileSD = null;

		// 准备存储位置
		boolean sdExist = Environment.getExternalStorageState()
				.equals(Environment.MEDIA_MOUNTED);
		if (!sdExist) {
			Toast.makeText(act,"没有找到SD存储卡",Toast.LENGTH_SHORT).show();
			return null;

		} else {
			//TODO 内容提示完善
			path = Environment.getExternalStorageDirectory().getPath() + "/yourneed/Camera";
			fileSD = new File(path);
			if (fileSD.exists()) {
				filepath = path + "/" + System.currentTimeMillis() + ".jpg";
			} else {
				fileSD.mkdir();
				filepath = fileSD.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
			}
			return filepath;
		}
	}
}
