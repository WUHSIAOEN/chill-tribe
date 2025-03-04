package core.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class District {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DISTRICT_ID")
	private Integer districtId;
	
	@Column(name = "CITY_ID")
	private Integer cityId;
	
	@Column(name = "DISTRICT_NAME")
	private String districName;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	
}
