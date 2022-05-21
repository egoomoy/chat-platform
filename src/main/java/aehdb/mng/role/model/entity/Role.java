package aehdb.mng.role.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import aehdb.mng.user.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String roleNm;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USERS_ROLE",
	joinColumns = @JoinColumn(name = "USER_ID"),
	inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<User> users;
	
}