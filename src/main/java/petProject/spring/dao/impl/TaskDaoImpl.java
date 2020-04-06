package petProject.spring.dao.impl;

import petProject.spring.dao.TaskDao;
import petProject.spring.persistance.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class TaskDaoImpl implements TaskDao {
    private  final NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM tasks",
                new EmptySqlParameterSource(),
                new BeanPropertyRowMapper<>(Task.class));
    }

    @Override
    public void create(Task task) {
        jdbcTemplate.update("INSERT  INTO  tasks (name, description) VALUES (:name, :description)",
                new MapSqlParameterSource()
        .addValue("name",task.getName())
        .addValue("description",task.getDescription()));

    }
}
