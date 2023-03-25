package Beam;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="MemberEntity")
@Table(name="AdvancedPermissions")
public class MemberBeam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer P_ID;
	private String username;
	private String account;
	private String password;
	private String bornDate;
	private String identityCard;
	private String phoneNumber;
	private String email;
	
	public MemberBeam() {
		
	}
	
	public MemberBeam(Integer p_ID, String username, String account, String password, String bornDate,
			String identityCard, String phoneNumber, String email) {
		super();
		P_ID = p_ID;
		this.username = username;
		this.account = account;
		this.password = password;
		this.bornDate = bornDate;
		this.identityCard = identityCard;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public MemberBeam(String username, String account, String password, String bornDate,
			String identityCard, String phoneNumber, String email) {
		super();

		this.username = username;
		this.account = account;
		this.password = password;
		this.bornDate = bornDate;
		this.identityCard = identityCard;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}


	public Integer getP_ID() {
		return P_ID;
	}
	
	public void setP_ID(Integer p_ID) {
		P_ID = p_ID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBornDate() {
		return bornDate;
	}
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
