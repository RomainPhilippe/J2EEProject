package dao;

import javax.sql.DataSource;

	public interface AreaDao {
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the Student table.
	    */
	   public void create(Integer id_user,String name_area,String label_area, Double latitude, Double longitude, Double rayon);
	  
}