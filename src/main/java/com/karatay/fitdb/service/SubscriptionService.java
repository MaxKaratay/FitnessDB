package com.karatay.fitdb.service;

import com.karatay.fitdb.domain.*;
import com.karatay.fitdb.repository.ClientRepository;
import com.karatay.fitdb.repository.SubscriptionRepository;
import com.karatay.fitdb.repository.VisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private VisitsRepository visitsRepository;
    @Autowired
    private ClientRepository clientRepository;

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * check the current number of subscription by instruct and compare it with discipline limit
     *
     * @param instruct current instructor and discipline
     * @return true if subscription can be added and false otherwise
     */
    public boolean freeTrainingPlace(Instruct instruct) {
        int t = subscriptionRepository.countSubscriptionByInstruct(instruct);
        System.out.println(t);
        System.out.println(instruct.getDiscipline().getMaxVisitors());
        return t < instruct.getDiscipline().getMaxVisitors();
    }

    public List<Schedule> allSchedulesByClient(Client client){
        return StreamSupport.stream(subscriptionRepository.findAllByClient(client).spliterator(), false)
                .map(Subscription::getVisits).flatMap(List::stream).map(Visits::getSchedule)
                .collect(Collectors.toList());
    }

    public Set<Schedule> schedulesByInstructWithOutCrossing(Instruct instruct, Client client) {//TODO make it work
        List<Schedule> schedulesPeriodByClient = allSchedulesByClient(client);
        Set<Schedule> result = new TreeSet<>(Comparator.comparingInt(e -> e.getPeriod().hashCode()));
        Iterable<Schedule> allByInstruct = scheduleService.getScheduleRepository().findAllByInstruct(instruct);
        if (schedulesPeriodByClient.isEmpty()) {
            for (Schedule s : allByInstruct) {
                result.add(s);
            }
            return result;
        }
        for (Schedule sch : allByInstruct) {
            Period p = sch.getPeriod();
            for (Schedule clientSch : schedulesPeriodByClient) {
                if (!p.getDay().getID().equals(clientSch.getPeriod().getDay().getID())
                        && !p.getTime().getID().equals(clientSch.getPeriod().getTime().getID())) {
                    result.add(sch);
                }
            }
        }
        return result;
    }

    /**
     * Method  organise data for report
     *
     * @param subscriptions collection with subscription ...
     * @return map witch key is discipline and value is the map that contain instruct and number of client pair
     */
    public Map<Discipline, Map<Instruct, Integer>> reportData(Collection<Subscription> subscriptions) {
        Map<Discipline, Map<Instruct, Integer>> result = new HashMap<>();
        Iterable<Discipline> disciplines = scheduleService.getInstructService().getDisciplineRepository().findAll();
        for (Discipline d : disciplines) {
            Iterable<Instruct> instructs = scheduleService.getInstructService().getInstructRepository().findAllByDiscipline(d);
            Map<Instruct, Integer> ii = new HashMap<>();
            for (Instruct i : instructs) {
                ii.put(i, (int) subscriptions.stream().map(Subscription::getInstruct).filter(e -> e.equals(i)).count());
            }
            result.put(d, ii);
        }
        return result;
    }

    public SubscriptionRepository getSubscriptionRepository() {
        return subscriptionRepository;
    }

    public VisitsRepository getVisitsRepository() {
        return visitsRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }
}
