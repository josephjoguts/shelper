package ru.aaa.shelper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.*;
import ru.aaa.shelper.entity.Grant;
import ru.aaa.shelper.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grants")
public class GrantController {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @GetMapping("/{username}/filter")
    public List<Grant> findGrantsForUser(@PathVariable String username) {
        String sql = "SELECT g.id, g.name, g.type, g.amount, g.region, g.scale  " +
                "                FROM Grants g  " +
                "                WHERE " +
                "                   (:event_types IS NULL OR  ARRAY_CONTAINS(:event_types, g.type))" +
                "                  AND (:scales IS NULL OR  ARRAY_CONTAINS(:scales, g.scale))" +
                "                  AND (:regions IS NULL OR  ARRAY_CONTAINS(:regions, g.region))" +
                "                  AND g.amount BETWEEN :minimal_grant_amount AND :maximum_grant_amount" +
                "                  AND g.id NOT IN (SELECT DISTINCT g.id FROM Grants g" +
                "                                                                 INNER JOIN Project_Grants pg ON g.id = pg.grant_id" +
                "                                                                 INNER JOIN Projects p ON p.id = pg.project_id" +
                "                                                                 INNER JOIN Users_Projects up ON p.id = up.project_id" +
                "                                                                 INNER JOIN Users u ON up.user_id = u.id" +
                "                                   WHERE u.name = :username);";
        User user = getUser(username);
        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getName());
        params.put("event_types", user.getEventTypes());
        params.put("scales", user.getScales());
        params.put("regions", user.getRegions());
        params.put("minimal_grant_amount", user.getMinimalGrantAmount());
        params.put("maximum_grant_amount", user.getMaximumGrantAmount());

        List<Grant> grants = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Grant.class));

        return grants;
    }

    public User getUser(String userName) {
        String sql = "SELECT id, name as username, send_email, minimal_grant_amount, maximum_grant_amount, event_types, regions, scales FROM Users WHERE name = :name";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", userName);
        User user = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(User.class));
        return user;
    }

    @GetMapping("/{id}")
    public Grant getGrantById(@PathVariable("id") int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM Grants WHERE id = :id", params, Grant.class);
    }

    @PostMapping
    public void createGrant(@RequestBody Grant grant) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", grant.getName());
        params.addValue("type", grant.getType());
        params.addValue("amount", grant.getAmount());
        params.addValue("region", grant.getRegion());
        params.addValue("scale", grant.getScale());

        namedParameterJdbcTemplate.update("INSERT INTO Grants (name, type, amount, region, scale) VALUES (:name, :type, :amount, :region, :scale)", params);
    }

    @PutMapping("/{id}")
    public Grant updateGrant(@PathVariable("id") int id, @RequestBody Grant grant) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("name", grant.getName());
        params.addValue("type", grant.getType());
        params.addValue("amount", grant.getAmount());
        params.addValue("region", grant.getRegion());
        params.addValue("scale", grant.getScale());

        namedParameterJdbcTemplate.update("UPDATE Grants SET name = :name, type = :type, amount = :amount, region = :region, scale = :scale WHERE id = :id", params);
        return getGrantById(id);
    }

}
