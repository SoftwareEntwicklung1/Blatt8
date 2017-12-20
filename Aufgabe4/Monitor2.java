import java.util.ArrayList;

interface Triggers {
	boolean checktrigger(ArrayList<Dataset> data);
}

interface Alert{
     void printalert(Dataset data);
}

public class Monitor {
	ArrayList<Dataset> data;
	ArrayList<Triggers> triggers;
	ArrayList<Alert> alerts;

	Monitor() {
		
		this.triggers = new ArrayList<Triggers>();
		this.alerts = new ArrayList<Alert>();
        this.data = new ArrayList<Dataset>();

	}

	void report(Dataset a) {
		data.add(a);
		Triggers temptrigger;
		for (int i = 0; i < triggers.size(); i++) {
			temptrigger = triggers.get(i);
			if (temptrigger.checktrigger(data)) {
				for (Alert E : alerts) {
					E.printalert(a);
				}
			}

		}
	}

	public void addTrigger(Triggers trigger) {
		triggers.add(trigger);
	}

	public void addAlert(Alert alert) {
		alerts.add(alert);

	}

}

class Dataset {
    private long t;
    private double wert;
    Dataset(long t,double wert){
     this.t=t;
     this.wert=wert;
    }
    public long getZeit(){
        return this.t;
    }
    public double getWert(){
        return this.wert;
    }
    public void setZeit(long t){
        this.t=t;
    }
    public void setWert(double w){
        this.wert=w;
    }
}

class DeltaTrigger implements Triggers {
	long time;
	double value;
	double change;

	DeltaTrigger(long x, double y) {
		this.time = x;
		this.change = y;
	}

	public void settime(long time) {
		this.time = time;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getChange() {
		return change;
	}

	public boolean checktrigger(ArrayList<Dataset> data) {
        int siz = data.size();
        Dataset dat = data.get(siz-1);
        for (int i = 1; i < siz; i++) {
            Dataset dat2 = data.get(siz-i-1);
            if(dat.getZeit() - dat2.getZeit() > this.time) {
                break;
            }
            if( Math.abs(dat.getWert() - dat2.getWert()) > this.change) {
                return true;
            }
            
        }

        return false;
	}
}

class AboveTrigger implements Triggers {
	private double max;

	AboveTrigger(double x) {
		this.max = x;
	}

	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(double max) {
		this.max = max;
	}

	public boolean checktrigger(ArrayList<Dataset> data) {

		if (data.get(data.size()-1).getWert() > max) {
			return true;
		}
		return false;
	}

}

class EmailAlert implements Alert{
    String Email;
    String m;
    EmailAlert(String x,String y){
       this.Email=x;
       this.m=y;
    }
	
	public void printalert(Dataset data) {
		System.out.println(Email);
		System.out.println(m);
		
	}
} 

class TextAlert implements Alert{
    String text;
    TextAlert(String x){
        this.text=x;
    }
	
	public void printalert(Dataset data) {
		text=text.replaceFirst("%t", "");
		text=text.replaceFirst("%v", "");
		System.out.println(data.getZeit()+text+data.getWert());

	}
  }