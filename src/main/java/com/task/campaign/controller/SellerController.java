package com.task.campaign.controller;

import com.task.campaign.database.model.Seller;
import com.task.campaign.service.SellerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {
    final SellerService sellerService;

    @GetMapping
    public Seller getSeller(){
        return sellerService.getLoggedInSeller();
    }
}
