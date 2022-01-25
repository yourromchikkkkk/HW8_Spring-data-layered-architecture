package com.example.springbootproject.controller;

import com.example.springbootproject.dto.DepartureDto;
import com.example.springbootproject.entity.Departure;
import com.example.springbootproject.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class DepartureController {

    @Autowired
    DepartureService departureService;

    @GetMapping("/hello")
    public String hello() {
        return "HELLO DDD";
    }

    @PostMapping("/departures")
    public ResponseEntity<Departure> addDeparture(@RequestBody Departure departure) {
        Departure returnValue = departureService.addDeparture(departure);

        if (Objects.isNull(returnValue)) {
           return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(returnValue);
        }
    }

    @GetMapping("/departures")
    public List<Departure> getAllDepartures() {
        return departureService.getDepartures();
    }

    @GetMapping("/departures/{name}")
    public ResponseEntity<Departure> getDepartureByName(@PathVariable String name) {
        Departure departureByName = departureService.getByName(name);

        if (Objects.isNull(departureByName)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(departureByName);
        }
    }

    @PutMapping("/departures/{id}")
    public ResponseEntity<Departure> updateDeparture(@RequestBody Departure departure, @PathVariable int id) {
        departure.setId(id);
        Departure returnValue = departureService.updateDeparture(departure);

        if (Objects.isNull(returnValue)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(returnValue);
        }
    }
}
