package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.InvoiceItems;

import java.util.List;

public interface InvoiceItemService {
    List<InvoiceItems> findInvoiceItemsBySiteId(int siteId);

    int derniereFactureReel(int siteId);


    List<InvoiceItems> getAllInvoiceItems();


    List<InvoiceItems> findByInvoiceInvoiceId(int invoiceId);

    String getDestrictSiteFromInvoiceItem(int siteId);
}
