import java.util.ArrayList;

public class Page {
	private int pageNumber;
	private int size;
	private int spaceUsed;
	private ArrayList<Business_Name> records;
	
	public Page(int s){
		size=s;
		records=new ArrayList<Business_Name>();
		spaceUsed=0;
	}
	
	public String pageToWrite(){
		String ret="";
		for(Business_Name rec: records){
			ret.concat(rec.recordToWrite());
			ret.concat("\n");
		}
		return ret;
	}
	
	public boolean insertRecord(Business_Name rec){
		if(spaceAvailable(rec)){
			records.add(rec);
			spaceUsed+=rec.getBytesLength();
			return true;
		}else{
			return false;
		}
	}
	
	public boolean spaceAvailable(Business_Name rec){
		if((rec.getBytesLength()+spaceUsed)>size){
			return false;
		}
		return true;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public ArrayList<Business_Name> getRecords() {
		return records;
	}
	
}
