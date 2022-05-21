package aehdb.mng.user.model.entity;

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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicUpdate;

import aehdb.comm.model.entity.BaseEntity;
import aehdb.mng.role.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name="`users`") // "user" is a reserved word in postgresql.
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String accntId;

	@Column(length = 60, nullable = false)
	@NotEmpty
	private String password;

	@Column
	private int orgnId;
	
	@Column
	private String userNm;
	  
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_ROLE",
              joinColumns = @JoinColumn(name = "USER_ID"),
              inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
		//    indexes = {
		//            @Index(name = "idx_car_driver_car_id", columnList = "car_id"),
		//            @Index(name = "idx_car_driver_driver_id", columnList = "driver_id")
		//    }
	)
    private Set<Role> role;
    
	// 비밀번호 오류 잠금, del_yn 등 추가 컬럼 
    // 서비스 - 유저 m to m 검토 =>지원 가능한 레거시 서비스 리스트 보관용? one to one으로 개발해도 되긴할 듯?
}
