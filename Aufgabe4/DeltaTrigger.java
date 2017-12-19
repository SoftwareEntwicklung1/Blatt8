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

	public boolean checktrigger(Dataset new1) {
		if (new1.getZeit() == this.time) {
			this.value = new1.getWert();
		} else if (this.value - new1.getWert() > getChange()) {
			return true;
		}
		return false;
	}
}
