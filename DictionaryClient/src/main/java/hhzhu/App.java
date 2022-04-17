package hhzhu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


/**
 * Author: Haohong Zhu
 * Student ID: 1305370
 * JavaFX App
 */
public class App extends Application {
    HBox hBoxTop = new HBox();
    TextArea textAreaTop = new TextArea();
    VBox vBoxTop = new VBox();
    Button query = new Button("Query");
    Button add = new Button("Add");
    Button remove = new Button("Remove");

    TextFlow textFlowCenter = new TextFlow();
    Text text = new Text();
    String receivedContent = "";

    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            BorderPane root = new BorderPane();
            //top
            query.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg)
                {
                    String content = textAreaTop.getText();
                    short action = 1;
                    DictionaryClient dictionaryClient = new DictionaryClient();
                    receivedContent = dictionaryClient.send(content,action);
                    show();
                }
            });
            add.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg)
                {
                    String content = textAreaTop.getText();
                    short action = 2;
                    DictionaryClient dictionaryClient = new DictionaryClient();
                    receivedContent = dictionaryClient.send(content,action);
                    show();
                }
            });
            remove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg)
                {
                    String content = textAreaTop.getText();
                    short action = 3;
                    DictionaryClient dictionaryClient = new DictionaryClient();
                    receivedContent = dictionaryClient.send(content,action);
                    show();
                }
            });
            query.setPrefWidth(100);
            add.setPrefWidth(100);
            remove.setPrefWidth(100);
            query.setId("lion");
            add.setId("lion");
            remove.setId("lion");
            vBoxTop.setPadding(new Insets(0,5,0,5));
            vBoxTop.setSpacing(8);
            vBoxTop.getChildren().addAll(query,add,remove);
            textAreaTop.setMaxHeight(105);
            textAreaTop.setWrapText(true);
            hBoxTop.getChildren().addAll(textAreaTop,vBoxTop);
            HBox.setHgrow(textAreaTop, Priority.ALWAYS);
            hBoxTop.setPadding(new Insets(5));
            root.setTop(hBoxTop);

            //center
            textFlowCenter.getChildren().addAll(text);
            textFlowCenter.setPadding(new Insets(5));
            root.setCenter(textFlowCenter);
            BorderPane.setMargin(textFlowCenter,new Insets(5));

            Scene scene = new Scene(root, 800, 500);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setTitle("Dictionary Client");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void show(){
        text.setText(receivedContent);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}