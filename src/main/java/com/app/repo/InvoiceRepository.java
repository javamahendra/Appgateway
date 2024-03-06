package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.domain.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
	
	

}
