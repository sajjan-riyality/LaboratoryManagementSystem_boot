package com.lab.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.dto.InventoryDTO;
import com.lab.entity.Inventory;
import com.lab.repository.InventoryRepository;
import com.lab.service.InventoryService;
import com.lab.util.MapperUtil;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public InventoryDTO addItem(InventoryDTO dto) {
        Inventory entity = mapperUtil.toEntity(dto, Inventory.class);
        Inventory saved = inventoryRepository.save(entity);
        return mapperUtil.toDTO(saved, InventoryDTO.class);
    }

    @Override
    public InventoryDTO updateItem(Long id, InventoryDTO dto) {
        Inventory item = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setItemName(dto.getItemName());
        item.setQuantity(dto.getQuantity());
        item.setUnit(dto.getUnit());
        item.setCategory(dto.getCategory());
        item.setExpiryDate(dto.getExpiryDate());
        item.setLowStockAlert(dto.getLowStockAlert());

        return mapperUtil.toDTO(inventoryRepository.save(item), InventoryDTO.class);
    }

    @Override
    public void deleteItem(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public InventoryDTO getItem(Long id) {
        Inventory item = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        return mapperUtil.toDTO(item, InventoryDTO.class);
    }

    @Override
    public List<InventoryDTO> getAllItems() {
        return inventoryRepository.findAll()
                .stream()
                .map(i -> mapperUtil.toDTO(i, InventoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDTO> getByCategory(String category) {
        return inventoryRepository.findByCategory(category)
                .stream()
                .map(i -> mapperUtil.toDTO(i, InventoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDTO> getLowStockItems() {
        return inventoryRepository.findAll()
                .stream()
                .filter(i -> i.getQuantity() <= i.getLowStockAlert())
                .map(i -> mapperUtil.toDTO(i, InventoryDTO.class))
                .collect(Collectors.toList());
    }
}
