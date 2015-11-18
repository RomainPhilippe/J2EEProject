package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.User;

public class UserMapper implements RowMapper<User> {
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      User User = new User(rs.getInt("id_user"), rs.getString("mail"),
    		  rs.getString("password"),rs.getDate("date"),rs.getString("token"));

      return User;
   }
}