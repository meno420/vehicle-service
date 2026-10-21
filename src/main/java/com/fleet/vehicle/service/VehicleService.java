package com.fleet.vehicle.service;

import com.fleet.vehicle.model.Vehicle;
import com.fleet.vehicle.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    public Vehicle findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehículo no encontrado: " + id));
    }

    public Vehicle create(Vehicle vehicle) {
        if (vehicle.getId() == null || vehicle.getId().isBlank()) {
            vehicle.setId("veh-" + UUID.randomUUID().toString().substring(0, 8));
        } else if (repository.existsById(vehicle.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existe un vehículo con id: " + vehicle.getId());
        }
        return repository.save(vehicle);
    }
}
