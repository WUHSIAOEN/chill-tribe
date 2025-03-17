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
	
	private Integer address_id;
	private Integer city_id;
	private String city_name;
	private Integer district_id;
	private String district_name;
	private Integer zip_code;
	private String address;
	private Integer address_default;
	private String tag;
	
	
	
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
	
	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public Integer getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public Integer getZip_code() {
		return zip_code;
	}

	public void setZip_code(Integer zip_code) {
		this.zip_code = zip_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAddress_default() {
		return address_default;
	}

	public void setAddress_default(Integer address_default) {
		this.address_default = address_default;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
