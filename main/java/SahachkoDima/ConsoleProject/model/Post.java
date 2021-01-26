package SahachkoDima.ConsoleProject.model;

import java.util.Date;

public class Post {
	private Long id;
	private String content;
	private Date created;
	private Date updated;
	
	public Post(String content) {
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		if((created == null) && (updated == null)) {
			return id + "." + content;	
		} else if(updated == null) {
			return id + "." + content + ". (Created: " + created + ")";
		} else {
		return  id + "." + content + ". (Created: " + created + ". Updated: " + updated + ")";
		}
	}

	
}
