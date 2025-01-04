package spring.error;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EdadEntModel {
	
	@JsonProperty("v_anos")    		private Integer anos;
	@JsonProperty("v_mes")    		private Integer mes;
	@JsonProperty("v_dias")    		private Integer dias;
	
}
