package com.example.smartindiahackathon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.smartindiahackathon.Model.Ticket;



@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	
}
