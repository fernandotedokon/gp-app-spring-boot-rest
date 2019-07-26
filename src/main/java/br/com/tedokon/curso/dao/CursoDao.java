package br.com.tedokon.curso.dao;

import br.com.tedokon.curso.domain.Curso;

import java.util.List;

// classe de persistencia JPA, recomendado injetar dependencias atraves de interface e nao classe concretas
public interface CursoDao {

    // metodos
    void save(Curso curso);

    void update(Curso curso);

    void delete(Long id);

    Curso findById(Long id);

    List<Curso> findAll();
}
