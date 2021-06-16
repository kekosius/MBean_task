package MBean;

import java.util.ArrayList;

public class Percents implements PercentsMBean{

    private double cent = 0;

    @Override
    public void update(ArrayList<String> history) {
        int miss = 0;
        int all = history.size();
        for (String result : history) {
            if (result.equals("unsuccessful hit")|result.equals("not in area")) miss++;
        }
        if (all>0) {
            cent = (100.0 * miss) / all;
        }
    }

    @Override
    public double getPercent() {
        return cent;
    }

}

