package com.example.agenda.services;

import com.example.agenda.models.Agenda;
import com.example.agenda.repositories.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaServiceImpl implements AgendaService{

    @Autowired
    private AgendaRepository repository;
    @Override
    public List<Agenda> listAgenda() {


        return repository.findAll();

    }

    @Override
    public Agenda saveAgenda(Agenda agenda) {
        return repository.save(agenda);
    }

    @Override
    public Agenda getAgendaById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Agenda updateAgenda(Agenda agenda) {
        return repository.save(agenda);
    }

    @Override
    public void deleteAgenda(Integer id) {

         repository.deleteById(id);

    }
}
