package backend;

import model.InputData;
import model.InputDataObjects;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class DataInput {
    private final List<File> fileName = new ArrayList<>();
    protected CalculationRepositoryInterface repository;

    public DataInput(CalculationRepositoryInterface repository) {
        this.repository = repository;
    }

    public void addFileName() {
        String directory = "Data";
        File dir = new File(directory);
        fileName.addAll(Arrays.asList(Objects.requireNonNull(dir.listFiles())));
    }

    public void readFromFile() {
        for (File path : fileName) {
            Workbook wb = loadWorkbook(path);
            InputDataObjects inputObjects = new InputDataObjects(wb);
            LinkedList<InputData> pointList = new LinkedList<>();
            for (Row row : wb.getSheetAt(0)) {
                if (row.getRowNum() > 3) {
                    if (row.getCell(4) == null || row.getCell(4).getCellType() == CellType.BLANK) {
                        if (row.getCell(2) == null || row.getCell(2).getCellType() == CellType.BLANK) {
                            pointList.addLast(inputObjects.getOnlyPoint(row));
                        } else {
                            if (row.getCell(1) == null || row.getCell(1).getCellType() == CellType.BLANK) {
                                pointList.addLast(inputObjects.getControlPoint(row));
                            } else {
                                pointList.addLast(inputObjects.getOnlyFullPoint(row));
                            }
                        }
                    } else {
                        if (row.getCell(2) == null || row.getCell(2).getCellType() == CellType.BLANK) {
                            pointList.addLast(inputObjects.getPoint(row));
                        } else {
                            pointList.addLast(inputObjects.getFullPoint(row));
                        }
                    }
                }
            }
            this.repository.addInputData(inputObjects.returnKey(), pointList);
        }
    }

    private Workbook loadWorkbook(File path) {
        var extension = path.getName().substring(path.getName().lastIndexOf(".") + 1).toLowerCase();
        try (BufferedInputStream file = new BufferedInputStream(new FileInputStream(path))) {
            switch (extension) {
                case "xls":
                    return new HSSFWorkbook(file);
                case "xlsx":
                    return new XSSFWorkbook(file);
                default:
                    throw new RuntimeException("Неизвестный файл Excel: " + extension);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
