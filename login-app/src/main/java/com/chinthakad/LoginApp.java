package com.chinthakad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO: Write down notes here.
 *
 * @see org.springframework.session.data.redis.RedisIndexedSessionRepository
 * @see org.springframework.session.data.redis.RedisSessionExpirationPolicy
 * @see org.springframework.session.web.http.SessionEventHttpSessionListenerAdapter
 * @see org.springframework.security.web.session.HttpSessionEventPublisher
 * @see org.springframework.session.ReactiveSessionRepository
 * @see org.springframework.session.data.redis.ReactiveRedisSessionRepository
 */
@SpringBootApplication
public class LoginApp {

    public static void main(String[] args) {
        SpringApplication.run(LoginApp.class, args);
    }
}
