package net.java.sptringboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import net.java.sptringboot.model.User;
import net.java.sptringboot.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUSer() {
		User user = new User();
		user.setName("test");
		user.setEmail("test@test.as");
		user.setPassword("123");
		
		User savedUser = repo.save(user);
		User existUSer = entityManager.find(User.class, savedUser.getId());
		
		assertThat(existUSer.getEmail()).isEqualTo(user.getEmail());
	}
	@Test
	public void testFindUserByEmail() {
		String email = "test@test.as";
		User user = repo.findbyEmail(email);
		assertThat(user).isNotNull();
	}
}
