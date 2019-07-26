package br.com.tedokon.curso.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    // SpringRootConfig primeira classe que sera inicializada
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringRootConfig.class};
    }

    // nao precisa mexer nao vai trabalhar com recurso mais avancado
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    // quando encontrar / rem uma configuracao
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
