package dao;

import java.util.List;

import javax.sql.DataSource;

import model.Area;

	public interface AreaDao {
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the Area table.
	    */
	   public void create(Integer id_user,String name_area,String label_area, Double latitude, Double longitude, Double rayon);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the Area table.
	    */
	   public List<Area> listAreaByIdUser(Integer id_user);
}