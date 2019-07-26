package br.com.tedokon.curso.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// configuracao
// habilita suporte spring mvc
// controla transacoes banco de dados
// scaniar tudo que estiver no pacote
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("br.com.tedokon.curso")
public class SpringRootConfig {
}
