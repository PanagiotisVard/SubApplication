package subApplication.model;

public class Subscription {
	private String kindOfSubscription;
	private int visible;
	
	
	
	public Subscription(String kindOfSubscription, int visible) {
		super();
		this.kindOfSubscription = kindOfSubscription;
		this.visible = visible;
	}
	public String getKindOfSubscription() {
		return kindOfSubscription;
	}
	public void setKindOfSubscription(String kindOfSubscription) {
		this.kindOfSubscription = kindOfSubscription;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	
	

}
