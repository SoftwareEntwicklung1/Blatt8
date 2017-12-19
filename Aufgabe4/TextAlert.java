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