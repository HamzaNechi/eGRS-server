package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.InvoiceItems;
import com.orange.orangegrs.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItems, Long> {
    @Query("SELECT i FROM InvoiceItems i WHERE i.siteId.SiteId = :siteId order by i.itemDate desc limit 6")
    List<InvoiceItems> findInvoiceBySite(int siteId);


    @Query(value = "SELECT COUNT(*) FROM InvoiceItems i JOIN Invoices c ON i.InvoiceId = c.InvoiceId WHERE i.SiteId = :siteId AND i.ItemType = 1  AND DATEDIFF(MONTH, CAST(CAST(c.Year AS VARCHAR(4)) + '-' + CAST(c.Month AS VARCHAR(2)) + '-01' AS DATE), GETDATE()) <= 6" , nativeQuery = true)
    int derniereFactureReel(int siteId);


    List<InvoiceItems> findAll();


    List<InvoiceItems> findByInvoiceInvoiceId(int invoiceId);


    @Query(value = "SELECT top 1 d.Description FROM Districts d, InvoiceItems i where d.DistrictId = i.DistrictCode and i.SiteId = :siteId", nativeQuery = true)
    String getDestrictSiteFromInvoiceItem(int siteId);
}
