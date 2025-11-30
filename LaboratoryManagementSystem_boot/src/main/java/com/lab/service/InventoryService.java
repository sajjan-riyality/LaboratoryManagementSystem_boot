package com.lab.service;

import java.util.List;

import com.lab.dto.InventoryDTO;

public interface InventoryService {

    InventoryDTO addItem(InventoryDTO inventoryDTO);

    InventoryDTO updateItem(Long id, InventoryDTO inventoryDTO);

    void deleteItem(Long id);

    InventoryDTO getItem(Long id);

    List<InventoryDTO> getAllItems();

    List<InventoryDTO> getByCategory(String category);

    List<InventoryDTO> getLowStockItems();
}
