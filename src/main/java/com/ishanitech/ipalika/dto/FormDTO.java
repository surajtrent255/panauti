/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 23, 2019
 */
package com.ishanitech.ipalika.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDTO {
	private int id;
	private String formId;
	private String formName;
}
