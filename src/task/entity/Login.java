package task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {

	@Id
	@Column(name = "user")
	private String user;
	
	@Column(name = "psd")
	private String psd;

	public Login() {

	}

	public Login(String user, String psd) {
		setUser(user);
		setPsd(psd);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	@Override
	public String toString() {
		return "Login [user=" + user + ", psd=" + psd + "]";
	}
        
}
