package com.example.service;

import com.example.repository.AssetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProcurementService {

    private final AssetRepository assetRepository;

    public ProcurementService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Transactional
    public void purchaseAsset(String name, double cost) {
        double currentBudget = assetRepository.getRemainingBudget();

        if (cost > currentBudget) {
            throw new RuntimeException("Inaccessible Funds! Asset cost exceeds remaining balance.");
        }

        assetRepository.updateBudget(currentBudget - cost);
        assetRepository.saveAsset(name, cost);
    }
}
