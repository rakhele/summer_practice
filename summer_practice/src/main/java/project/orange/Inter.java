package project.orange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

public class Inter extends JFrame {

    private JPanel contentPane;
    private JButton readGraphFromFileButton;
    private JButton generateRandomGraphButton;
    private JTextArea textArea;
    private JButton stepByStepButton;
    private JTable graphMatrix;
    private JTextArea lgraph;
    private  JPanel graph;
    private JButton readGraphFromKeyboardButton;
    private JButton drawGraphButton;
    private JButton runAlgorithmButton;
    private JButton saveResultToFileButton;
    private JButton aboutAlgorithmButton;
    private JButton helpButton;

    private Input inputWin;
    private String input;

    public Inter() {
        setUI();
        inputWin = new Input();
        inputWin.setBounds(150, 100, 320, 330);
        readGraphFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    onFromFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        generateRandomGraphButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                onRandom();
            }
        });

        drawGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onDraw();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onHelp();
            }
        });

        aboutAlgorithmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onAbout();
            }
        });

        runAlgorithmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onRun();
            }
        });

        stepByStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onStep();
            }
        });

        saveResultToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onSave();
            }
        });

        readGraphFromKeyboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onConsole();
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onRandom();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void setUI(){

        /*
        Цвета для того, чтобы показать, как все делится.
        Лучше ipady, gridy и все такое часто не трогать, а то я хз, что там действительно влияет на расположение, а что
        уже нет
        Наверно для всех JPanel лучше отдельные методы создать, чтобы было аккуратнее и чище
        И мне не нравится, что при запуске программы сразу не открывается большое окно, а его нужно растягивать. Я не знаю,
        как это исправить
        */

        contentPane = new JPanel();
        //contentPane.setSize(1920, 1080);

        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;

        JPanel algoPanel = new JPanel();
        algoPanel.setLayout(new GridLayout(1, 2));
        graph = new JPanel();
        graph.setLayout(new GridLayout(1, 1));
        graph.setBackground(new Color(0xFFFFFF));
        lgraph = new JTextArea("Тут будет текущий граф");
        lgraph.setLineWrap(true);
        lgraph.setEditable(false);
        lgraph.setCaretColor(new Color(0x030311));
        lgraph.setForeground(new Color(0x030311));
        lgraph.setBackground(new Color(0xFFFFFF));
        lgraph.setFont(new Font("Arial", Font.TYPE1_FONT, 15));
        lgraph.setMinimumSize(new Dimension(100, 100));
        graph.add(lgraph);

        JPanel tables = new JPanel();
        tables.setLayout(new GridLayout(2, 1));
        JPanel startMatrix = new JPanel();
        startMatrix.setBackground(new Color(0xB50000));

        JPanel resultMatrix = new JPanel();
        resultMatrix.setBackground(new Color(0x00BB));
        tables.add(startMatrix);
        tables.add(resultMatrix);

        algoPanel.add(graph);
        algoPanel.add(tables);

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridheight = 10;
        constraints.anchor = GridBagConstraints.PAGE_START;
        contentPane.add(algoPanel, constraints);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 3, 5, 0));
        setButtons(buttonsPanel);

        constraints.ipady = 0;
        constraints.gridy = 10;
        constraints.gridx = 0;
        constraints.weighty = 0.05;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridheight = 1;
        contentPane.add(buttonsPanel, constraints);



        setContentPane(contentPane);
        setVisible(true);
    }

    private void setButtons(JPanel buttonsPanel){
        readGraphFromFileButton = new JButton();
        readGraphFromFileButton.setText("Ввести граф из файла");
        buttonsPanel.add(readGraphFromFileButton);

        readGraphFromKeyboardButton = new JButton();
        readGraphFromKeyboardButton.setText("Ввести граф с клавиатуры");
        buttonsPanel.add(readGraphFromKeyboardButton);

        generateRandomGraphButton = new JButton();
        generateRandomGraphButton.setText("Сгенерировать случаный граф");
        buttonsPanel.add(generateRandomGraphButton);


        runAlgorithmButton = new JButton();
        runAlgorithmButton.setText("Запустить алгоритм");
        buttonsPanel.add(runAlgorithmButton);

        stepByStepButton = new JButton();
        stepByStepButton.setText("Алгоритм пошагово");
        buttonsPanel.add(stepByStepButton);

        saveResultToFileButton = new JButton();
        saveResultToFileButton.setText("Сохранить результат в файл");
        buttonsPanel.add(saveResultToFileButton);


        helpButton = new JButton();
        helpButton.setText("Помощь");
        buttonsPanel.add(helpButton);

        drawGraphButton = new JButton();
        drawGraphButton.setText("Отрисовать граф");
        buttonsPanel.add(drawGraphButton);

        aboutAlgorithmButton = new JButton();
        aboutAlgorithmButton.setText("Об алгоритме");
        buttonsPanel.add(aboutAlgorithmButton);

    }

    private void onFromFile() throws IOException {
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            FileReader reader = new FileReader(file);
            input = "";
            int c;
            while((c=reader.read())!=-1){
                input += (char)c;
            }
            System.out.println(input);
            lgraph.setText(input);
            graph.add(lgraph);
        }
    }

    private void onRandom() {
        /*JDialog frame = new JDialog();
        frame.setVisible(true);
        frame.setBounds(250,250, 300, 300);
        frame.setModal(true);*/


        input = JOptionPane.showInputDialog(this, new String[] {"", "Введите количество вершин случайного графа: "}, "Генерафия случайного графа", JOptionPane.PLAIN_MESSAGE);
        System.out.println(input);
    }

    private void onDraw () {

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(250,250, 300, 300);

        class GComp extends JComponent {
            protected void paintComponent(Graphics g) {
                Font font = new Font("Ariel", Font.BOLD, 25);
                Graphics2D g2 = (Graphics2D) g;
                g2.setFont(font);
                Line2D l2 = new Line2D.Double(1, 1, 100, 100);
                Rectangle rec = new Rectangle(10, 10, 30, 30);
                Ellipse2D ell = new Ellipse2D.Double(30, 50, 100, 100);
                g2.draw(rec);
                g2.draw(ell);
                g2.draw(l2);
            }
        }


        frame.add(new GComp());
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void onSave (){

    }

    private void onStep (){

    }

    private void onHelp (){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(250,250, 300, 300);
    }

    private void onRun (){

    }

    private void onAbout (){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(250,250, 300, 300);
    }

    private void onConsole(){
        inputWin.setVisible(true);
        input = inputWin.getInputText();

        System.out.println("[" + input + "]");
        lgraph.setText(input);
        graph.add(lgraph);
    }


}