package com.kamai.antiauction.repository.jdbc;

import com.kamai.antiauction.model.User;
import com.kamai.antiauction.repository.interfaces.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Log4j
@PropertySource("classpath:queries/user.properties")
@Repository
public class JdbcUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final Environment env;

    @Autowired
    public JdbcUserRepository(NamedParameterJdbcTemplate namedJdbcTemplate, Environment env) {
        System.out.println("Class initialized");

        this.namedJdbcTemplate = namedJdbcTemplate;
        this.env = env;
    }


    @Override
    public User save(User user) {

        System.out.println("Saving user: " + user);

        MapSqlParameterSource namedParams = new MapSqlParameterSource();

        namedParams
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("login", user.getLogin())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("birth", user.getBirth())
                .addValue("sex", user.getSex())
                .addValue("phone", user.getPhone())
                .addValue("regDate", user.getRegDate())
                .addValue("image", user.getImage())
                .addValue("notificationSettIsOn", user.getNotificationSettIsOn());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = namedJdbcTemplate.update(env.getProperty("saveUser"), namedParams, keyHolder, new String[] {"id"});

        log.info(update + " row updated in the users table");

        user.setId(
                Objects.requireNonNull(keyHolder.getKey()).longValue()
        );

        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }
}
