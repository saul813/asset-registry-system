package com.example.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class AssetRepository {

    private final JdbcTemplate jdbcTemplate;

    public AssetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public double getRemainingBudget() {
        String sql = "SELECT remaining_balance FROM institution_budget WHERE id = 1";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public void updateBudget(double newBalance) {
        String sql = "UPDATE institution_budget SET remaining_balance = ? WHERE id = 1";
        jdbcTemplate.update(sql, newBalance);
    }

    public void saveAsset(String name, double cost) {
        String sql = "INSERT INTO assets (name, cost) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, cost);
    }

    public List<Map<String, Object>> findAllAssets() {
        return jdbcTemplate.queryForList("SELECT * FROM assets ORDER BY id DESC");
    }
}
