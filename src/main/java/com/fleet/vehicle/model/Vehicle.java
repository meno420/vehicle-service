package com.fleet.vehicle.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    private String id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String plate;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double batteryStatusPercentage;

    private String engineStatus;
    private Double lastLatitude;
    private Double lastLongitude;
    private String lastMaintenanceDate;

    public Vehicle() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPlate() { return plate; }
    public void setPlate(String plate) { this.plate = plate; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Double getBatteryStatusPercentage() { return batteryStatusPercentage; }
    public void setBatteryStatusPercentage(Double batteryStatusPercentage) { this.batteryStatusPercentage = batteryStatusPercentage; }

    public String getEngineStatus() { return engineStatus; }
    public void setEngineStatus(String engineStatus) { this.engineStatus = engineStatus; }

    public Double getLastLatitude() { return lastLatitude; }
    public void setLastLatitude(Double lastLatitude) { this.lastLatitude = lastLatitude; }

    public Double getLastLongitude() { return lastLongitude; }
    public void setLastLongitude(Double lastLongitude) { this.lastLongitude = lastLongitude; }

    public String getLastMaintenanceDate() { return lastMaintenanceDate; }
    public void setLastMaintenanceDate(String lastMaintenanceDate) { this.lastMaintenanceDate = lastMaintenanceDate; }
}
