package model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.NumberToTextConverter;

public class CellType {

    public Integer getValueCellByType(Cell cell) {
        return Integer.parseInt(processCell(cell));
    }
    public String processCell(Cell cell) {
        String result = "";
        switch (cell.getCellType()) {
            case STRING:
                result = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = cell.getLocalDateTimeCellValue().toString();
                } else {
                    result = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                result = Boolean.toString(cell.getBooleanCellValue());
                break;
            default:
                break;
        }
        return result;
    }
}
