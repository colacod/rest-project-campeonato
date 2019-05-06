package br.com.project.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.project.entity.TimeEntity;
import br.com.project.repository.TimeRepository;
import br.com.project.resource.Time;

@Component
@Transactional
public class TimeComponentImpl implements TimeComponent {

	@Autowired
	private TimeRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Time getTime(Integer id) {
		Optional<TimeEntity> time = repository.findById(id);
		if (time.isPresent()) {
			return modelMapper.map(time.get(), Time.class);
		}
		return null;
	}

	@Override
	public List<Time> getTimes() {
		List<TimeEntity> timesEntity = repository.findAll();
		if (timesEntity.isEmpty()) {
			return new ArrayList<>();
		}
		return timesEntity.stream().map(source -> modelMapper.map(source, Time.class)).collect(Collectors.toList());
	}

	@Override
	public Time saveTime(Time time) {
		TimeEntity timeEntity = new TimeEntity();
		timeEntity.setTimeNome(time.getTimeNome());
		timeEntity.setTimeAbreviado(time.getTimeAbreviado());
		return modelMapper.map(repository.saveAndFlush(timeEntity), Time.class);
	}
}
