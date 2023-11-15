package com.tistory.ospace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {
	@Autowired
	private UserRepository userRepo;
	
	public Mono<ServerResponse> getUserById(ServerRequest req) {
		String id = req.pathVariable("userId");
		return userRepo
				.findById(id)
				.flatMap(user->
					ServerResponse
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just(user), User.class)
				)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> getAllUsers(ServerRequest req) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(userRepo.findAll(), User.class);
	}
	
	public Mono<ServerResponse> saveUser(ServerRequest req) {
		Mono<User> user = req.bodyToMono(User.class);
		return user
				.single()
				.flatMap(u->{
					return ServerResponse
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(userRepo.save(u), User.class);
				});
	}
	
	public Mono<ServerResponse> updateUser(ServerRequest req) {
		String id = req.pathVariable("userId");
		Mono<User> user = req.bodyToMono(User.class);
		return user.flatMap(u->{
			if (null != id && !id.isEmpty()) u.setId(id);
			return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(userRepo.save(u), User.class);
		});
	}
	
	public Mono<ServerResponse> deleteUser(ServerRequest req) {
		String id = req.pathVariable("userId");
		userRepo.delete(id);
		return ServerResponse
				.ok()
				.build();
	}
}
