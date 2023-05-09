package com.fundacionoasis.service;

import com.fundacionoasis.entity.*;
import com.fundacionoasis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebPageService {
    @Autowired
    private IHomeRepository iHomeRepository;
    @Autowired
    private IProyectosRepository iProyectosRepository;
    @Autowired
    private IProgramRepository iProgramRepository;
    @Autowired
    private IDonacionesRepository iDonacionesRepository;
    @Autowired
    private IFooterRepository iFooterRepository;


    public List<HomePage> getAllHomes(){
        return iHomeRepository.findAll();
    }
    public HomePage saveHomes(HomePage home){
        return iHomeRepository.save(home);
    }
    public Optional<HomePage> findByIdHome(Long id){
        return iHomeRepository.findById(id);
    }
    public List<Proyectos> getAllProyectos(){
        return iProyectosRepository.findAll();
    }
    public Proyectos saveProyectos(Proyectos home){
        return iProyectosRepository.save(home);
    }
    public Optional<Proyectos> findByIdProyectos(Long id){
        return iProyectosRepository.findById(id);
    }
    public List<Program> getAllProgram(){
        return iProgramRepository.findAll();
    }
    public Program saveProgram(Program home){
        return iProgramRepository.save(home);
    }
    public Optional<Program> findByIdProgram(Long id){
        return iProgramRepository.findById(id);
    }

    public List<Donaciones> getAllDonaciones(){
        return iDonacionesRepository.findAll();
    }
    public Donaciones saveDonaciones(Donaciones home){
        return iDonacionesRepository.save(home);
    }
    public Optional<Donaciones> findByIdDONACIONES(Long id){
        return iDonacionesRepository.findById(id);
    }
    public List<Footer> getAllFooter(){
        return iFooterRepository.findAll();
    }
    public Footer saveFooter(Footer home){
        return iFooterRepository.save(home);
    }
    public Optional<Footer> findByIdFotter(Long id){
        return iFooterRepository.findById(id);
    }


}
