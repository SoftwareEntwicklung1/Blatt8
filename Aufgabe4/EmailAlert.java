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