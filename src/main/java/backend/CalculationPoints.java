package backend;

import model.ControlPoints;
import model.InputData;

import java.util.*;

public class CalculationPoints {

    private final GeneralMethodsInterface methods = new GeneralMethods();
    protected final FinalCalculation finalBuilder = new FinalCalculation();
    private final Map<Integer, Integer> valueByMin = new HashMap<>();
    private final LinkedList<InputData> valueList = new LinkedList<>();
    private final LinkedList<ControlPoints> neighboring = new LinkedList<>();
    private final LinkedList<ControlPoints> diagonal = new LinkedList<>();
    private int minNumber;
    private int maxNumber;

    private InputData value;
    protected CalculationRepositoryInterface repository;


    public CalculationPoints(CalculationRepositoryInterface repository) {
        this.repository = repository;
    }

    public void getFinalObjects() {
        for (String key : repository.getNewValues().keySet()) {
            for (InputData data : repository.getNewValues().get(key)) {
                value = data;
                performPointCalculation();
                if (data.getFlag() != 3 || data.getFlag() != 4 ) {
                    performControlPointCalculation();
                }
                valueList.addLast(finalBuilder.getFinalObjects());
            }
            repository.addFinalValue(key, valueList);
        }
    }

    public void performPointCalculation() {
        methods.getMinValuePoint(value.getPoint());
        finalBuilder.addObjectsPoint(value, methods.getMinValuePoint() - value.getPoint(), getPrecipitation(methods.getMinValuePoint(), value));
    }

    public void performControlPointCalculation() {
        getMinMaxNumber(value.getNumber());
        methods.getMinValueControlPoint(value.getControlPoint());
        valueByMin.put(value.getNumber(),methods.getMinValueControlPoint() - value.getControlPoint());
        calculationForNeighboringPoints();
        calculationForDiagonalPoints();
        finalBuilder.addObjectsControlPoint(value, methods.getMinValueControlPoint() - value.getControlPoint(), getString(neighboring), getString(diagonal));
    }

    private int getPrecipitation(int min, InputData val) {
        if (val.getOldDeviation() != 0) {
            return (min - val.getPoint()) - val.getOldDeviation();
        } else {
            return 0;
        }
    }

    private void calculationForNeighboringPoints() {
        int value = 0;
        int number = 0;
        for (Integer key : valueByMin.keySet()) {
            if (number != 0) {
                neighboring.addLast(new ControlPoints(getKey(number, key), Math.abs(value - valueByMin.get(key))));
            }
            number = key;
            value = valueByMin.get(key);
            neighboring.addLast(new ControlPoints(getKey(minNumber, maxNumber), Math.abs(valueByMin.get(maxNumber) - valueByMin.get(minNumber))));
        }
    }

        private void calculationForDiagonalPoints () {
            int difference = Integer.parseInt(String.valueOf(Math.floor(maxNumber / 2.0)));
            for (Integer key : valueByMin.keySet()) {
                if (key <= maxNumber - difference) {
                    diagonal.addLast(new ControlPoints(getKey(key, key + difference), Math.abs(valueByMin.get(key) - valueByMin.get(key + difference))));
                }
            }
        }

        private String getKey ( int first, int second){
            return String.join("-", String.valueOf(first), String.valueOf(second));
        }

        private String getString (LinkedList < ControlPoints > value) {
            List<String> string = new ArrayList<>();
            for (ControlPoints val : value) {
                string.add(String.join("=", val.getKey(), String.valueOf(val.getValue())));
            }
            return String.join(",", string);
        }

        private void getMinMaxNumber (int number){
            if (minNumber == 0 || maxNumber == 0) {
                if (minNumber < number) {
                    minNumber = number;
                    maxNumber = number;
                }
            } else {
                if (minNumber > number) {
                    minNumber = number;
                } else {
                    maxNumber = number;
                }
            }
        }
    }