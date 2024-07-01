package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.InvoiceItems;
import com.orange.orangegrs.repositories.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService{

    @Autowired
    InvoiceItemRepository invoiceItemRepository;


    @Override
    public List<InvoiceItems> findInvoiceItemsBySiteId(int siteId) {
        return this.invoiceItemRepository.findInvoiceBySite(siteId);
    }

    @Override
    public int derniereFactureReel(int siteId) {
        List<InvoiceItems> invoices = this.invoiceItemRepository.findInvoiceBySite(siteId);
        int invoiceReal = 0;
        for (InvoiceItems v : invoices){
            if(v.getItemType() == 1){
                invoiceReal++;
            }
        }
        return invoiceReal;
    }

    @Override
    public List<InvoiceItems> getAllInvoiceItems() {
        return this.invoiceItemRepository.findAll();
    }

    @Override
    public List<InvoiceItems> findByInvoiceInvoiceId(int invoiceId) {
        return this.invoiceItemRepository.findByInvoiceInvoiceId(invoiceId);
    }

    @Override
    public String getDestrictSiteFromInvoiceItem(int siteId) {
        return this.invoiceItemRepository.getDestrictSiteFromInvoiceItem(siteId);
    }

    @Override
    public List<InvoiceItems> getInvoiceBetweenTwoVisit(Date endDate, Date startDate, int siteId) {
        /*return this.invoiceItemRepository.findInvoiceItemsByItemDateBetweenMonths(
                siteId
        );*/
        return this.invoiceItemRepository.findByItemDateBetweenAndSiteId(
             endDate,
             startDate,
             siteId
        );
    }

    @Override
    public InvoiceItems findLastInvoiceBefore(Date startDate, int siteId) {
        return this.invoiceItemRepository.findLastInvoiceBefore(startDate,siteId);
    }

    @Override
    public InvoiceItems findLastInvoiceAfter(Date endDate, int siteId) {
        return this.invoiceItemRepository.findLastInvoiceAfter(endDate, siteId);
    }
}
