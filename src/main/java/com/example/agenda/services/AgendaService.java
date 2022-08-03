package com.example.agenda.services;

import com.example.agenda.models.Agenda;

import java.util.List;

public interface AgendaService {


    public List<Agenda> listAgenda();
    public Agenda saveAgenda(Agenda agenda);
    public Agenda getAgendaById(Integer id);
    public Agenda updateAgenda(Agenda agenda);

    public void deleteAgenda(Integer id);

}
