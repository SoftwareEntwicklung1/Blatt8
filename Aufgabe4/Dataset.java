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