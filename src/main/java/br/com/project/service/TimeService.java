package br.com.project.service;

import java.util.List;

import br.com.project.resource.Time;

public interface TimeService {

	Time getTime(Integer id);

	Time saveTime(Time time);

	List<Time> getTimes();

}
