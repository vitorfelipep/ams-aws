package com.project.ams.funerary.config;


/**
 * @author vitor
 *
 */
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
////	@Autowired
////	private AuthenticationManager authenticationManager;
//
//	 @Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		 clients.inMemory()
//		 	.withClient("angular").secret("@ngul@r0")
//		 	.scopes("read", "write")
//		 	.authorizedGrantTypes("password", "refresh_token")
//		 	.accessTokenValiditySeconds(1800)
//		 	.refreshTokenValiditySeconds(3600 * 24)
//		 	.and()
//		 	.withClient("mobile")
//		 	.secret("m0b1l3")
//		 	.scopes("read")
//		 	.authorizedGrantTypes("password", "refresh_token")
//		 	.accessTokenValiditySeconds(1800)
//		 	.refreshTokenValiditySeconds(3600 * 24);
//	 }
//
//	 @Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
////		 endpoints
////		 	.tokenStore(tokenStore())
////		 	.accessTokenConverter(accessTokenConverter())
////		 	.reuseRefreshTokens(false)
////		 	.authenticationManager(authenticationManager);
//	}
//
//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//		accessTokenConverter.setSigningKey("montesião");
//		return accessTokenConverter;
//	}
//
//	@Bean
//	public TokenStore tokenStore() {
//		return new JwtTokenStore(accessTokenConverter());
//	}
//}