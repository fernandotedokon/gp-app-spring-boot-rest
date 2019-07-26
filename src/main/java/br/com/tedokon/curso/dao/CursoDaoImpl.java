package br.com.tedokon.curso.dao;

import br.com.tedokon.curso.domain.Curso;
import br.com.tedokon.curso.exception.NaoExisteDaoException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

// repository - classe de persistencia gerenciada pelo spring
@Repository
public class CursoDaoImpl implements CursoDao {

    // injeta uma instancia nessa variavel sempre que precisarmos dela
    @PersistenceContext
    private EntityManager entityManager;

    // curso que sera inclido
    @Override
    public void save(Curso curso) {

        entityManager.persist(curso);
    }

    // curso que sera alterado
    @Override
    public void update(Curso curso) {

        entityManager.merge(curso);
    }

    // metodo para exclui uma linha busca referencia da linha antes
    @Override
    public void delete(Long id) {
        try {
            entityManager.remove(entityManager.getReference(Curso.class, id));
        } catch (EntityNotFoundException ex) {
            throw new NaoExisteDaoException("Curso não encontrado para id = " + id + ".");
        }
    }

    // curso para id para localizar no banco, retorna unico curso
    @Override
    public Curso findById(Long id) {
        Curso curso = entityManager.find(Curso.class, id);
        if (curso == null) {
            throw new NaoExisteDaoException("Curso não encontrado para id = " + id + ".");
        }
        return curso;
    }

    // retorna lista de curso
    @Override
    public List<Curso> findAll() {
        return entityManager
                .createQuery("select c from Curso c", Curso.class)
                .getResultList();
    }
}
