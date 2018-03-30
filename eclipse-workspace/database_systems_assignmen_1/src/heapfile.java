import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class heapfile {
	public static void writeHeapfile(int pageSize,ArrayList<Page> list){
		DataOutputStream os = null;
		File heapfile = new File("heapfile."+pageSize+".dat");
		try {
			heapfile.createNewFile();
		} catch (IOException e1) {}
		
		try {
			os = new DataOutputStream(new FileOutputStream("heapfile."+pageSize+".dat",false));
		} catch (FileNotFoundException e) {}
		
		for(Page p: list){
			try {
				os.write(p.pageToWrite().getBytes());
			} catch (IOException e) {
				System.out.println("Write failed.");
			}
		}
		try {
			os.close();
		} catch (IOException e) {
			System.out.println("Failed to close heapfile.");
		}
	}
}
