package com.example.controller;

import com.example.repository.AssetRepository;
import com.example.service.ProcurementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssetController {

    private final ProcurementService procurementService;
    private final AssetRepository assetRepository;

    public AssetController(ProcurementService procurementService, AssetRepository assetRepository) {
        this.procurementService = procurementService;
        this.assetRepository = assetRepository;
    }

    @GetMapping("/")
    public String showDashboard(Model model) {
        model.addAttribute("assets", assetRepository.findAllAssets());
        model.addAttribute("budget", assetRepository.getRemainingBudget());
        return "dashboard";
    }

    @PostMapping("/register-asset")
    public String registerNewAsset(@RequestParam String assetName,
                                   @RequestParam double assetCost,
                                   Model model) {
        try {
            procurementService.purchaseAsset(assetName, assetCost);
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }

        model.addAttribute("assets", assetRepository.findAllAssets());
        model.addAttribute("budget", assetRepository.getRemainingBudget());
        return "dashboard";
    }
}
