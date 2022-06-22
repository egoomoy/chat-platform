package aehdb.mng.legacy.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import aehdb.chat.room.model.entity.Room;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Legacy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String lgcyCd;
	@Column
	private String lgcyNm;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "legacy")
	private List<Room> room;
}
