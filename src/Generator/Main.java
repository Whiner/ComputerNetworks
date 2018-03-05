package Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
    public static void main(String[] args) { // свой класс исключений сделать


        Field.GetInstance().setCells_Count_X(10);
        Field.GetInstance().setCells_Count_Y(10);
        Field.GetInstance().setSizeFieldInPx_X(100);
        Field.GetInstance().setSizeFieldInPx_Y(100);

        TopologyGenerator generator = new TopologyGenerator();
        try {
            generator.GenerateWAN(6,3);
        } catch (GeneratorException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
