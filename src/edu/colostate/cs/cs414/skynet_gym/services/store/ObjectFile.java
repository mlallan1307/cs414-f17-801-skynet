package edu.colostate.cs.cs414.skynet_gym.services.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Handles serialized object saving/loading (such as a class) to a file.
 * 
 * @author Mike Allan
 *
 */
public class ObjectFile {
	
	private static String fileDirectory = "";
	private final static String ext = ".ser";
	
	public static void saveAsFile(Object o, String name){
		try {
			FileOutputStream myFileOutputStream = new FileOutputStream(fileName(name));
			ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
			myObjectOutputStream.writeObject(o);
			myObjectOutputStream.close();
		}
		catch (Exception e) {
			throw new RuntimeException("Error when saving to file. " + e.toString());
		}
	}

	public static Object loadObjectFile(String name){
		try {
			FileInputStream myFileInputStream = new FileInputStream(fileName(name));
		    ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
		    Object o = (Object) myObjectInputStream.readObject(); 
		    myObjectInputStream.close();
		    return o;
		}
		catch (FileNotFoundException e) {
			// ignore
		}
		catch (Exception e) {
			System.out.println("Error when loading file." + e.toString()); 
		}
		return null;
	}
	
	public static Boolean removeFile(String name){
		File delete = new File(fileName(name));
		if (delete.exists()) {
			return delete.delete();
		}
		return false;
	}
	
	private static String fileName(String name) {
		return (fileDirectory + name + ext);
	}
}
