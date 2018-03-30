

import java.util.Date;

public class Business_Name {
	private String bus_name;
	private String status;
	private Date reg_date;
	private Date cancel_date;
	private Date ren_date;
	private String form_state_num;
	private String pre_state_reg;
	private String abn;
	
	public Business_Name(String bus_name, String status, Date reg_date, Date cancel_date, Date ren_date, String form_state_num,
			String pre_state_reg, String abn) {
		this.setBus_name(bus_name);
		this.setStatus(status);
		this.setReg_date(reg_date);
		this.setCancel_date(cancel_date);
		this.setRen_date(ren_date);
		this.setForm_state_num(form_state_num);
		this.setPre_state_reg(pre_state_reg);
		this.setAbn(abn);
	}
	
	public String recordToWrite(){
		String ret="";
		String[] att = entryAsStrings();
		for(int i=0;i<att.length;i++){
			ret.concat(att[i]);
			ret.concat(">");
		}
		return ret;
	}
	
	public String[] entryAsStrings(){
		String[] attributes = new String[8];
		if(bus_name==null){
			attributes[0]="";
		}else{
			attributes[0]=bus_name;
		}

		if(status==null){
			attributes[1]="";
		}else{
			attributes[1]=status;
		}

		if(reg_date==null){
			attributes[2]="";
		}else{
			attributes[2]=reg_date.toString();
		}

		if(cancel_date==null){
			attributes[3]="";
		}else{
			attributes[3]=cancel_date.toString();
		}

		if(ren_date==null){
			attributes[4]="";
		}else{
			attributes[4]=ren_date.toString();
		}

		if(form_state_num==null){
			attributes[5]="";
		}else{
			attributes[5]=form_state_num;
		}

		if(pre_state_reg==null){
			attributes[6]="";
		}else{
			attributes[6]=pre_state_reg;
		}

		if(abn==null){
			attributes[7]="";
		}else{
			attributes[7]=abn;
		}
		
		return attributes;
	}
	
	public int getBytesLength(){
		int count=0;
		String[] att=entryAsStrings();
		for(int i=0;i<att.length;i++){
			count+=att[i].getBytes().length;
		}
		return count;
	}

	public String getBus_name() {
		return bus_name;
	}

	public void setBus_name(String bus_name) {
		this.bus_name = bus_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getCancel_date() {
		return cancel_date;
	}

	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
	}

	public Date getRen_date() {
		return ren_date;
	}

	public void setRen_date(Date ren_date) {
		this.ren_date = ren_date;
	}

	public String getForm_state_num() {
		return form_state_num;
	}

	public void setForm_state_num(String form_state_num) {
		this.form_state_num = form_state_num;
	}

	public String getPre_state_reg() {
		return pre_state_reg;
	}

	public void setPre_state_reg(String pre_state_reg) {
		this.pre_state_reg = pre_state_reg;
	}

	public String getAbn() {
		return abn;
	}

	public void setAbn(String abn) {
		this.abn = abn;
	}
	
}
