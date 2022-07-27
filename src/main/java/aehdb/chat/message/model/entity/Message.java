package aehdb.chat.message.model.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import aehdb.chat.message.model.dto.MessageDto.MESSAGETYPE;
import aehdb.chat.room.model.entity.Room;
import aehdb.comm.model.entity.BaseEntity;
import aehdb.mng.legacy.model.entity.Legacy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Message extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "UUID")
	private UUID roomUuid;

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
	
}
