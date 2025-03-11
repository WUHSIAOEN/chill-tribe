package core.util;

public class Core {
	private boolean successful;
	private String message;
	private String cpassword;
	private String strdateofbirth;
	private String googleemail;
	private String access_token;
	private String googleid;
	private String googlename;
	
	
	
	public String getGoogleemail() {
		return googleemail;
	}

	public void setGoogleemail(String googleemail) {
		this.googleemail = googleemail;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getGoogleid() {
		return googleid;
	}

	public void setGoogleid(String googleid) {
		this.googleid = googleid;
	}

	public String getGooglename() {
		return googlename;
	}

	public void setGooglename(String googlename) {
		this.googlename = googlename;
	}

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
