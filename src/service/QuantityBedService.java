package service;


import dao.QuantityBedDao;
import dto.CategoryRoomDto;
import dto.QuantityBedDto;
import entity.CategoryRoom;
import entity.QuantityBed;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class QuantityBedService {
    private static final QuantityBedService INSTANCE = new QuantityBedService();

    private final QuantityBedDao quantityBedDao = QuantityBedDao.getInstance();

    public List<QuantityBedDto> findAll() {
        return quantityBedDao.findAll().stream()
                .map(quantityBed -> QuantityBedDto.builder()
                        //        .map(quantityBed -> QuantityBedDto.builder()
                        .id(quantityBed.getId())
                        .capacity(quantityBed.getCapacity())
                        .build())
                .collect(toList());

    }

    public List<QuantityBedDto> findById(int id) {
        return quantityBedDao.findById(id).stream()
                .map(categoryRoom -> QuantityBedDto.builder()
                        .id(categoryRoom.getId())
                        .capacity(categoryRoom.getCapacity())
                        .build())
                .collect(toList());

    }

    public void updateQuantityBed(int id, int capacity) {
        var quantity = quantityBedDao.findById(id);
        quantity.ifPresent(quantityBed -> {
            quantityBed.setCapacity(capacity);
            quantityBedDao.update(quantityBed);
        });
    }
    public void saveQuantityBed(int capacity) {
        var quantityBed = QuantityBed.builder()
                .capacity(capacity)
                .build();
        quantityBedDao.save(quantityBed);
    }
    public boolean delete(int id) {
        return quantityBedDao.delete(id);

    }

    public static QuantityBedService getInstance() {
        return INSTANCE;
    }
}