package com.example.springbootproject.controller;

import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.entity.WorkerInfo;
import com.example.springbootproject.repository.WorkerRepository;
import com.example.springbootproject.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class WorkerController {

    @Autowired
    WorkerRepository repository;

    @Autowired
    WorkerService workerService;

    //getOne
    @GetMapping("/workers/{id}")
    public ResponseEntity<Worker> getById(@PathVariable int id) {
        Worker worker = workerService.getById(id);
        if (!Objects.isNull(worker)) {
            return ResponseEntity.ok(workerService.getById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //getByName
    @GetMapping("/workers/name/{name}")
    public ResponseEntity<Worker> getByName(@PathVariable String name) {
        if (!Objects.isNull(workerService.getByName(name))) {
            return ResponseEntity.ok(workerService.getByName(name));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //createOne
    @PostMapping("/workers")
    public ResponseEntity<Worker> createOne(@RequestBody Worker worker) {
        if (Objects.isNull(workerService.addWorker(worker))) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(workerService.getById(worker.getId()));
        }
    }

    //getAll
    @GetMapping("/workers")
    public List<Worker> getAllWorkers() {
        return repository.findAll();
    }

    //updateOne
    @PutMapping("/workers/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable int id, @RequestBody Worker worker) {
        worker.setId(id);
        Worker returnWorker = workerService.updateWorker(worker);
        if (!Objects.isNull(returnWorker)) {
            return ResponseEntity.ok(workerService.getById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //getByDeparture
    @GetMapping("/workers/departure/{id}")
    public ResponseEntity<List<Worker>> getByDepartureId(@PathVariable int id) {
        List<Worker> returnWorker = workerService.getByDepartureId(id);
        if (!Objects.isNull(returnWorker)) {
            return ResponseEntity.ok(returnWorker);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //setWorkerInfo
    @PostMapping("/workers/{id}/info")
    public ResponseEntity<Worker> setWorkerInfo(@PathVariable int id, @RequestBody WorkerInfo workerInfo) {
        Worker returnWorker = workerService.setWorkerInfo(id, workerInfo);
        if (!Objects.isNull(returnWorker)) {
            return ResponseEntity.ok(returnWorker);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //не працюэ коректно, як би хотілось
//    @PutMapping("/workers/{id}/info")
//    public ResponseEntity<Worker> updateWorkerInfo(@PathVariable int id, @RequestBody WorkerInfo workerInfo) {
//        return updateWorkerInfo(id, workerInfo);
//    }
}
