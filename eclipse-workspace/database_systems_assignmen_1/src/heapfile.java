import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class heapfile {
	public static void writeHeapfile(String file,int pageSize,ArrayList<Business_Name> list){
		DataOutputStream os = null;
		File heapfile = new File("heapfile."+pageSize+".dat");
		try {
			heapfile.createNewFile();
		} catch (IOException e1) {}
		
		try {
			os = new DataOutputStream(new FileOutputStream("heapfile."+pageSize+".dat",false));
		} catch (FileNotFoundException e) {}
		
		int page=0,x;
		String[] entry;
		for (Business_Name name: list){
			entry=new String[7];
			if(name.getReg_date()==null){
				entry[2]="";
			}else{
				entry[2]=name.getReg_date().toString();
			}
			if(name.getCancel_date()==null){
				entry[3]="";
			}else{
				entry[3]=name.getCancel_date().toString();
			}
			if(name.getRen_date()==null){
				entry[4]="";
			}else{
				entry[4]=name.getRen_date().toString();
			}
			if(name.getForm_state_num()==null){
				entry[5]="";
			}else{
				entry[5]=name.getForm_state_num();
			}
			if(name.getPre_state_reg()==null){
				entry[6]="";
			}else{
				entry[6]=name.getPre_state_reg();
			}
			if(name.getAbn()==null){
				entry[7]="";
			}else{
				entry[7]=name.getAbn();
			}
			
			//Determine if record will fit in page
			x=0;
			for(int i=0;i<entry.length;i++){
				x=entry[i].getBytes().length;
			}
			//If page will not fit mark end of page and start new
			if((x+page)>pageSize){
				try {
					//Indicate end of record
					os.write(">".getBytes());
				} catch (IOException e) {}
				page=0;
			}

			//Write record to heapfile
			for(int i=0;i<entry.length;i++){
				try {
					os.write(entry[i].getBytes());
					//Indicate end of value
					os.write("<".getBytes());
				} catch (IOException e) {}
			}
		}
	}
}
