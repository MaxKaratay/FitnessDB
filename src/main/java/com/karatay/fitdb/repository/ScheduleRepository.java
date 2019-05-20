package com.karatay.fitdb.repository;

import com.karatay.fitdb.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    @Query(value = "select s from Schedule s inner join Period p on s.period = p" +
            " inner join Day d on p.day = d where d = :day" +
            " order by STR_TO_DATE(s.period.time.start,'%i:%s')")
    Iterable<Schedule> findAllByDay(@Param("day") Day day);
    Iterable<Schedule> findAllByInstruct(Instruct instruct);
    Iterable<Schedule> findAllByInstruct_Instructor(Instructor instructor);
    Iterable<Schedule> findAllByInstruct_Discipline(Discipline discipline);
}
