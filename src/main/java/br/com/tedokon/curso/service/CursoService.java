package br.com.tedokon.curso.service;

import br.com.tedokon.curso.domain.Curso;

import java.util.Date;
import java.util.List;

// camada de regra de negocio, separa regras ou logicas que nao fazem parte do controler da aplicacao
// inteface
public interface CursoService {

    void save(Curso curso);

    // 2 argumentos
    void update(Long id, Curso curso);

    void delete(Long id);

    Curso findById(Long id);

    // sem argumentos, retorna lista de curso
    List<Curso> findAll();

    // altera uma data de curso ja cadastrado
    Curso updateDataInicio(Long id, Date dataInicio);
}
