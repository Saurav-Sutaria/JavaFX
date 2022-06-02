package hellofx;
import static java.lang.constant.ConstantDescs.NULL;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.*;
import java.util.regex.*;
public class ContactForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane p = new GridPane();
        p.setAlignment(Pos.CENTER);
        p.setVgap(20);
        //names
        Text fName = new Text("First Name : ");
        Text mName = new Text("Middle Name : ");
        Text lName = new Text("Last Name : ");
        TextField fNameT = new TextField();
        TextField mNameT = new TextField();
        TextField lNameT = new TextField();
        p.add(fName, 0, 0);
        p.add(fNameT, 1, 0);
        p.add(mName, 0, 1);
        p.add(mNameT, 1, 1);
        p.add(lName, 0, 2);
        p.add(lNameT, 1, 2);

        //email address
        Text email = new Text("Email : ");
        TextField emailT = new TextField();
        p.add(email, 0, 3);
        p.add(emailT, 1, 3);

        //mobile number
        Text mobile = new Text("Mobile Number : ");
        TextField mobileT = new TextField();
        p.add(mobile, 0, 4);
        p.add(mobileT, 1, 4);

        //date of birth
        Text dob = new Text("Date Of birth : ");
        String[] dateArr = new String[31];
        for(int i=0;i<31;i++) dateArr[i] = (i+1) + "";
        String[] monthArr = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
        String[] yearArr = new String[23];
        for(int i=0;i<23;i++) yearArr[i] = (2000 + i) + "";
        ComboBox<String> date = new ComboBox<>();
        ComboBox<String> month = new ComboBox<>();
        ComboBox<String> year = new ComboBox<>();
        date.setValue(dateArr[0]);
        month.setValue(monthArr[0]);
        year.setValue(yearArr[0]);
        date.getItems().addAll(dateArr);
        month.getItems().addAll(monthArr);
        year.getItems().addAll(yearArr);
        HBox dobBox = new HBox(10);
        dobBox.getChildren().addAll(date, month, year);
        
        p.add(dob, 0, 5);
        p.add(dobBox, 1, 5);

        //gender
        Text gender = new Text("Gender : ");
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        ToggleGroup gen = new ToggleGroup();
        male.setToggleGroup(gen);
        female.setToggleGroup(gen);
        HBox genBox = new HBox(10);
        genBox.getChildren().addAll(male, female);
        p.add(gender, 0, 6);
        p.add(genBox, 1, 6);

        //languages known
        Text language = new Text("Languages Known : ");
        CheckBox hin = new CheckBox("Hindi");
        CheckBox guj = new CheckBox("Gujarati");
        CheckBox eng = new CheckBox("English");
        HBox langBox = new HBox();
        langBox.getChildren().addAll(hin, guj, eng);
        langBox.setSpacing(10);
        p.add(language, 0, 7);
        p.add(langBox, 1, 7);
        //buttons
        Button submit = new Button("Submit");
        Button clr = new Button("Clear");
        HBox btBox = new HBox(30);
        btBox.getChildren().addAll(submit, clr);
        p.add(btBox, 1, 8);
        //submit clear action
        submit.setOnAction(e -> {
            if(fNameT.getText().trim().isEmpty() || lNameT.getText().trim().isEmpty() || mNameT.getText().trim().isEmpty() ||
                    emailT.getText().trim().isEmpty() || mobileT.getText().trim().isEmpty() || !(hin.isSelected() || eng.isSelected() || guj.isSelected()
                    )|| gen.getSelectedToggle() == NULL){
                Alert emptyField = new Alert(AlertType.ERROR,"Please fill all the field");
                emptyField.setHeaderText("Fail to Submit");
                emptyField.showAndWait();
                
            }
            else if(!(isValid(emailT.getText().trim()))){
                Alert invalidEmail = new Alert(AlertType.ERROR, "Invalid Email");
                invalidEmail.setHeaderText("Enter valid email address");
                invalidEmail.showAndWait();
            }
            else if(!(checkNum(mobileT.getText().trim()))){
                Alert invalidNum = new Alert(AlertType.ERROR, "Invalid Mobile Number");
                invalidNum.setHeaderText("Enter valid mobile number");
                invalidNum.showAndWait();
            }
            else{
                Stage newStage = new Stage();
                StackPane newPane = new StackPane();
                Text greet = new Text("Welcome.... " + fNameT.getText().trim());
                Font font = Font.font("Arial", FontWeight.BOLD, 40);
                greet.setFont(font);
                newPane.getChildren().add(greet);
                Scene newScene = new Scene(newPane, 400, 400);
                newStage.setTitle("Welcome Page");
                newStage.setScene(newScene);
                newStage.show();
            }
        });
        //clear button action
        clr.setOnAction(e -> {
            fNameT.clear();
            mNameT.clear();
            emailT.clear();
            lNameT.clear();
            mobileT.clear();
            date.setValue(dateArr[0]);
            month.setValue(monthArr[0]);
            year.setValue(yearArr[0]);
            male.setSelected(false);
            female.setSelected(false);
            hin.setSelected(false);
            guj.setSelected(false);
            eng.setSelected(false);
        });
        primaryStage.setTitle("Contact form");
        primaryStage.setScene(new Scene(p, 400, 400));
        primaryStage.show();
    }
    public static boolean isValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static boolean checkNum(String s){
        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(s);
        return (m.matches());
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
