package com.tistory.ospace;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepository {
	@SuppressWarnings("serial")
	private Map<String, User> users = new HashMap<>() {{
		put("1", new User("1", "foo1"));
		put("2", new User("2", "foo2"));
	}};
	
	private Integer id = 10;
	
    public Mono<User> findById(String id) {
    	return Mono.just(users.get(id));
    }

	public Flux<User> findAll() {
		return Flux.fromIterable(users.values());
	}

	public Mono<User> save(User user) {
		User res = null;
		if (null == user.getId()) {
			user.setId((++id).toString());
			users.put(user.getId(), user);
			res = user;
		} else {
			res = users.get(user.getId());
			if (null != res) {
				res.setName(user.getName());
			}
		}
		
		return Mono.just(res);
	}
	
	public void delete(String id) {

		users.remove(id);
	}
}
