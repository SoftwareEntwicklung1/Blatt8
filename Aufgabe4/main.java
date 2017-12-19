

public class main {

	public static void main(String[] args){
		Monitor m = new Monitor();
		m.addTrigger(new AboveTrigger(39));
		m.addTrigger(new DeltaTrigger(4000, 1));
		m.addAlert(new TextAlert("%t ms: Value changed to %v"));
		m.report(new Dataset(0, 38.4));
		m.report(new Dataset(1000, 38.5));
		m.report(new Dataset(2000, 38.5));
		m.report(new Dataset(3000, 38.6));
		m.report(new Dataset(4000, 39.1)); // AboveTrigger(39) feuert
		m.report(new Dataset(5000, 38.9));
		m.report(new Dataset(6000, 38.5));
		m.report(new Dataset(7000, 38.0)); // DeltaTrigger(4000, 1) feuert
		m.addAlert(new EmailAlert("hans@example.com", "ALERT!"));
		m.report(new Dataset(8000, 38.0)); // DeltaTrigger feuert erneut
		m.report(new Dataset(9000, 38.0));
	}
}
