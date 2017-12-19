
class AboveTrigger implements Triggers {
	private double Max;

	AboveTrigger(double x) {
		this.Max = x;
	}

	/**
	 * @return the max
	 */
	public double getMax() {
		return Max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(double max) {
		this.Max = max;
	}

	public boolean checktrigger(Dataset new1) {

		if (new1.getWert() > Max) {
			return true;
		}
		return false;
	}

}