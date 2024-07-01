package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.InvoiceItems;
import com.orange.orangegrs.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItems, Long> {
    @Query("SELECT i FROM InvoiceItems i WHERE i.siteId.siteId = :siteId order by i.itemDate desc limit 6")
    List<InvoiceItems> findInvoiceBySite(int siteId);


    @Query(value = "SELECT COUNT(*) FROM InvoiceItems i JOIN Invoices c ON i.InvoiceId = c.InvoiceId WHERE i.SiteId = :siteId AND i.ItemType = 1  AND DATEDIFF(MONTH, CAST(CAST(c.Year AS VARCHAR(4)) + '-' + CAST(c.Month AS VARCHAR(2)) + '-01' AS DATE), GETDATE()) <= 6" , nativeQuery = true)
    int derniereFactureReel(int siteId);


    List<InvoiceItems> findAll();


    List<InvoiceItems> findByInvoiceInvoiceId(int invoiceId);


    @Query(value = "SELECT top 1 d.Description FROM Districts d, InvoiceItems i where d.DistrictId = i.DistrictCode and i.SiteId = :siteId", nativeQuery = true)
    String getDestrictSiteFromInvoiceItem(int siteId);




    @Query("SELECT v FROM InvoiceItems v where v.itemDate between :startDate and :endDate and v.siteId.siteId = :siteId order by v.itemDate ASC ")
    List<InvoiceItems> findByItemDateBetweenAndSiteId(Date endDate, Date startDate, int siteId);

    @Query("SELECT v FROM InvoiceItems v WHERE v.itemDate < :startDate AND v.siteId.siteId = :siteId ORDER BY v.itemDate DESC limit 1")
    InvoiceItems findLastInvoiceBefore(Date startDate, int siteId);


    @Query("SELECT v FROM InvoiceItems v WHERE v.itemDate > :endDate AND v.siteId.siteId = :siteId ORDER BY v.itemDate ASC limit 1")
    InvoiceItems findLastInvoiceAfter(Date endDate, int siteId);


    @Query("SELECT SUM(iv.consumptionKwh) as consommationKwh, SUM(iv.finalSale) as consommationTnd, v.month, v.year " +
            "FROM InvoiceItems iv JOIN iv.invoice v " +
            "WHERE iv.itemDate > :startDate " +
            "GROUP BY v.month, v.year " +
            "ORDER BY v.year DESC, v.month DESC")
    List<Object[]> findConsumptionSummaryForLastSixMonths(@Param("startDate") Date startDate);



    @Query("SELECT SUM(iv.consumptionKwh) as consommationKwh, SUM(iv.finalSale) as consommationTnd, s.elecTypeId FROM InvoiceItems iv JOIN iv.invoice v JOIN iv.siteId s WHERE iv.itemDate > :startDate " +
            "GROUP BY s.elecTypeId")
    List<Object[]> findConsumptionWithTypeSiteForLastSixMonths(@Param("startDate") Date startDate);


    @Query("SELECT SUM(iv.consumptionKwh) as consommationKwh, SUM(iv.finalSale) as consommationTnd FROM InvoiceItems iv JOIN iv.invoice v WHERE v.month >= 1 and v.year >= :startYear")
    List<Object[]> findConsumptionDepuisPremierJanvier(@Param("startYear") int startYear);
}
