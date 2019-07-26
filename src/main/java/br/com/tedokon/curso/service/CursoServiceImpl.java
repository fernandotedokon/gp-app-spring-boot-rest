package br.com.tedokon.curso.service;

import br.com.tedokon.curso.dao.CursoDao;
import br.com.tedokon.curso.domain.Curso;
import br.com.tedokon.curso.exception.IdNaoValidoServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

// bean gerenciado pelo spring do tipo de servico, de regras de negocio
// gerencia as transacoes, como readly como falze passa a ser gerenciado pelas transacoes
@Service
@Transactional
public class CursoServiceImpl implements CursoService {

    // injeta interface anotacao nativa do spring
    @Autowired
    private CursoDao dao;

    // intrucao para como parametro curso
    @Override
    public void save(Curso curso) {

        dao.save(curso);
    }

    // argumento id e curso
    // mnetodo merge da jpa precisa que passa id senao ele trabalha como inclusao
    @Override
    public void update(Long id, Curso curso) {
        curso.setId(idValido(id));
        dao.findById(id);
        dao.update(curso);
    }

    // arqumento id passado como parametro
    @Override
    public void delete(Long id) {

        dao.delete(idValido(id));
    }

    // define que eh um metodo especifico de leitura, retorna apenas um curso
    @Override @Transactional(readOnly = true)
    public Curso findById(Long id) {

        return dao.findById(idValido(id));
    }

    // retorna uma lista de curso
    @Override @Transactional(readOnly = true)
    public List<Curso> findAll() {

        return dao.findAll();
    }

    // faz apenas uma consulta, curso retornado em estado persistente
    // hibernete altea data inicio no banco de dados
    @Override
    public Curso updateDataInicio(Long id, Date dataInicio) {

        Curso curso = dao.findById(idValido(id));
        curso.setDataInicio(dataInicio);
        return curso;
    }

    private Long idValido(Long id) {
        if (id <= 0) {
            throw new IdNaoValidoServiceException("Valor do campo id está invalido. Deve ser uma valor inteiro maior que zero.");
        }
        return id;
    }
}
