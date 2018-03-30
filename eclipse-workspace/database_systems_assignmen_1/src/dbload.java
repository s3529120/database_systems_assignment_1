

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class dbload {

	public static void main(String[] args) {
		//Initialize
				ArrayList<Business_Name> list;
				ArrayList<Page> pages;
				Long start,end;
				
				//Set start timer
				start=System.currentTimeMillis();
				//Read CSV into program
				list=readCSV(args[0]);
				//Set end timer calculate and display time taken
				end=System.currentTimeMillis();
				System.out.println("Load CSV time"+(end-start));
				
				//Set start timer
				start=System.currentTimeMillis();
				//Generate pages with records
				pages=generatePages(list,Integer.parseInt(args[1]));
				//Set end timer calculate and display time taken
				end=System.currentTimeMillis();
				System.out.println("Page generation:"+(end-start));
				
				//Set start timer
				start=System.currentTimeMillis();
				//Write pages to heapfile
				heapfile.writeHeapfile(Integer.parseInt(args[1]), pages);
				//Set end timer calculate and display time taken
				end=System.currentTimeMillis();
				System.out.println("Page generation:"+(end-start));
				
			}

			
			public static ArrayList<Business_Name> readCSV(String filename){
				String line="";
				String split="\\t";
				ArrayList<Business_Name> list = new ArrayList<Business_Name>();
				String bus_name,status;
				Date reg_date;
				Date cancel_date;
				Date ren_date;
				String form_state_num,pre_state_reg,abn;
				
				//Read in CSV file
				try (BufferedReader br = new BufferedReader(new FileReader(filename))){
					//Read in each line
					while((line=br.readLine())!=null) {
						//Set null values for potentially unreachable values
						pre_state_reg=null;
						abn=null;
						form_state_num=null;
						
						//Split line on delim
						String[] hold = line.split(split);
						
						//Set values known to be p[resent
						bus_name=hold[1];
						status=hold[2];
						
						//Determine if record is long enough to contain values and set if so
						if(hold.length>6) {
							form_state_num=hold[6];
							if(hold.length>7) {
								pre_state_reg=hold[7];
								if(hold.length>8) {
									abn=hold[8];
								}
							}
						}
						
						//Null dates in no entry else convert string to date value
						//Registration date
						if(hold[3].isEmpty()) {
							reg_date=null;
						}else {
							reg_date=new SimpleDateFormat("dd/MM/yyyy").parse(hold[3]);
						}
						//Cancellation date
						if(hold[4].isEmpty()) {
							cancel_date=null;
						}else {
							cancel_date=new SimpleDateFormat("dd/MM/yyyy").parse(hold[4]);
						}
						//Renewal date
						if(hold[5].isEmpty()) {
							ren_date=null;
						}else {
							ren_date=new SimpleDateFormat("dd/MM/yyyy").parse(hold[5]);
						}
						
						//Add Business_name object to list read in
						list.add(new Business_Name(bus_name,status,reg_date,cancel_date,
								ren_date,form_state_num,pre_state_reg,abn));
					}
				}catch(Exception e) {
					System.err.println("CSV file read failed");;
				}
				//Return list of Business names
				return list;
			}
			
			public static ArrayList<Page> generatePages(ArrayList<Business_Name> namelist,int pageSize){
				ArrayList<Page> pages=new ArrayList<Page>();
				
				Page currentPage=new Page(pageSize);
				int i=0;
				for(Business_Name rec: namelist){
					if(!currentPage.spaceAvailable(rec)){
						pages.add(currentPage);
						currentPage=new Page(pageSize);
					}
					currentPage.insertRecord(rec);
				}
				pages.add(currentPage);
				return pages;
			}

	}

