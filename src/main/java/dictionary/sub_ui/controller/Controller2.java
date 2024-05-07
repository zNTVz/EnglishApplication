package dictionary.sub_ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Controller2 {
    @FXML
    ImageView img;
    Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/hangman1.png")));
    Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/hangman2.png")));
    Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/hangman3.png")));
    Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/hangman4.png")));
    Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/hangman5.png")));
    Image image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/hangman6-lose.png")));

    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
    @FXML
    TextField tf3;
    @FXML
    TextField tf4;
    @FXML
    TextField tf5;
    @FXML
    TextField tf6;
    @FXML
    TextField tf7;
    @FXML
    TextField tf8;
    @FXML
    TextField input;
    @FXML
    Label hint;
    @FXML
    Label letter_count;
    @FXML
    Label hint_label;

    String[] data = {
            "MEXICO COUNTRY",
            "HEDWIG BIRD",
            "KUAKATA BEACH",
            "CANADA COUNTRY",
            "DOCTOR PROFESSION",
            "FOOTBALL GAME",
            "TEACHER MENTOR",
            "LEOPARD ANIMAL",
            "BICYCLE TRANSPORT",
            "SALMON FISH",
            "SPARROW BIRD",
            "PARROTS BIRD",
            "EAGLE BIRD",
            "TRAIN TRANSPORT",
            "SHIP TRANSPORT",
            "ENGINEER PROFESSION",
            "BANKER PROFESSION",
            "CRICKET GAME",
            ""
    };

    int random = new Random().nextInt(data.length);
    String word_hint = data[random];
    String[] split = word_hint.split(" ", 2);
    String word = split[0];
    String hint_str = split[1];
    int letter_size = word.length();

    public void initialize() {
        setHint();
    }

    public void setHint() {
        hint.setText(hint_str);
        letter_count.setText(letter_size + " Letters");

        if (letter_size == 7) {
            tf8.setVisible(false);
        }
        if (letter_size == 6) {
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
        if (letter_size == 5) {
            tf6.setVisible(false);
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
        if (letter_size == 4) {
            tf5.setVisible(false);
            tf6.setVisible(false);
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
    }

    private boolean Completed() {
        // Kiểm tra tất cả các ô văn bản tương ứng với số ký tự của từ
        return switch (letter_size) {
            case 8 -> !tf1.getText().isEmpty()
                    && !tf2.getText().isEmpty()
                    && !tf3.getText().isEmpty()
                    && !tf4.getText().isEmpty()
                    && !tf5.getText().isEmpty()
                    && !tf6.getText().isEmpty()
                    && !tf7.getText().isEmpty()
                    && !tf8.getText().isEmpty();
            case 7 -> !tf1.getText().isEmpty()
                    && !tf2.getText().isEmpty()
                    && !tf3.getText().isEmpty()
                    && !tf4.getText().isEmpty()
                    && !tf5.getText().isEmpty()
                    && !tf6.getText().isEmpty()
                    && !tf7.getText().isEmpty();
            case 6 -> !tf1.getText().isEmpty()
                    && !tf2.getText().isEmpty()
                    && !tf3.getText().isEmpty()
                    && !tf4.getText().isEmpty()
                    && !tf5.getText().isEmpty()
                    && !tf6.getText().isEmpty();
            case 5 -> !tf1.getText().isEmpty()
                    && !tf2.getText().isEmpty()
                    && !tf3.getText().isEmpty()
                    && !tf4.getText().isEmpty()
                    && !tf5.getText().isEmpty();
            case 4 -> !tf1.getText().isEmpty()
                    && !tf2.getText().isEmpty()
                    && !tf3.getText().isEmpty()
                    && !tf4.getText().isEmpty();
            // Thêm các trường hợp khác nếu cần
            default -> false;
        };
    }


    public void CheckInput() {
        String str = input.getText();
        if (word.contains(str)) {
            int index = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (String.valueOf(c).equals(str)) {
                    setLetter(index, Character.toString(c));
                }
                index++;
            }
            if (Completed()) {
                // Hiển thị hình ảnh chúc mừng khi người chơi thắng
                img.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/hangman7-win.jpg"))));
            }
        } else {
            setImage();
        }
    }

    public void setLetter(int index, String str) {
        if (index == 0)
            tf1.setText(str);
        else if (index == 1)
            tf2.setText(str);
        else if (index == 2)
            tf3.setText(str);
        else if (index == 3)
            tf4.setText(str);
        else if (index == 4)
            tf5.setText(str);
        else if (index == 5)
            tf6.setText(str);
        else if (index == 6)
            tf7.setText(str);
        else if (index == 7)
            tf8.setText(str);
    }

    int life = 6;

    public void setImage() {
        if (life == 6)
            img.setImage(image2);
        else if (life == 5)
            img.setImage(image3);
        else if (life == 4)
            img.setImage(image4);
        else if (life == 3)
            img.setImage(image5);
        else if (life == 2)
            img.setImage(image6);
        else if (life == 1)
            img.setImage(image7);
        life--;
    }

    @FXML
    public void changeScene(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/gameScene.fxml")));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Hangman Game");
        window.setScene(new Scene(parent, 800, 650));
        window.show();
    }
}
