package com.rafsan.inventory.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.rafsan.inventory.entity.Item;
import com.rafsan.inventory.entity.Supplier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrintInvoice {

	private final ObservableList<Item> items;
	private final String barcode;
	private final String invoiceId;
	private final Supplier supplier;
	private double grandTotal;


	public PrintInvoice(ObservableList<Item> items, String barcode, String invoiceId, Supplier supplier) {
		this.items = FXCollections.observableArrayList(items);
		this.barcode = barcode;
		this.invoiceId = invoiceId;
		this.supplier = supplier;
	}

	public String generateReport() {

		try (FileOutputStream fs = new FileOutputStream("C:/Invoice/Invoice_"+invoiceId+".pdf");){
			Document document = new Document(PageSize.A4);
			File directory = new File("c://Invoice");
			if(!directory.exists()) {
				directory.mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, fs);
			document.open();
			PdfPTable table = new PdfPTable(1);
			
			table.addCell(getStoreName());

			table.addCell(getEmptyRow());

			table.addCell(getSingleRowWithData("Tax Invoice"));

			table.addCell(getInvoiceMetadata());

			table.addCell(getEmptyRow());

			table.addCell(getAddressData());

			table.addCell(getEmptyRow());

			table.addCell(getProductDetail());

			table.addCell(getEmptyRow());

			table.addCell(getGrandTotal());

			table.addCell(getEmptyRow());

			table.addCell(getFooterDetail());

			document.add(table);

			document.close();
		} catch (IOException | DocumentException ex) {
			System.out.println(ex.getMessage());
		}

		return "C:/Invoice/Invoice_"+invoiceId+".pdf";
	}

	private PdfPCell getStoreName(){
		Image img = null;
		try {
			img = Image.getInstance("c:/logo.png");
			img.scaleAbsolute(40f, 20f);
		}catch (Exception e) {
		}
		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		Phrase p = new Phrase();
		p.add(new Chunk(img, 0, 0, true));
		p.add(new Chunk("\n"));
		p.add(new Chunk("SREE SIVA SAKTHI ENTERPRISES \n",new Font(FontFamily.HELVETICA, 15, Font.BOLD)));
		p.add(new Chunk("KRISHNA NAGAR, ARIKALAPADI, ARAKKONAM Tk., Vellore Dist \n", new Font(FontFamily.HELVETICA, 11, Font.BOLD)));
		p.add(new Chunk("Mobile : +91 94442 78847 / Email : sssenterpriseram@gmail.com \n",new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
		p.add(new Chunk("GSTIN : 33AOCPR1669C2Z4",new Font(FontFamily.HELVETICA, 10, Font.BOLD)));

		pdfPCell.setPhrase(p);

		return pdfPCell;
	}

	private PdfPCell getEmptyRow() {
		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.setFixedHeight(6f);
		return pdfPCell;
	}


	private PdfPCell getSingleRowWithData(String text) {
		PdfPCell pdfPCell = new PdfPCell(new Phrase(text, new Font(FontFamily.HELVETICA, 12, Font.BOLD)));
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		return pdfPCell;
	}

	private PdfPTable getInvoiceMetadata() {
		PdfPTable table = new PdfPTable(2);

		Font headerFont = new Font(FontFamily.HELVETICA, 8, Font.BOLD);

		Font dataFont = new Font(FontFamily.HELVETICA, 8);

		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		Phrase p = new Phrase();
		p.add(new Chunk("Invoice No : ", headerFont));
		p.add(new Chunk(this.invoiceId,dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Transport Mode : ",headerFont));
		p.add(new Chunk(" -- ",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Invoice Date : ",headerFont));
		p.add(new Chunk(getDate(),dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);


		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Vehicle Number : ",headerFont));
		p.add(new Chunk(" -- ",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Reverse Charge (Y/N) : ",headerFont));
		p.add(new Chunk("--",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Date of Supply : ",headerFont));
		p.add(new Chunk(getDate(),dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("State : ",headerFont));
		p.add(new Chunk("TAMILNADU",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Place of Supply : ",headerFont));
		p.add(new Chunk("TAMILNADU",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		return table;
	}

	private PdfPTable getAddressData() {
		PdfPTable table = new PdfPTable(2);

		Font headerFont = new Font(FontFamily.HELVETICA, 8, Font.BOLD);

		Font dataFont = new Font(FontFamily.HELVETICA, 8);

		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		Phrase p = new Phrase();
		p.add(new Chunk("Billing Address",headerFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);


		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		p = new Phrase();
		p.add(new Chunk("Shipping Address",headerFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Name : ",headerFont));
		p.add(new Chunk(this.supplier.getName(),dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Name : ",headerFont));
		p.add(new Chunk(this.supplier.getName(),dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Address : ",headerFont));
		p.add(new Chunk(this.supplier.getAddress(),dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Address : ",headerFont));
		p.add(new Chunk(this.supplier.getAddress(),dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);


		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("GSTIN : ",headerFont));
		p.add(new Chunk(this.supplier.getGstnnumber(),dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);


		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("GSTIN : ",headerFont));
		p.add(new Chunk(this.supplier.getGstnnumber(),dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);


		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("State : ",headerFont));
		p.add(new Chunk("TAMILNADU",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("State : ",headerFont));
		p.add(new Chunk("TAMILNADU",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		return table;
	}


	private PdfPTable getProductDetail() {

		PdfPTable table = new PdfPTable(10);


		try {
			table.setWidths(new float[]{0.3f, 1.0f, 0.5f, 0.3f, 0.4f, 0.3f, 0.4f, 0.3f, 0.4f, 0.5f});
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		List<String> headerList = Arrays.asList("S.no", "Description of Goods", "HSN/SAC Code", "Qty", "MRP",
				"CGST %", "CGST amount", "SGST %", "SGST amount", "Amount");

		createProductHeader(table, headerList);


		PdfPCell pdfPCell = null;

		Phrase p = null;

		Font font = new Font(FontFamily.HELVETICA, 8);

		int itemCount = 1;

		for (Item item : items) {

			pdfPCell = new PdfPCell();
			pdfPCell.setFixedHeight(20f);
			pdfPCell.setBorder(Rectangle.RIGHT);
			p = new Phrase();
			p.add(new Chunk(""+itemCount, font));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);


			pdfPCell = new PdfPCell();
			pdfPCell.setBorder(Rectangle.RIGHT);
			p = new Phrase();
			p.add(new Chunk(item.getItemName(), font));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);

			pdfPCell = new PdfPCell();
			pdfPCell.setBorder(Rectangle.RIGHT);
			p = new Phrase();
			p.add(new Chunk(""+item.getHsncode(), font));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);

			pdfPCell = new PdfPCell();
			pdfPCell.setBorder(Rectangle.RIGHT);
			p = new Phrase();
			p.add(new Chunk(new DecimalFormat("").format(item.getQuantity()), font));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);

			pdfPCell = new PdfPCell();
			pdfPCell.setBorder(Rectangle.RIGHT);
			p = new Phrase();
			p.add(new Chunk(""+item.getUnitPrice(), font));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);

			pdfPCell = new PdfPCell();
			pdfPCell.setBorder(Rectangle.RIGHT);
			p = new Phrase();
			p.add(new Chunk(""+item.getCgstPer(), font));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);

			pdfPCell = new PdfPCell();
			pdfPCell.setBorder(Rectangle.RIGHT);
			p = new Phrase();
			p.add(new Chunk(""+item.getCgst(), font));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);

			pdfPCell = new PdfPCell();
			pdfPCell.setBorder(Rectangle.RIGHT);
			p = new Phrase();
			p.add(new Chunk(""+item.getSgstPer(), font));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);

			pdfPCell = new PdfPCell();
			pdfPCell.setBorder(Rectangle.RIGHT);
			p = new Phrase();
			p.add(new Chunk(""+item.getSgst(), font));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);

			pdfPCell = new PdfPCell();
			pdfPCell.setBorder(Rectangle.RIGHT);
			p = new Phrase();
			p.add(new Chunk(""+item.getTotal(), font));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);

			itemCount++;
			grandTotal = grandTotal+item.getTotal();
		}


		if(itemCount<=15) {
			for(int i = 1; i<=10; i++) {
				float space = 315-(itemCount*20);
				pdfPCell = new PdfPCell();
				pdfPCell.setBorder(Rectangle.RIGHT);
				pdfPCell.setFixedHeight(space);
				table.addCell(pdfPCell);
			}
		}



		return table;
	}



	private void createProductHeader(PdfPTable table, List<String> headerList) {

		PdfPCell pdfPCell = null;
		Phrase p = null;
		for(String headerName : headerList) {
			pdfPCell = new PdfPCell();
			pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			p = new Phrase();
			p.add(new Chunk(headerName,new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			pdfPCell.setPhrase(p);
			table.addCell(pdfPCell);
		}
	}

	private PdfPTable getGrandTotal() {

		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new float[]{2.3f, 0.5f});
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD); 

		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Phrase p = new Phrase();
		p.add(new Chunk("Total ",font));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		p = new Phrase();
		p.add(new Chunk(new DecimalFormat(".##").format(grandTotal),font));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		int n = (int) Math.round(grandTotal);
		float s = n;
		float roundoff = (float) (s-grandTotal);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		p = new Phrase();
		p.add(new Chunk("Round off ",font));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		String symbol = "(+) ";

		if(Math.signum(roundoff) == -1.0)
			symbol = "(-) ";

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		p = new Phrase();
		p.add(new Chunk(symbol + "0"+new DecimalFormat(".##").format(Math.abs(roundoff)),font));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);


		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		p = new Phrase();
		p.add(new Chunk("Grand Total ",font));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		p = new Phrase();
		p.add(new Chunk(""+n,font));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);


		String grandTotalWords = getGrandTotalWords(grandTotal);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		p = new Phrase();
		p.add(new Chunk(grandTotalWords,font));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		p = new Phrase();
		p.add(new Chunk(""));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		return table;
	}


	private PdfPTable getFooterDetail() {

		PdfPTable table = new PdfPTable(3);
		try {
			//table.setWidths(new int[]{2, 1, 1});
			table.setWidths(new float[]{1.5f, 0.9f, 1.2f});
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD);

		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.addElement(getBankDetailandTerms());
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pdfPCell.setVerticalAlignment(Element.ALIGN_LEFT);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		Phrase p = new Phrase();
		p.add(new Chunk("\n\n\n\n\n\n\n\n\n\n\n"));
		p.add(new Chunk("Common Seal",new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);	 

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		p = new Phrase();
		p.add(new Chunk("\n\n\nCertified that the particulars given above are true and correct\n\n\n\n\n\n\n", new Font(FontFamily.HELVETICA, 7)));
		p.add(new Chunk("For Sree Siva Sakthi Enterprises\n",font));
		p.add(new Chunk("\n\n\n\n"));
		p.add(new Chunk("Authorised signatory",font));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		return table;
	}

	private PdfPTable getBankDetailandTerms() {

		PdfPTable table = new PdfPTable(1);

		Font headerFont = new Font(FontFamily.HELVETICA, 8, Font.BOLD);

		Font dataFont = new Font(FontFamily.HELVETICA, 8, Font.BOLD);


		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		Phrase p = new Phrase();
		p.add(new Chunk("Bank Details : ",headerFont));
		p.add(new Chunk("SREE SIVA SAKTHI ENTERPRISES",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Banker : ",headerFont));
		p.add(new Chunk("TMB Ltd.,",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Bank A/C : ",headerFont));
		p.add(new Chunk("193150050800264",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Bank IFSC : ",headerFont));
		p.add(new Chunk("TMBL0000193",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Branch : ",headerFont));
		p.add(new Chunk("Arakkonam",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("MICR Code : ",headerFont));
		p.add(new Chunk("631060102",dataFont));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		p = new Phrase();
		p.add(new Chunk("Terms & Conditions \n\n",headerFont));
		p.add(new Chunk("1. Goods sold once will not be taken back.\n\n",new Font(FontFamily.HELVETICA, 7)));
		p.add(new Chunk("2. Seller is not responsible for any loss or damages of goods in transit.\n\n",new Font(FontFamily.HELVETICA, 7)));
		p.add(new Chunk("3. Subject to Arakkonam Jurisdiction",new Font(FontFamily.HELVETICA, 7)));
		pdfPCell.setPhrase(p);
		table.addCell(pdfPCell);

		return table;
	}

	private String getDate() {
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		return formatter.format(date);
	}

	private String getGrandTotalWords(double grandTotal) {
		StringBuilder sb = new StringBuilder("Rupees ");
		int n = (int) Math.round(grandTotal);
		if (n <= 0)
		{
			System.out.println("Enter numbers greater than 0");
		}
		else
		{
			sb.append(pw((n / 1000000000), "Hundred "));
			sb.append(pw((n / 10000000) % 100, "Crore "));
			sb.append(pw(((n / 100000) % 100), "Lakh "));
			sb.append(pw(((n / 1000) % 100), "Thousand "));
			sb.append(pw(((n / 100) % 10), "Hundred "));
			sb.append(pw((n % 100), " "));
		}

		if(sb.toString().trim().length() > 6) {
			sb.append("Only ");
		}

		return sb.toString();
	}

	private String pw(int n, String ch)
	{
		StringBuilder sb = new StringBuilder();

		String one[] = { "", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ",
				"Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ",
		"Nineteen " };

		String ten[] = { "", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety " };

		if (n > 19)
		{
			sb.append((ten[n / 10] + "" + one[n % 10]));
		}
		else
		{
			sb.append(one[n]);
		}
		if (n > 0)
			sb.append(ch);
		return sb.toString();
	}
}
