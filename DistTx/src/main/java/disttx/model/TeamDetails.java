package disttx.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="team_details")
public class TeamDetails implements Serializable{
	private String id;
	private String teamName;
	private String location;
	private Integer currentRank;
	private Date builtOn;
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="team_name")
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name="current_rank")
	public Integer getCurrentRank() {
		return currentRank;
	}
	public void setCurrentRank(Integer currentRank) {
		this.currentRank = currentRank;
	}
	@Column(name="built_on")
	public Date getBuiltOn() {
		return builtOn;
	}
	public void setBuiltOn(Date builtOn) {
		this.builtOn = builtOn;
	}
}
