package com.fitdb.service;

import com.fitdb.domain.Discipline;
import com.fitdb.domain.Instruct;
import com.fitdb.domain.Instructor;
import com.fitdb.repository.DisciplineRepository;
import com.fitdb.repository.InstructRepository;
import com.fitdb.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InstructService {
    @Autowired
    private InstructRepository instructRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    public InstructRepository getInstructRepository() {
        return instructRepository;
    }

    public InstructorRepository getInstructorRepository() {
        return instructorRepository;
    }

    public DisciplineRepository getDisciplineRepository() {
        return disciplineRepository;
    }

    public Map<Discipline, Map<Instructor, Integer>> getInstructListByDiscipline() {
        Map<Discipline, Map<Instructor, Integer>> result = new TreeMap<>(Comparator.comparingLong(Discipline::getID));
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        for (Discipline dis : disciplines) {
            result.put(dis, findAllByDiscipline(dis));
        }
        return result;
    }

    public Map<Discipline, Integer> findAllDisciplineByInstructor(Instructor instructor) {/// maybe remove
        Iterable<Instruct> allByInstructor = instructRepository.findAllByInstructor(instructor);
        Map<Discipline, Integer> result = new HashMap<>();
        for (Instruct i : allByInstructor) {
            result.put(i.getDiscipline(), i.getPrice());
        }
        return result;
    }

    /**
     * @param discipline required discipline for searching
     *
     * @return return map with instructors and prices for they lecture for discipline
    */
    public Map<Instructor, Integer> findAllByDiscipline(Discipline discipline) {
        Map<Instructor, Integer> result = new HashMap<>();
        Iterable<Instruct> allByDiscipline = instructRepository.findAllByDiscipline(discipline);
        for (Instruct i : allByDiscipline) {
            result.put(i.getInstructor(), i.getPrice());
        }
        return result;
    }

    public boolean exist(Discipline discipline, Instructor instructor) {
        return instructRepository.findByDisciplineAndInstructor(discipline, instructor).isPresent();
    }
}
