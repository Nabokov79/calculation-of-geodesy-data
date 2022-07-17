package backend;

import model.InputData;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class ReadToFile  {

    private final CalculationRepositoryInterface repository;
    private HSSFWorkbook workbook = new HSSFWorkbook();
    private HSSFSheet sheet;
    private static final String DIR = System.getProperty("user.dir");
    private final String directory = "Результаты расчета";
    private int rowNum = 0;
    private Row row;

    private String key;
    private LinkedList<InputData> values;

    public ReadToFile(CalculationRepositoryInterface repository) {
        this.repository = repository;
    }

    public void writeToFile(CalculationRepositoryInterface repository) {
        for (String key : repository.getFinalValue().keySet()) {
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet("Просто лист");
            this.key = key;
            values = repository.getFinalValue().get(key);
            writeValue();
        }
    }

    private void writeValue() {
        int flag = 0;
        for (InputData value : values) {
            flag = value.getFlag();
        }

            switch (flag) {
                case 1:
                case 2:
                    row = sheet.createRow(rowNum);
                    writeHeaderPoint();
                    writeTypePoint();
                    ++rowNum;
                    row = sheet.createRow(rowNum);
                    writeHeaderControlPoint();
                    writeTypeControlPoint();
                    break;
                case 3:
                case 4:
                    row = sheet.createRow(rowNum);
                    writeHeaderPoint();
                    writeTypePoint();
                    break;
                case 5:
                    row = sheet.createRow(rowNum);
                    writeHeaderControlPoint();
                    writeTypeControlPoint();
                    break;
            }
            writeStart();
        }

    private void writeHeaderPoint() {
        row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Номер точки.");
        row.createCell(1).setCellValue("Значения реперов.");
        row.createCell(2).setCellValue("Отклонение от мин. значения за текущий год.");
        row.createCell(3).setCellValue("Отклонение от мин. значения за предыдущий год.");
        row.createCell(4).setCellValue("Осадка.");
        ++rowNum;
    }

    private void writeHeaderControlPoint() {
        row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Номер точки.");
        row.createCell(1).setCellValue("Значения к.т.");
        row.createCell(2).setCellValue("Отклонение от мин. значения.");
        row.createCell(3).setCellValue("Разность соседних точек.");
        row.createCell(4).setCellValue("Разность диагональных точек.");
        ++rowNum;
    }

    private void writeTypePoint() {
        for (InputData value : values) {
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(value.getNumber());
            row.createCell(1).setCellValue(value.getPoint());
            row.createCell(2).setCellValue(value.getNewDeviation());
            row.createCell(3).setCellValue(value.getOldDeviation());
            row.createCell(4).setCellValue(value.getPrecipitation());
            ++rowNum;
        }
    }

    private void writeTypeControlPoint() {
        InputData val = null;
        for (InputData value : values) {
            if (value.getNumber() == 1) {
                val = value;
            }
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(value.getNumber());
            row.createCell(1).setCellValue(value.getControlPoint());
            row.createCell(2).setCellValue(value.getControlPointDeviation());
            row.createCell(3).setCellValue(value.getNeighboring());
            row.createCell(4).setCellValue(value.getDiagonal());
            ++rowNum;
        }
        assert val != null;
        writeControlPointNumberOne(val);
    }

    private void writeControlPointNumberOne(InputData value) {
        row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(value.getNumber());
        row.createCell(1).setCellValue(value.getControlPoint());
        row.createCell(2).setCellValue(value.getControlPointDeviation());
    }

    public void writeStart() {
        try (FileOutputStream out = new FileOutputStream(createFile())) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createFile() {
        String fileName = key + ".xls";
        new File(DIR + File.separator + directory).mkdir();
        File file = new File(directory + File.separator + fileName);
        return file.toString();
    }
}
