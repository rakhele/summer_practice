package project.orange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input extends JDialog {
    private JPanel contentPane;
    private JPanel mainPane;
    private JTextArea textArea;
    private JButton confermButton;
    private String textInput = "";

    public Input(){
        setTitle("Ввести граф с клавиатуры");
        setModal(true);
        contentPane = new JPanel();
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        contentPane.setLayout(new GridBagLayout());
        setAutoRequestFocus(true);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 5;
        constraints.weighty = 5;
        constraints.gridy = 5;
        constraints.gridx = 5;
        constraints.gridheight = 10;
        constraints.anchor = GridBagConstraints.PAGE_START;


        mainPane = new JPanel();
        var r = new GridLayout(2, 1);
        mainPane.setLayout(r);


        textArea = new JTextArea("hello :)");
        mainPane.add(textArea);

        mainPane.add(textArea);
        mainPane.add(new JScrollPane(textArea));
        textArea.setLineWrap(true);

        contentPane.add(mainPane, constraints);
        setContentPane(contentPane);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        confermButton = new JButton("Подтвердить");
        confermButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onConferm();
            }
        });
        constraints.ipady = 0;
        constraints.gridy = 10;
        constraints.gridx = 0;
        constraints.weighty = 0.05;
       // constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridheight = 5;
        //constraints.
        //r.addLayoutComponent("best Button", confermButton);
        mainPane.add(confermButton, constraints);


    }

    private void onConferm(){
        textInput = textArea.getText();
        return;
    }

    public String getInputText(){
        textInput = textArea.getText();
        return textInput;
    }
}