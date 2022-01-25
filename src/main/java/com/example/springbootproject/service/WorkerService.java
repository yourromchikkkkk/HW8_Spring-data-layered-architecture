package com.example.springbootproject.service;


import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.entity.WorkerInfo;
import com.example.springbootproject.logger.Logger;
import com.example.springbootproject.repository.WorkerInfoRepository;
import com.example.springbootproject.repository.WorkerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    Logger logger;

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    DepartureService departureService;

    @Autowired
    WorkerInfoRepository workerInfoRepository;

    public Worker getById(int id) {
        Worker worker = workerRepository.findWorkerById(id);
        return worker;
    }

    public Worker addWorker(Worker worker) {
        Worker returnWorker = getById(worker.getId());
        if (Objects.isNull(returnWorker)) {
             return workerRepository.save(worker);
        } else {
            logger.logMessage(String.format("Worker with id = %s is already present", worker.getId()));
            return null;
        }
    }

    public Worker getByName(String name) {
        return workerRepository.findWorkerByName(name);
    }

    public Worker updateWorker(Worker worker) {
        Optional<Worker> optionalWorker = workerRepository.findById(worker.getId());
        if (optionalWorker.isPresent()) {
            return workerRepository.save(worker);
        } else {
            logger.logMessage(String.format("No such worker to update with id = %s", worker.getId()));
            return null;
        }
    }

    public List<Worker> getByDepartureId(int id) {
        List<Worker> returnList = workerRepository.findAllByDepartureId(id);
        if (!Objects.isNull(returnList)) {
            return returnList;
        } else {
            logger.logMessage(String.format("There are no workers in departure with id = %s", id));
            return null;
        }
    }

    public Worker setWorkerInfo(int id, WorkerInfo workerInfo) {
        Worker returnWorker = getById(id);
        returnWorker.setId(id);
        if (!Objects.isNull(returnWorker)) {
            returnWorker.setWorkerInfo(workerInfo);
            workerInfoRepository.save(workerInfo);
            return updateWorker(returnWorker);
        } else {
            return null;
        }
    }
}
