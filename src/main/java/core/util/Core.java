package core.util;

public class Core {
	private boolean successful;
	private String message;
	private String cpassword;
	private String strdateofbirth;
	
	public String getStrdateofbirth() {
		return strdateofbirth;
	}

	public void setStrdateofbirth(String strdateofbirth) {
		this.strdateofbirth = strdateofbirth;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public boolean isSuccessful() {
		return successful;
	}
	
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
