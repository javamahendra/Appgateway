package com.app.service;

import java.util.List;

import com.app.domain.Invoice;

public interface InvoiceService {
	
	public List<Invoice> getAllInvoices();
	public Integer saveInvoices(List<Invoice> invoices);

}
