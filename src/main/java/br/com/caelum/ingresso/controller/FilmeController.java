package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.infra.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sessao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents.UriTemplateVariables;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class FilmeController {


    @Autowired
    private FilmeDao filmeDao;
    @Autowired
    private SessaoDao sessaoDao;
    @Autowired
    private imdbClient imdbClient;
    
    @GetMapping ("/filme/em-cartaz")
    public ModelAndView emCartaz() {
    	ModelAndView modelAndView = new ModelAndView ("filme/em-cartaz");
    	modelAndView.addObject("filmes", filmeDao.findAll());
    	return modelAndView;
    }
    
    @GetMapping ("filme/{idFilme}/detalhe")
    public ModelAndView detalhe(@PathVariable ("idFilme") Integer filmeId) {
    	ModelAndView modelAndView = new ModelAndView("filme/detalhe");
    	Filme filme = filmeDao.findOne(filmeId);
    	
    	Optional <DetalhesDoFilme> optional = imdbClient.request(filme);
    	modelAndView.addObject("detalhes", optional.orElse(new DetalhesDoFilme()));
    	List<Sessao> sessoesDoFilme = sessaoDao.buscaSessoesDoFilme(filme);
    	modelAndView.addObject("sessoes", sessoesDoFilme);
    	return modelAndView;
    }


    @GetMapping({"/admin/filme", "/admin/filme/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, Filme filme){

        ModelAndView modelAndView = new ModelAndView("filme/filme");

        if (id.isPresent()){
            filme = filmeDao.findOne(id.get());
        }

        modelAndView.addObject("filme", filme);

        return modelAndView;
    }


    @PostMapping("/admin/filme")
    @Transactional
    public ModelAndView salva(@Valid Filme filme, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(filme.getId()), filme);
        }

        filmeDao.save(filme);

        ModelAndView view = new ModelAndView("redirect:/admin/filmes");

        return view;
    }


    @GetMapping(value="/admin/filmes")
    public ModelAndView lista(){

        ModelAndView modelAndView = new ModelAndView("filme/lista");

        modelAndView.addObject("filmes", filmeDao.findAll());

        return modelAndView;
    }


    @DeleteMapping("/admin/filme/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        filmeDao.delete(id);
    }

}
