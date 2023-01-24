package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.Companies;
import com.schoolProject.schoolProject.model.Discriminations;
import com.schoolProject.schoolProject.model.Fuel;
import com.schoolProject.schoolProject.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    Stock findByStockId(String stockId);

    @Query("SELECT S FROM Stock S WHERE S.companies=?1 and S.fuel=?1 and S.discriminations=?1")
    Stock findStock(Companies companies, Fuel fuel, Discriminations discriminations);

    @Query("SELECT S.quantity FROM Stock S WHERE S.companies =?1 and S.fuel=?1 and S.discriminations=?1")
    double getQuantity(Companies companies, Fuel fuel, Discriminations discriminations);

    @Modifying
    @Query("update Stock S set S.quantity = :quantity where S.companies = :companies and" +
            " S.fuel= :fuel and S.discriminations= :discriminations")
    void updateQuantity(@Param(value = "companies")Companies companies,
                        @Param(value = "fuel")Fuel fuel,
                        @Param(value = "discriminations")Discriminations discriminations,
                        @Param(value ="quantity")double quantity);


}
