package com.app.rest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxls.template.SimpleExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.domain.Invoice;
import com.app.service.InvoiceService;

@RestController
public class ExcelDownload {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping(value = "/dwnldxls", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public @ResponseBody FileSystemResource getDwnldExcel(HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		response.setHeader("Content-Disposition", "inline; filename='Invoice.xls");
		List<String> headers = Arrays.asList("Invoice No", "Invoice Date", "GSTIN", "Customer Name", "State Name",
				"Quantity", "Rate", "Discount", "Taxable Value", "IGST Rate", "IGST Amount", "CGST Rate", "CGST Amount",
				"SGST Rate", "SGST Amount", "Total Invoice Value");

		File file = new File("Invoice.xls");
		FileOutputStream fos = new FileOutputStream(file);
		file.createNewFile();
		SimpleExporter exporter = new SimpleExporter();

		List<Invoice> invoiceLst = invoiceService.getAllInvoices();
		
		exporter.gridExport(headers, invoiceLst,
				"inum, strDate, gtin, billedtoname, statename, quantity, rateperitem, discount, taxablevalue, igstrate, igstamount, cgstrate, cgstamount, sgstrate, sgstamount, total",
				fos);

		return new FileSystemResource(file);
	}

}
