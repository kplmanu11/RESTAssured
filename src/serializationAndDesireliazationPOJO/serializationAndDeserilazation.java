package serializationAndDesireliazationPOJO;

public class serializationAndDeserilazation {
	
	//serialization//converting the pojo class to the json payload
	//setters are important for the serialization
	//getters are important for the deserialization
	//deserialization// converting the json response to the class object
	//for deseriliazation 4 jars need to be added jackon databind  and gson
	private String message;
	private String greet;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		 this.message = message;
	}
	
	public String getGreet() {
		return greet;
		
	}
	public void setGreet(String greet) {
		this.greet = greet;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		serializationAndDeserilazation obj = new serializationAndDeserilazation();
		obj.setGreet("Hello");
		obj.setMessage("Kapil");
		
		System.out.println(obj.getGreet());
		System.out.println(obj.getMessage());
		obj.setGreet("Hello1");
		obj.setMessage("Kapil11");
		System.out.println(obj.getGreet());
		System.out.println(obj.getMessage());
	}
	

}
