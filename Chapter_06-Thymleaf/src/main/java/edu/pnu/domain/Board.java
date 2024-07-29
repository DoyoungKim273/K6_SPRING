package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	@Column(updatable = false)
	private String writer;
	private String content;
	@Builder.Default
	@Column(insertable = false, updatable = false, columnDefinition = "date default (curdate())")
	private Date createDate = new Date(); 
//	private Date createDate;
	@Builder.Default	
	@Column(insertable = false, updatable = false, columnDefinition = "bigint default 0")
	private Long cnt = 0L;
//	private Long cnt;

}
