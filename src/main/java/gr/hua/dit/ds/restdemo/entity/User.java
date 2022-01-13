package gr.hua.dit.ds.restdemo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user")
public class User implements Serializable {

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private int enabled;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE })
	private Set<Authority> authorities = new HashSet<>();

	@JsonManagedReference
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE })
	private UserDetails userDetails;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.DETACH,
			CascadeType.REFRESH, CascadeType.REMOVE })
	private Set<Subjects> subjs = new HashSet<>();

	// Constructors
	public User() {
	}

	public User(String username, String password, int enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	// Setters+Getters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return this.enabled == 1;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setAuthority(Authority authority) {
		authorities.add(authority);
	}
	
	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public Set<Subjects> getSubjects() {
		return subjs;
	}

	public void setDayoffs(Set<Subjects> dayoffs) {
		this.subjs = subjs;
	}
	//Boolean methods to get if user contains an attribute
	public boolean containsAuthority(String authority) {
		return this.authorities.stream().map(Authority::getAuthority).filter(authority::equals).findFirst().isPresent();
	}
	
	public boolean containsDetails() {
		return (this.userDetails == null);
	}
	//Overriden methods
	@Override
	public String toString() {
		return "[User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", authorities= "+ authorities + " ]";
	}

}
