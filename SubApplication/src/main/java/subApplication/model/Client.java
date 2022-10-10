package subApplication.model;

public class Client {

	//Fields
	private String firstName; 		   //First name of the Client
	private String lastName;  		   //Last name of the Client
	private String fatherFirstName;    //Clients Father first name
	private String address;			   //Address
	private String kindOfSubscription; //either student Sub either normal Sub
	private String kindOfExercise;     //kick-box/mai-thai/box/all/some of 'em
	private int    zipCode;			   //TK
	private long   phoneNumber;		   //Phone number
	private String birthDate;
	private String created_at;
	private int daysToExpire;
	private int payments;
	private double debt;
	private String debtWithCurrency;
	private final String currency = "\u20ac"; // euro
	
	
	
	
	//Constructor
	public Client() {
		this.firstName = "";
		this.lastName = "";
		this.fatherFirstName = "";
		this.address = "";
		this.kindOfSubscription = "";
		this.kindOfExercise = "";
		this.zipCode = 0;
		this.phoneNumber = 0;
		this.birthDate = "";
		this.created_at = "";
		
	}

	
	//Constructor
	public Client(String firstName, String lastName, String fatherFirstName, String address, int zipCode,
			String kindOfSubscription, String kindOfExercise, long phoneNumber, String birthDate,  String created_at, int payment) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.fatherFirstName = fatherFirstName;
		this.address = address;
		this.kindOfSubscription = kindOfSubscription;
		this.kindOfExercise = kindOfExercise;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.created_at = created_at;
		this.payments = payment;

	}


	//Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherFirstName() {
		return fatherFirstName;
	}

	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getKindOfSubscription() {
		return kindOfSubscription;
	}

	public void setKindOfSubscription(String kindOfSubscription) {
		this.kindOfSubscription = kindOfSubscription;
	}

	public String getKindOfExercise() {
		return kindOfExercise;
	}

	public void setKindOfExercise(String kindOfExercise) {
		this.kindOfExercise = kindOfExercise;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	public int getDaysToExpire() {
		return daysToExpire;
	}


	public void setDaysToExpire(int daysToExpire) {
		this.daysToExpire = daysToExpire;
	}


	public int getPayments() {
		return payments;
	}


	public void setPayments(int payments) {
		this.payments = payments;
	}


	public double getDebt() {
		return debt;
	}


	public void setDebt(double debt) {
		this.debt = debt;
	}
	
	public String getDebtWithCurrency() {
		return debt+currency;
	}
	
	
	
	
	

	
		
}
