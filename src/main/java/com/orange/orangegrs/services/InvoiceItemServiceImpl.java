package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.InvoiceItems;
import com.orange.orangegrs.repositories.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
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
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        Timestamp startDate = Timestamp.valueOf(sixMonthsAgo.atStartOfDay());
        return this.invoiceItemRepository.derniereFactureReel(siteId);
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
}
