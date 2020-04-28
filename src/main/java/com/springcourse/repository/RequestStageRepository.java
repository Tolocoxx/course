package com.springcourse.repository;

import com.springcourse.domain.RequestStage;
import com.springcourse.domain.enums.RequestState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {


    public List<RequestState> findAllByRequestId(Long id);
}
