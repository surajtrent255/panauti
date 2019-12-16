package com.ishanitech.ipalika.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 
 * @author Umesh Bhujel
 *
 */
@Data
@NoArgsConstructor
@ToString
public class User implements Serializable{
	private static final long serialVersionUID = 3484530113208289531L;
	private int id;
	private String username;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private boolean expired;
	private boolean firstLogin;
	private Date registeredDate;
	private String mobileNumber;
	
	private String password;
	private boolean enabled;
	private boolean locked;
	private Set<Role> role = new HashSet<>();
}
