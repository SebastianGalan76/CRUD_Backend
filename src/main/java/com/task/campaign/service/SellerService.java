package com.task.campaign.service;

import com.task.campaign.database.model.Seller;
import com.task.campaign.database.repository.SellerRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class SellerService {
    final SellerRepository sellerRepository;

    //Basic function to get a seller account from the repository.
    public Seller getLoggedInSeller(){
        return sellerRepository.getReferenceById(1L);
    }

    public void save(Seller seller) {
        sellerRepository.save(seller);
    }
}
