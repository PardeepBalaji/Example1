package com.example.jwt_demo1;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepo userRepository;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@PostMapping("/singUp")
	public ResponseEntity<Employee> singUp(@RequestBody Employee user) {
		
		Employee user1=userRepository.save(user);
		return ResponseEntity.ok(user1);
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Employee login) throws UserPrincipalNotFoundException{
Employee user=userRepository.findByNameAndPassword(login.getName(),login.getPassword());
		
		if(user==null)
		{
			throw new UserPrincipalNotFoundException("user name password is null");
		}
		
		String token = jwtUtils.generateJwt(user);
		

		return ResponseEntity.ok(token);
	}
	
	@GetMapping("/find")
	public ResponseEntity<Employee> login(@RequestParam int id , @RequestHeader String token){		
		Optional<Employee> user = userRepository.findById(id);
		
		if(jwtUtils.verify(token)) {
			Employee u = user.get();
			return 	ResponseEntity.ok(u);

		}else {
			return ResponseEntity.ok(null);

		}
	}
}
