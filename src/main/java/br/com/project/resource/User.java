package br.com.project.resource;

import java.util.List;

import br.com.project.enums.AuthoritiesEnum;
import br.com.project.enums.ScopeEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ApiModel
public class User {

	private List<ScopeEnum> scope;
	private String client_id;
	private String user_id;
	private List<AuthoritiesEnum> authorities;

}
