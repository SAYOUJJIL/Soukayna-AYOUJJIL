package ma.enset.jpaenset;

import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaEnsetApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaEnsetApplication.class, args);
	}
	@Bean
	CommandLineRunner start(IUserService iUserService){
		return args -> {

			User user = new User();
			user.setUserName("user1");
			user.setPassword("user1");
			iUserService.addNewUser(user);

			User user1 = new User();
			user1.setUserName("admin");
			user1.setPassword("admin");
			iUserService.addNewUser(user1);

			Stream.of("STUDENT","USER","ADMIN").forEach(
					r->{
						Role role =new Role();
						role.setRoleName(r);
						role.setDescri("my role is : "+r);
						iUserService.addNewRole(role);
					}
			);

			iUserService.addRoleToUser("user1","STUDENT");
			iUserService.addRoleToUser("user1","USER");
			iUserService.addRoleToUser("admin","USER");
			iUserService.addRoleToUser("admin","ADMIN");

			try {
				User user3 = iUserService.authentificate("user1","user1");
				System.out.println(user.getUserId());
				System.out.println(user.getUserName());
				System.out.println("Liste des roles : ");
				user3.getRoles().forEach(
						r->{
							System.out.println("Roles=>"+r);
						}
				);
			}catch (Exception e){
				e.getStackTrace();
			}
		};
	}
}
