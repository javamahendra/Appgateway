package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.Invoice;
import com.app.repo.InvoiceRepository;
import com.app.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public List<Invoice> getAllInvoices() {
		
		return invoiceRepository.findAll();
	}

	@Override
	public Integer saveInvoices(List<Invoice> invoices) {
		
		return invoiceRepository.saveAll(invoices).size();
	}

}
