package com.potato.TutorCall.config;

// @Configuration
// @NoArgsConstructor
// @EnableRedisHttpSession
// public class RedisConfig implements BeanClassLoaderAware {
//  @Value("${spring.data.redis.host}")
//  private String host;
//
//  @Value("${spring.data.redis.port}")
//  private int port;
//
//  @Value("${spring.data.redis.password}")
//  private String password;
//  @Bean
//  static ConfigureRedisAction configureRedisAction() {
//    return ConfigureRedisAction.NO_OP;
//  }
//  @Bean
//  public RedisConnectionFactory redisConnectionFactory() {
//
//    RedisStandaloneConfiguration redisStandaloneConfiguration = new
// RedisStandaloneConfiguration();
//    redisStandaloneConfiguration.setHostName(host);
//    redisStandaloneConfiguration.setPort(port);
//    redisStandaloneConfiguration.setPassword(password);
//    return new LettuceConnectionFactory(redisStandaloneConfiguration);
//  }
//
//  @Bean
//  public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//    return new GenericJackson2JsonRedisSerializer(objectMapper());
//  }
//  ObjectMapper objectMapper() {
//    ObjectMapper om = new ObjectMapper();
////    om.registerModule(new CoreJackson2Module());
//
//    om.registerModules(SecurityJackson2Modules.getModules(this.loader));
//
//    return om;
//  }
//  ClassLoader loader;
//
//  @Bean
//  public RedisTemplate<?, ?> redisTemplate() {
//    RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
//    redisTemplate.setConnectionFactory(redisConnectionFactory());
////    redisTemplate.setDefaultSerializer(new StringRedisSerializer());
//    return redisTemplate;
//  }
//
//  @Override
//  public void setBeanClassLoader(ClassLoader classLoader) {
//    this.loader = classLoader;
//  }
// }
