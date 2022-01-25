package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQueries;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Integer> {

    @Query("select d from Departure as d where d.name = :name")
    Departure findDepartureByName(@Param("name") String name);
}
