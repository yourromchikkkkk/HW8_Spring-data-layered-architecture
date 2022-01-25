package com.example.springbootproject.repository;

import com.example.springbootproject.entity.WorkerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerInfoRepository extends JpaRepository<WorkerInfo, Integer> {

}
