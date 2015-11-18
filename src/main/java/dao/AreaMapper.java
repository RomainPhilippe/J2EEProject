package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Area;

public class AreaMapper implements RowMapper<Area> {
   public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
      Area area = new Area(rs.getInt("id_area"), rs.getInt("id_user"),rs.getString("name_area"),
    		  rs.getString("label_area"),
    		  rs.getDouble("latitude"),rs.getDouble("longitude"),rs.getDouble("longitude"));

      return area;
   }
}