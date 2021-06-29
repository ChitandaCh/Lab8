module client.gradle.main {

    requires javafx.graphics;
    requires javafx.controls;

    opens com.company.Models;
    opens com.company;
}