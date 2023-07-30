package com.example.quicksortvisual;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class QSVController {
    @FXML
    private Pane animationPane;

    @FXML
    private TextField rectCountField;

    private Rectangle[] rectangles;
    private int[] values;

    private int waitTime = 3000;


    public void onBuildButtonClick() {
        // clear previous rectangles
        animationPane.getChildren().clear();

        // get the number of rectangles to generate
        int numRecs = Integer.parseInt(rectCountField.getText());

        // create a new array for the rectangles
        rectangles = new Rectangle[numRecs];

        // create an array for the rectangle values (heights)
        values = new int[numRecs];

        int recWidth = (int) animationPane.getWidth()/numRecs;

        // generate rectangles and add them to the pane
        for (int i = 0; i < numRecs; i++) {
            // generate a random height
            int randHeight = (int) (Math.random() * animationPane.getHeight());

            // create the rectangle
            Rectangle r = new Rectangle(recWidth*i, animationPane.getHeight()-randHeight, recWidth, randHeight);
            r.setFill(Color.BLUE);
            r.setStroke(Color.LIGHTBLUE);
            r.setStrokeWidth(1);

            // add the rectangle to the array of rectangles
            rectangles[i] = r;

            // add the value (height) to the array of values
            values[i] = randHeight;

            // show the rectangle on the screen
            animationPane.getChildren().add(r);
        }
    }

    public void onSortButtonClick() throws InterruptedException {
         quicksort(values, 0, rectangles.length-1);
    }

    public void quicksort(int[] inArray, int startIndex, int endIndex) throws InterruptedException {
        if (startIndex < endIndex) {
            int pivotIndex = quicksortPartition(inArray, startIndex, endIndex);
            quicksort(inArray, startIndex, pivotIndex);
            quicksort(inArray, pivotIndex+1, endIndex);
        }
    }

    private int quicksortPartition(int[] inArray, int startIndex, int endIndex) throws InterruptedException {
        int midpoint = (endIndex + startIndex) / 2;
        int pivotVal = inArray[midpoint];

        // highlight the rectangle at the midpoint
        rectangles[midpoint].setFill(Color.LIGHTBLUE);

        // wait a bit
        // Wait for a short delay before continuing the sorting
        // Wait for a short delay before continuing the sorting
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(rectangles[midpoint].fillProperty(), Color.LIGHTGREEN)));
        timeline.play();

        int i = startIndex - 1;
        int j = endIndex + 1;

        while (true) {
            do {
                i++;
            } while (inArray[i] < pivotVal);

            do {
                j--;
            } while (inArray[j] > pivotVal);

            if (i >= j) { // indexes swapped
                rectangles[midpoint].setFill(Color.LIGHTGREEN);
                return j;
            }

            // swap inversion
            swap(inArray, i, j);
        }

    }

    private void swap(int[] inArray, int i, int j) {
        int    tmp = inArray[i];

        inArray[i] = inArray[j];
        inArray[j] = tmp;
    }
}