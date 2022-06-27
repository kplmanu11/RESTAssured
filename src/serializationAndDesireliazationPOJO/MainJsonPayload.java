package serializationAndDesireliazationPOJO;

//Parent JSON
public class MainJsonPayload {
	private String uRL;
	private String services;
	private String expertise;
	private Courses course;
	private String instructor;
	private String linkedin;
	
	
	public String getURL() {
		return uRL;
	}
	public void setURL(String uRL) {
		this.uRL = uRL;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Courses getCourse() {
		return course;
	}
	public void setCourse(Courses course) {
		this.course = course;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	

}
