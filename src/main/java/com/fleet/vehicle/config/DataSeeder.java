package com.fleet.vehicle.config;

import com.fleet.vehicle.model.Vehicle;
import com.fleet.vehicle.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedVehicles(VehicleRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Vehicle v = new Vehicle();
                v.setId("veh-001");
                v.setPlate("ABC-1234");
                v.setModel("EcoTruck EV");
                v.setBatteryStatusPercentage(84.5);
                v.setEngineStatus("OK");
                v.setLastLatitude(28.6353);
                v.setLastLongitude(-106.0889);
                v.setLastMaintenanceDate("2026-09-01");
                repository.save(v);
            }
        };
    }
}
