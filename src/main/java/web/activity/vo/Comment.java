package web.activity.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import core.util.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.member.vo.Member;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment extends Core{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_ID")
	private Integer commentId;
	
	@Column(name = "MEMBER_ID")
	private Integer memberId;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
	private Member member;
	
	@Column(name = "ACTIVITY_ID")
	private Integer activityId;
	
	private String content;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "COMMENT_TIME")
	private Timestamp commentTime;
	
	@Column(name = "star_rating")
	private Integer starRating;
	
}
