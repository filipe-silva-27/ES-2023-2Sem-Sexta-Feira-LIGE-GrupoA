package gui;

import javax.swing.*;

import static java.lang.System.*;

public class ConvertController extends ViewController{


    public ConvertController(App app) {
        super(app);
    }

    public void showView(){
       cardLayout.show(contentPane,App.CONVERT_MENU);
    }

    /**
     * Função que faz a conversão de CSV para JSON
     */
    public void convertCSVtoJSON(){
        //TODO chamar convert(uploadedFile); ~Torgo
        if(isFileUploaded()){
            //TODO chamar funcao de converter CSV para JSON
            out.println(app.getSchedule());
            out.println("Conversão CSV to JSON");
        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Função que faz a conversão de JSON para CSV
     */
    public void convertJSONtoCSV(){
        if(isFileUploaded()){
            //TODO chamar funcao de converter JSON para CSV
            out.println(app.getSchedule());
            out.println("Conversão JSON para CSV");
        }else{
            JOptionPane.showMessageDialog(contentPane, "Por favor faça upload de um ficheiro primeiro!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
