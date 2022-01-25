package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    @Query("select w from Worker as w where w.departure.id = :departureId and w.name in :list ")
    List<Worker> findAllByDepartureId(@Param("departureId") int departureId);

    Worker findWorkerById(int id);

    Worker findWorkerByName(String name);

}
