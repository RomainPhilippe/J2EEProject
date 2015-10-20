package model;

public class Area {

	   private Integer id_area;
	   private Integer id_user;
	   private String name_area;
	   private String label_area;
	   private Double latitude;
	   private Double longitude;
	   private Double distance;
	   
	   
	public Integer getId_area() {
		return id_area;
	}
	public void setId_area(Integer id_area) {
		this.id_area = id_area;
	}
	public Integer getId_user() {
		return id_user;
	}
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	public String getName_area() {
		return name_area;
	}
	public void setName_area(String name_area) {
		this.name_area = name_area;
	}
	public String getLabel_area() {
		return label_area;
	}
	public void setLabel_area(String label_area) {
		this.label_area = label_area;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Area(Integer id_area, Integer id_user, String name_area, String label_area, Double latitude, Double longitude,
			Double distance) {
		super();
		this.id_area = id_area;
		this.id_user = id_user;
		this.name_area = name_area;
		this.label_area = label_area;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
	}
	   
	   
}
