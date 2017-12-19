import java.util.ArrayList;

public class Monitor {
	Dataset data;
	ArrayList<Triggers> Triggers;
	ArrayList<Alert> alerts;

	Monitor() {
		this.data = null;
		this.Triggers = new ArrayList<Triggers>();
		this.alerts = new ArrayList<Alert>();

	}

	void report(Dataset a) {
		data = a;
		Triggers temptrigger;
		for (int i = 0; i < Triggers.size(); i++) {
			temptrigger = Triggers.get(i);
			if (temptrigger.checktrigger(a)) {
				for (Alert E : alerts) {
					E.printalert(a);
				}
			}

		}
	}

	public void addTrigger(Triggers Trigger) {
		Triggers.add(Trigger);
	}

	public void addAlert(Alert alert) {
		alerts.add(alert);

	}

}
