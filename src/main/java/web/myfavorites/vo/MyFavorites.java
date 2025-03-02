package web.myfavorites.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import core.util.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.activity.vo.Activities;
import web.activity.vo.ActivityImage;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyFavorites extends Core{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MY_FAVORITE_ID")
	private Integer myFavoriteId;
	
	@Column(name = "ACTIVITY_ID")
	private Integer activity_id;
	
	@ManyToOne
	@JoinColumn(name = "ACTIVITY_ID", insertable = false, updatable = false)
	private Activities activity;
	
	@Column(name = "MEMBER_ID")
	private Integer memberId;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "ADDED_TIME")
	private Timestamp addedTime;

}
