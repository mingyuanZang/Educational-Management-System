package application.IOStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;


public class IOStream {

	public static void outputCollection(Collection<?> col, String fileName) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(col);
			oos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Collection<?> inputCollection(String fileName) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Collection<?> col = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			col = (Collection<?>) ois.readObject();
		} catch (FileNotFoundException e) {
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return col;
	}
	
//	public static void outputCollection(Object obj, String fileName) {
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try {
//			fos = new FileOutputStream(fileName);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(obj);
//			oos.flush();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (oos != null)
//					oos.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public static Object inputCollection(String fileName) {
//		FileInputStream fis = null;
//		ObjectInputStream ois = null;
//		Object obj = null;
//		try {
//			fis = new FileInputStream(fileName);
//			ois = new ObjectInputStream(fis);
//			obj = ois.readObject();
//		} catch (FileNotFoundException e) {
//			return null;
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ois != null)
//					ois.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return obj;
//	}
	
//	public final String STUDENTLIST = "studentlist.xml";
	
//	public void outputCollection(HashSet<?> set) {
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try {
//			fos = new FileOutputStream(STUDENTLIST);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(set);
//			oos.flush();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (oos != null)
//					oos.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public HashSet<?> inputCollection() {
//		FileInputStream fis = null;
//		ObjectInputStream ois = null;
//		HashSet<?> set = null;
//		try {
//			fis = new FileInputStream(STUDENTLIST);
//			ois = new ObjectInputStream(fis);
//			set = (HashSet<?>) ois.readObject();
//		} catch (FileNotFoundException e) {
//			return null;
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ois != null)
//					ois.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return set;
//	}

}
