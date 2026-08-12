package com.corpcazorla.epidemiological.infrastructure.adapter.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EpidemiologicalAttentionRequest {
	@NotBlank(message = "startDate no puede estar vacío")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de startDate debe ser YYYY-MM-DD")
	private String startDate;

	@NotBlank(message = "endDate no puede estar vacío")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de endDate debe ser YYYY-MM-DD")
	private String endDate;

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
