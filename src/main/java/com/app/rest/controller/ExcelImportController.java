package com.app.rest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.domain.ImportSummary;
import com.app.domain.Invoice;
import com.app.service.InvoiceService;

import static com.app.util.NullUtil.isEmpty;
import static com.app.util.NullUtil.isNotEmpty;

@RestController
@RequestMapping("/excel")
public class ExcelImportController {
	
	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private ResourceLoader resourceLoader;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

	@PostMapping("/upload")
	public void excelImport(@RequestParam("file") MultipartFile file) throws InvalidFormatException {

		//ImportResponse response = new ImportResponse();
		List<ImportSummary> summaryList = new ArrayList<>();
		
		if (!file.isEmpty()) {

			String xmlPath = "classpath:invoice.xml";

			Resource config = resourceLoader.getResource(xmlPath);

			ReaderConfig.getInstance().setSkipErrors(true);
			try {

				XLSReader xlsReader = ReaderBuilder.buildFromXML(config.getInputStream());
				Map<String, List<Invoice>> sheetMap = getExcelSheetMap();

				Map<String, List<Invoice>> beans = new HashMap<>();
				System.out.println("beans :" + beans);
				beans.put("invoiceList", sheetMap.get("invoiceList"));
				XLSReadStatus status = xlsReader.read(file.getInputStream(), beans);
				
				if (status.isStatusOK()) {
					Workbook workbook = WorkbookFactory.create(file.getInputStream());

					File convFile = new File("Import_Errors_" + dateFormat.format(new Date()) + ".xls");
					convFile.createNewFile();
					// System.out.println(convFile.getAbsolutePath());
					FileOutputStream fos = new FileOutputStream(convFile);
					for (String key : beans.keySet()) {

						if (isNotEmpty(sheetMap.get(key)) && sheetMap.get(key).size() > 0) {
							
							ImportSummary summary = new ImportSummary();
							if (sheetMap.containsKey(key)) {
								if (key.equals("invoiceList")) {
									summary.setName("Invoices");
								}else if (key.equals("invoiceList")) {
									summary.setName("Invoices");
								}
								Sheet datatypeSheet = workbook.getSheet(summary.getName());
								CellStyle style = workbook.createCellStyle();
								Font font = workbook.createFont();
								font.setColor(IndexedColors.RED.getIndex());
								style.setFont(font);
								Cell cell = datatypeSheet.getRow(1).createCell(datatypeSheet.getRow(1).getLastCellNum());
								cell.setCellValue("Errors");
								cell.setCellStyle(style);
								int lastcolumn = datatypeSheet.getRow(1).getLastCellNum() - 1;

								long failed = 0;

								List<Invoice> invoices = beans.get(key);
								List<Invoice> filteredList = new ArrayList<>();

								if (isNotEmpty(invoices)) {
									int index = 2;
									summary.setTotal(beans.get(key).size() - 1);
									List<Row> errorRows = new ArrayList<>();

									for (Invoice invoice : invoices) {
										if (!invoice.getInum().equalsIgnoreCase("Invoice No*")) {
											StringBuilder errorVal = new StringBuilder();

											int errorCount = 0;
											if (isNotEmpty(datatypeSheet) && isNotEmpty(datatypeSheet.getRow(index))) {
												if (isEmpty(invoice.getInum())) {
													errorCount++;
													errorVal.append("Invoice Number is Empty");
												}
												if (isEmpty(invoice.getStrDate())) {
													if(isNotEmpty(errorVal.toString())){
														errorVal.append(", ");
													}
													errorCount++;
													errorVal.append("Invoice Date is Empty");
												}
												if (isEmpty(invoice.getBilledtoname())) {
													if(isNotEmpty(errorVal.toString())){
														errorVal.append(", ");
													}
													errorCount++;
													errorVal.append("Customer Name is Empty");
												}
												if (isEmpty(invoice.getStatename())) {
													if(isNotEmpty(errorVal.toString())){
														errorVal.append(", ");
													}
													errorCount++;
													errorVal.append("State Name is Empty");
												}
												if (isEmpty(invoice.getQuantity())) {
													invoice.setQuantity(1d);
												}
												if (isEmpty(invoice.getDiscount())) {
													invoice.setDiscount(0d);
												}
											}
											System.out.println("index val ::" + index);
											if (errorCount != 0) {
												failed++;
												Cell errors = datatypeSheet.getRow(index).createCell(lastcolumn);
												errors.setCellValue(errorVal.toString());
												errors.setCellStyle(style);
												errorRows.add(datatypeSheet.getRow(index));
												System.out.println("Failure Invoice :" + invoice);
											} else {
												System.out.println("Success Invoice :" + invoice);
												filteredList.add(invoice);
											}
											
											summary.setSuccess((beans.get(key).size() - 1) - failed);
											
											if (!errorRows.contains(datatypeSheet.getRow(index))) {
												if (isNotEmpty(datatypeSheet) && isNotEmpty(datatypeSheet.getRow(index))) {
													System.out.println("row removed ::"+datatypeSheet.getRow(index).getRowNum());
													datatypeSheet.removeRow(datatypeSheet.getRow(index));
												}
											}
											
											index++;
										}
									}
									
									for (int i = 1; i < datatypeSheet.getLastRowNum(); i++) {
										if (isEmpty(datatypeSheet.getRow(i)) || (isEmpty(datatypeSheet.getRow(i).getCell(0)) && isEmpty(datatypeSheet.getRow(i).getCell(1)))) {
											datatypeSheet.shiftRows(i + 1, datatypeSheet.getLastRowNum(), -1);
										}
									}
									beans.put(key, filteredList);
								}
								summary.setFailed((failed));
								System.out.println(summary);
								summaryList.add(summary);
								
								Integer count = invoiceService.saveInvoices(filteredList);
								System.out.println("saves invoices count ::" + count);
							}
						}
					}
					
					try {
						workbook.write(fos);
						fos.flush();
						fos.close();
					} catch (IOException e) {
						convFile = null;
					}
					if (isNotEmpty(summaryList)) {
						int failedCount = 0;
						for (ImportSummary summary : summaryList) {
							if (summary.getFailed() > 0) {
								failedCount += summary.getFailed();
							}
						}
						if (failedCount == 0 && isNotEmpty(convFile)) {

							try {
								convFile.delete();
							} catch (Exception e) {
							}
						}
					}
				}

			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Sorry..!!! We have noticed changes in the Template Parameters, please download latest template and re-import invoices.");
				e.printStackTrace();
			}
		}
	}

	private Map<String, List<Invoice>> getExcelSheetMap() {
		Map<String, List<Invoice>> sheetMap = new HashMap<>();
		List<Invoice> invoiceList = new ArrayList<>();
		sheetMap.put("invoiceList", invoiceList);

		return sheetMap;
	}

}
