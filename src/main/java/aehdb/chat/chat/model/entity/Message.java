package aehdb.chat.chat.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import aehdb.chat.chat.model.dto.MessageDto.MESSAGETYPE;
import aehdb.chat.room.model.entity.Room;
import aehdb.comm.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Message extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private MESSAGETYPE type;
	
	@Column(length = 50)
	@NotNull
	private String senderId;
	
	@Column(length = 50)
	@NotNull
	private String senderNm;

	@Column(length = 500)
	@NotNull
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "ROOM_ID")
	private Room room;
	
}
