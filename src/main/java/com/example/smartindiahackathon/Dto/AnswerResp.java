package com.example.smartindiahackathon.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnswerResp {

	private String answer;
	private String username;
	private Integer answerId;
}