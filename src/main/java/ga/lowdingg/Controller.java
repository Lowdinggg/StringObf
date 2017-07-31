package ga.lowdingg;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.PrintStream;
import java.util.Random;

public class Controller {

    @FXML
    TextField stringarea;
    @FXML
    TextArea codearea;
    boolean active = false;


   @FXML public void fire(){
       encrypt(stringarea.getText());
    }

     void encrypt(String string) {
        Random r = new Random(System.currentTimeMillis());
        byte[] b = string.getBytes();
        int c = b.length;
        PrintStream o = System.out;
        active  = true;
         codearea.appendText("String get(){");

         codearea.appendText(" final Object o = (new Object() {\n");
         codearea.appendText("int t; \n");
         codearea.appendText("public String toString() {\n");
         codearea.appendText("byte[] buf = new byte[");
         codearea.appendText(c+"");
         codearea.appendText("];\n");

        for (int i = 0; i < c; ++i) {
            int t = r.nextInt();
            int f = r.nextInt(24) + 1;

            t = (t & ~(0xff << f)) | (b[i] << f);

            codearea.appendText("t = ");
            codearea.appendText(t+"");
            codearea.appendText(";\n");
            codearea.appendText("buf[");
            codearea.appendText(""+i);
            codearea.appendText("] = (byte) (t >>> ");
            codearea.appendText(f+"");
            codearea.appendText(");\n");
        }

         codearea.appendText("return new String(buf);\n");
         codearea.appendText("}});\n");
         codearea.appendText("return o.toString();\n");
         codearea.appendText("}");

        active = false;

    }

}
