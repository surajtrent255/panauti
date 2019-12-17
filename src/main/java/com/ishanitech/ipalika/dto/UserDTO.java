/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 13, 2019
 */
package com.ishanitech.ipalika.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 2989312298454528014L;
	private String username;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	@JsonIgnore
	private String password;
	private String mobileNumber;
}
