/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Jan 27, 2020
 */
package com.ishanitech.ipalika.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResidentMemberDTO {
	private AnswerDTO answer;
	private List<MemberDTO> member;
}
