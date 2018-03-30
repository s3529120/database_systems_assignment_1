import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class heapfile {
	public static void writeHeapfile(int pageSize,ArrayList<Page> list){
		DataOutputStream os = null,ios=null;
		File heapfile = new File("heapfile."+pageSize+".dat");
		File indexfile = new File("indexfile."+pageSize+".dat");
		try {
			heapfile.createNewFile();
			indexfile.createNewFile();
		} catch (IOException e1) {}
		
		try {
			os = new DataOutputStream(new FileOutputStream("heapfile."+pageSize+".dat",false));
			ios = new DataOutputStream(new FileOutputStream("indexfile."+pageSize+".dat",false));
		} catch (FileNotFoundException e) {}
		
		
		int pageno=1,totalRecords=0;
		for(Page p: list){
			try {
				int recordno=1;
				for(Business_Name record: p.getRecords()){
					os.write(record.recordToWrite().getBytes());
					os.write("\n".getBytes());
					
					ios.writeInt(pageno);
					ios.write("-".getBytes());
					ios.writeInt(recordno);
					ios.write((","+record.getBus_name()+"\n").getBytes());
					recordno++;
					totalRecords++;
				}
				os.write("<\n".getBytes());
			} catch (IOException e) {
				System.out.println("Write failed.");
			}
			pageno++;
		}
		try {
			os.close();
		} catch (IOException e) {
			System.out.println("Failed to close heapfile.");
		}
		System.out.println(totalRecords+" records written accorss "+pageno+" pages.");
	}
}
