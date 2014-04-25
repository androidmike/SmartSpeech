package com.changclamor.roomtosprout.smartspeech.data;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

public class StorageUtils {
	public static final File EXTERNAL_DIR = Environment
			.getExternalStorageDirectory();
	private static StorageUtils instance = new StorageUtils();

	public static StorageUtils getInstance() {
		return instance;
	}

	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	public static void unzip() {
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable() {

			@Override
			public void run() {

				String source = EXTERNAL_DIR + File.separator + "kids.zip";
				String destination = EXTERNAL_DIR + File.separator
						+ "smartspeech" + File.separator + "res";
				String password = "password";

				try {
					ZipFile zipFile = new ZipFile(source);
					if (zipFile.isEncrypted()) {
						zipFile.setPassword(password);
					}
					zipFile.extractAll(destination);
				} catch (ZipException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
