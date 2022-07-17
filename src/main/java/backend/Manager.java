package backend;

public class Manager {

    private final CalculationRepositoryInterface repository = new CalculationRepository();
    private final DataInput input = new DataInput(repository);
    private final CalculationPoints points = new CalculationPoints(repository);
    private final ReadToFile read = new ReadToFile(repository);
    private final RecalculationPoints recalculation = new RecalculationPoints(repository);

    public void start() {
        getInputData();
        calculationInputData();
        read.writeToFile(repository);
    }

    public void getInputData() {
        input.addFileName();
        input.readFromFile();
        recalculation.conversionByTransition();
    }

    public void calculationInputData() {
        points.getFinalObjects();
    }
}
