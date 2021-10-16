package com.projeto.services;

import com.projeto.converter.DozerConverter;
import com.projeto.data.vo.PersonVO;
import com.projeto.exception.ResourceNotFoundException;
import com.projeto.data.model.Person;
import com.projeto.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public PersonVO create (PersonVO person){
        var entity = DozerConverter.parserOject(person, Person.class);
        var vo = DozerConverter.parserOject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public List<PersonVO> findAll(){
        return DozerConverter.paserListObjects(repository.findAll(), PersonVO.class);

    }

    public PersonVO findById(Long id){
       var entity = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No records found for this ID."));
       return DozerConverter.parserOject(entity, PersonVO.class);
    }

    public PersonVO update(PersonVO person){
        Person entity = repository.findById(person.getId())
                .orElseThrow(()->new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerConverter.parserOject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id){
        Person entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
