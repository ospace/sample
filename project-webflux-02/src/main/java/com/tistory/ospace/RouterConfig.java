package com.tistory.ospace;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

	@Bean
	RouterFunction<ServerResponse> routes(UserHandler handler) {
		return route(GET("/user").and(accept(MediaType.APPLICATION_JSON)), handler::getAllUsers)
			.andRoute(GET("/user/{userId}").and(accept(MediaType.APPLICATION_JSON)), handler::getUserById)
			.andRoute(POST("/user").and(accept(MediaType.APPLICATION_JSON)), handler::saveUser)
			.andRoute(PUT("/user/{userId}").and(accept(MediaType.APPLICATION_JSON)), handler::updateUser)
			.andRoute(DELETE("/user/{userId}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteUser);
	}
}
