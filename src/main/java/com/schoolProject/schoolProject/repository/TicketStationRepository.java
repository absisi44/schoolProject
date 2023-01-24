package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.TicketStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStationRepository extends JpaRepository<TicketStation,Long> {
    TicketStation findByTicketId(String ticketId);
}



