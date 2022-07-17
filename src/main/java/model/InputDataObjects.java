package model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class InputDataObjects extends CellType {

    private final String address;
    private final String tankName;

    public InputDataObjects(Workbook wb) {
        this.address = processCell(wb.getSheetAt(0).getRow(0).getCell(1));
        this.tankName = processCell(wb.getSheetAt(0).getRow(1).getCell(1));
    }

    public InputData getFullPoint(Row row) {
        return new InputData.InputDataBuilder(1,address, getValueCellByType(row.getCell(0)))
                                                        .point(getValueCellByType(row.getCell(1)))
                                                        .controlPoint(getValueCellByType(row.getCell(2)))
                                                        .transition(getValueCellByType(row.getCell(3)))
                                                        .oldDeviation(getValueCellByType(row.getCell(4)))
                                                        .build();
    }

    public InputData getOnlyFullPoint(Row row) {
        return new InputData.InputDataBuilder(2, address, getValueCellByType(row.getCell(0)))
                                                        .point(getValueCellByType(row.getCell(1)))
                                                        .controlPoint(getValueCellByType(row.getCell(2)))
                                                        .transition(getValueCellByType(row.getCell(3)))
                                                        .build();
    }

    public InputData getPoint(Row row) {
        return new InputData.InputDataBuilder(3, address, getValueCellByType(row.getCell(0)))
                                                        .point(getValueCellByType(row.getCell(1)))
                                                        .transition(getValueCellByType(row.getCell(3)))
                                                        .oldDeviation(getValueCellByType(row.getCell(4)))
                                                        .build();
    }

    public InputData getOnlyPoint(Row row) {
        return new InputData.InputDataBuilder(4, address, getValueCellByType(row.getCell(0)))
                                                        .point(getValueCellByType(row.getCell(1)))
                                                        .transition(getValueCellByType(row.getCell(3)))
                                                        .build();
    }

    public InputData getControlPoint(Row row) {
        return new InputData.InputDataBuilder(5, address, getValueCellByType(row.getCell(0)))
                .controlPoint(getValueCellByType(row.getCell(2)))
                .transition(getValueCellByType(row.getCell(3)))
                .build();
    }

    public String returnKey() {
        return address + " " + tankName;
    }


}
