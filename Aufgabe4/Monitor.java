interface Datesatz{
    long getZeit();
    double getWert();
}
class Dataset implements Datesatz{
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
public class Monitor {
    Dataset data;
    AboveTrigger Atrigger;
    DeltaTrigger Dtrigger;
    Alert alert;
    int k=0;
    Monitor(){}
    Monitor(long t,double wert){
     this.data=new Dataset(t, wert);
    }
    public long getZeit(){
        return data.getZeit();
    }
    public double getWert(){
        return data.getWert();
    }
    void addTrigger(AboveTrigger a){
        this.Atrigger.setMax(a.getMax());
    }
    void addTrigger(DeltaTrigger a){
        this.Dtrigger.setZeit(a.getZeit());
        this.Dtrigger.setChange(a.getChange());
    }
    void addAlert(TextAlert a){
        this.alert.a=a;
    }
    void addAlert(EmailAlert b){
        this.alert.b=b;
        k=-1;
    }
    void report(Dataset a){
     int px=0;   
     data.setZeit(a.getZeit());
     data.setWert(a.getWert());
     if(data.getWert()>this.Atrigger.getMax()){
         alert.Aprint(data.getZeit(),data.getWert());
     }
     if(Math.abs(data.getWert()-Dtrigger.letztchange)>=Dtrigger.change){
        alert.Aprint(data.getZeit(),data.getWert());
     }
     if(data.getZeit()%Dtrigger.Zeit==0){
            Dtrigger.letzttime=data.getZeit();
            Dtrigger.letztchange=data.getWert();
        }  
     if(k==-1){
         alert.Bprint();
         k=0;
     }

     }

} 
class Alert{
    TextAlert a;
    EmailAlert b;
    String  textchange(String t,String v){
        int p=a.text.indexOf("%t");
        int k=a.text.indexOf("%v");
        String x=a.text.substring(p+1, k);
        String newx=t.concat(x).concat(v);
        return newx;
      }
    void  Aprint(long x,double y){
        String t=""+x;
        String v=""+y;  
        String s=textchange(t, v);  
        System.out.println(s);
      }
    void  Bprint(){
        System.out.println(this.b.Email);
        System.out.println(this.b.m);  
      }
 
}
class AboveTrigger{
    private double Max;
    AboveTrigger(double x){
    this.Max=x;
   }
   /**
    * @return the max
    */
   public double getMax() {
       return Max;
   }
   /**
    * @param max the max to set
    */
   public void setMax(double max) {
       this.Max = max;
   }
    
}
class DeltaTrigger{
    long Zeit;
    double change;
    long letzttime;
    double letztchange;
     DeltaTrigger(long x,double y){
          this.Zeit=x;
          this.change=y;
     }
     /**
      * @param zeit the zeit to set
      */
     public void setZeit(long zeit) {
         this.Zeit = zeit;
     }
     /**
      * @param change the change to set
      */
     public void setChange(double change) {
         this.change = change;
     }
     /**
      * @return the zeit
      */
     public long getZeit() {
         return Zeit;
     }
     /**
      * @return the change
      */
     public double getChange() {
         return change;
     }
}
class TextAlert{
    String text;
    TextAlert(String x){
        this.text=x;
    }
  }
class EmailAlert{
    String Email;
    String m;
    EmailAlert(String x,String y){
       this.Email=x;
       this.m=y;
    }
} 