package disttx.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test_log")
public class TestLog implements Serializable{
	private String id;
	private Timestamp logTime;
	private String threadInfo;
	private String content;
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="log_time")
	public Timestamp getLogTime() {
		return logTime;
	}
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}
	@Column(name="thread_info")
	public String getThreadInfo() {
		return threadInfo;
	}
	public void setThreadInfo(String threadInfo) {
		this.threadInfo = threadInfo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
