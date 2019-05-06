package br.com.project.component;

import java.util.List;

import br.com.project.resource.Time;

public interface TimeComponent {

	Time getTime(Integer id);

	Time saveTime(Time time);

	List<Time> getTimes();

}
