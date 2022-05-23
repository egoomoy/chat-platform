//package aehdb;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import aehdb.mng.user.model.entity.User;
//import aehdb.mng.user.model.repository.UserRepository;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//public class TestUserRepository {
//
//	@Autowired
//	UserRepository repository;
//
//	@Test
//	public void addUser() {
//
//		User user = new User();
//		user.setAccntId("egoomoy");
//		user.setOrgnId(1);
//		user.setPassword("password");
//
//		repository.save(user);
//	}
//
////	@Test
////	public void 유저역리스트확인용() throws Exception {
////		CustomUserDetails cud = userService.loadUserByAccntid("aaa");
////	}
//
//}
