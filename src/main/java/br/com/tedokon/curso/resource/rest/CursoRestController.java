package br.com.tedokon.curso.resource.rest;

import br.com.tedokon.curso.domain.Curso;
import br.com.tedokon.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// RestController - receber e responder instrucoes
@RestController
@RequestMapping(
        value = "/cursos",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
// consumida e respondida como Json
public class CursoRestController {

    // interface - inseta na classe CursoRestController
    @Autowired
    private CursoService service;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id") Long id) {

        service.delete(id);
    }

    //
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Curso editarDataInicio(@PathVariable("id") Long id, @RequestBody Curso curso) {

        return service.updateDataInicio(id, curso.getDataInicio());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Curso editar(@PathVariable("id") Long id, @RequestBody Curso curso) {

        service.update(id, curso);
        return curso;
    }

    // recebe path id
    // anotacao status sucesso ok
    // arumento path id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Curso getCurso(@PathVariable("id") Long id) {

        // retorna no dao
        return service.findById(id);
    }

    // recebe apenas requisicao post - nesse caso inclusao metodo aqui
    // requestbody - recebe na requisao objeto pronto do cabecalho
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Curso curso) {

        service.save(curso);

        // apos incluir - cria url para enviar ao cliente
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(curso.getId()) // adiciona valor no lugar do id do path get id
                .toUri(); // retorna objeto para variavel locacion

        return ResponseEntity.created(location).build(); // retorna status 201 -  passa uri location - build finaliza
    }

    // metodo retorna lista de curso - nao possui argumento instrucoes como parametros
    // metodo tem que ser do tipo get
    // quando request /curso cai no metodo listae
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> listar() {
        return service.findAll();
    }
}
