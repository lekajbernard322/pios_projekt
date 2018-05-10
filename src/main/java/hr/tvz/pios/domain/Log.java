package hr.tvz.pios.domain;

import java.time.LocalDateTime;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="log")
public class Log {
	
	@Id
    private String id;
	
	private String logText;
	
    private LocalDateTime createDate = LocalDateTime.now();
    
    public Log() {}
    
    public Log(String id, String logText) {
        this.id = id;
        this.logText = logText;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getLogText() {
		return logText;
	}

	public void setLogText(String logText) {
		this.logText = logText;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log that = (Log) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
    @Override
    public String toString() {
    	return "[log=" + logText + ", date=" + createDate + "]";
    }

}
