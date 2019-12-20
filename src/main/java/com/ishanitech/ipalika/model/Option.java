/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 20, 2019
 */
package com.ishanitech.ipalika.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Option {
	private int id;
	private String optionId;
	private String optionText;
	private int questinId;
}
