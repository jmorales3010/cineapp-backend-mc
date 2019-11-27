package com.mitocode;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario = usuarioService.listarPorNombre(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		
		//Con esto podemos agregar informacional adicional al token
		//info.put("Info_adicional", "Informacion Adicional ".concat(authentication.getName()));
		//info.put("Info_adicional", authentication.getName());
		
		info.put("id_usuario", usuario.getIdUsuario());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
	
}
