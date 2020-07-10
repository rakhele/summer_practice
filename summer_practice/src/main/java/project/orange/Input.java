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
        GridLayout r = new GridLayout(2, 1);
        mainPane.setLayout(r);


        textArea = new JTextArea("");
        mainPane.add(textArea);

        mainPane.add(textArea);
        mainPane.add(new JScrollPane(textArea));
        textArea.setLineWrap(true);

        contentPane.add(mainPane, constraints);
        setContentPane(contentPane);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JTextArea lgraph = new JTextArea("Правила ввода графа:\nПетли запрещены, граф предполагается связным\n" +
                "На вход подаются ребра вида:\n  вершина1 вершина2 вес ребра\n" +
                "Вершины именуются !одним! символом\nДля завершения ввода закройте окно");
        lgraph.setLineWrap(true);
        lgraph.setEditable(false);
        //lgraph.setForeground(new Color(0x030311));
        //lgraph.setBackground(new Color(0xFFFFFF));
        lgraph.setMinimumSize(new Dimension(100, 100));
        mainPane.add(lgraph);


    }

    public String getInputText(){
        textInput = textArea.getText();
        return textInput;
    }
}
