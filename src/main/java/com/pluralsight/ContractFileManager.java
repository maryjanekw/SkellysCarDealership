package com.pluralsight;

import javax.imageio.IIOException;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    private static final String _fileName = "contracts.csv";

    public void saveContract(Contract contract){
        try (FileWriter writer = new FileWriter(_fileName, true)){
            StringBuilder builder = new StringBuilder();

            // common fields
            builder.append(contract instanceof SalesContract ? "Sale" : "Lease").append("|");
            builder.append(contract.getContractDate()).append("|");
            builder.append(contract.getCustomerName()).append("|");
            builder.append(contract.getCustomerEmail()).append("|");

            Vehicle vehicle = contract.getVehicleSold();
            builder.append(vehicle.getVin()).append("|");
            builder.append(vehicle.getYear()).append("|");
            builder.append(vehicle.getMake()).append("|");
            builder.append(vehicle.getModel()).append("|");
            builder.append(vehicle.getVehicleType()).append("|");
            builder.append(vehicle.getColor()).append("|");
            builder.append(vehicle.getOdometer()).append("|");
            builder.append(vehicle.getPrice()).append("|");

            // contract specific fields
            if (contract instanceof SalesContract){
                SalesContract salesContract = (SalesContract)  contract;
                builder.append(salesContract.getSalesTaxAmount()).append("|");
                builder.append(salesContract.getRecordingFee()).append("|");
                builder.append(salesContract.getProcessingFee()).append("|");
                builder.append(salesContract.getTotalPrice()).append("|");
                builder.append(salesContract.getFinancedAsText()).append("|");
                builder.append(salesContract.getMonthlyPayments());
            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                builder.append(leaseContract.getExpectedEndingValue()).append("|");
                builder.append(leaseContract.getLeaseFee()).append("|");
                builder.append(leaseContract.getTotalPrice()).append("|");
                builder.append(leaseContract.getMonthlyPayments());
            }
            builder.append("\n");
            writer.write(builder.toString());
        }catch (IOException ex){
            System.out.println("Error saving to file: " + ex.getMessage());
        }
    }
}
