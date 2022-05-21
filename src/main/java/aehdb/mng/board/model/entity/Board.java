package aehdb.mng.board.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import aehdb.comm.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@DynamicUpdate
@Accessors(chain = true)
public class Board extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Length(min=1, max=100, message="errors.range")
	@Column(length = 100, nullable=false)
	private String title;
}
