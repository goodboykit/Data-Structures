import java.util.Arrays;

public class CalcuData implements Datas {

    private double[] data;

    public CalcuData(double[] data) {
        this.data = data;
    }

    @Override
    public double Average(double[] data) {

        double sum = Arrays.stream(data).sum();
        double average = (double) sum / data.length;
        String formattedAverage = String.format("%.2f", average);
        return Double.parseDouble(formattedAverage);
        
    }

    @Override
    public double MaximumValue(double[] data) {

        double max = Arrays.stream(data).max().getAsDouble();
        return max;

    }

    @Override
    public double MinimumValue(double[] data) {

        double min = Arrays.stream(data).min().getAsDouble();
        return min;
    }
}
