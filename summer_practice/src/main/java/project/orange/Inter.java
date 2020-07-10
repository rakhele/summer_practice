package project.orange;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

public class Inter extends JFrame {

    private JPanel contentPane;
    private JButton readGraphFromFileButton;
    private JButton generateRandomGraphButton;
    private JButton stepByStepButton;
    private JTable graphMatrix;
    private JTable endMatrix;

    private JTextArea lgraph;
    private JPanel graph;
    private JButton readGraphFromKeyboardButton;
    private JButton drawGraphButton;
    private JButton runAlgorithmButton;
    private JButton saveResultToFileButton;
    private JButton helpButton;
    private JButton addEdge;
    private JButton deleteEdge;
    private JButton initialGraphButton;
    private JButton resetAlgorithmButton;
    private GraphController n;
    private JPanel startMatrix;
    private JPanel resultMatrix;

    private Input inputWin;
    private String input;

    public Inter() {
        setUI();
        setTitle("Алгоритм Флойда Уоршелла");
        n = new GraphController();
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
                try {
                    onSave();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        readGraphFromKeyboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onConsole();
            }
        });

        addEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onAddEdge();
            }
        });

        deleteEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onDeleteEdge();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onHelp();
            }
        });

        initialGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onInitialGraph();
            }
        });

        resetAlgorithmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onResetAlgorithm();
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

        contentPane = new JPanel();

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
        lgraph.setForeground(new Color(0x030311));
        lgraph.setBackground(new Color(0xFFFFFF));
        lgraph.setFont(new Font("TimesRoman", 0, 15));
        lgraph.setMinimumSize(new Dimension(100, 100));
        graph.add(lgraph);

        JPanel tables = new JPanel();
        tables.setLayout(new GridLayout(2, 1));
        graphMatrix = new JTable();
        startMatrix = new JPanel();
        startMatrix.add(graphMatrix);
        startMatrix.setBackground(new Color(0xFFEFED));

        endMatrix = new JTable();
        resultMatrix = new JPanel();
        resultMatrix.add(endMatrix);
        resultMatrix.setBackground(new Color(0xFCFFE0));
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
        buttonsPanel.setLayout(new GridLayout(4, 3, 5, 0));
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setButtons(JPanel buttonsPanel){
        readGraphFromFileButton = new JButton();
        readGraphFromFileButton.setText("Ввести граф из файла");
        buttonsPanel.add(readGraphFromFileButton);

        readGraphFromKeyboardButton = new JButton();
        readGraphFromKeyboardButton.setText("Ввести граф с клавиатуры");
        buttonsPanel.add(readGraphFromKeyboardButton);

        generateRandomGraphButton = new JButton();
        generateRandomGraphButton.setText("Сгенерировать случайный граф");
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

        drawGraphButton = new JButton();
        drawGraphButton.setText("Отрисовать граф");
        buttonsPanel.add(drawGraphButton);

        addEdge = new JButton();
        addEdge.setText("Добавить ребро");
        buttonsPanel.add(addEdge);

        deleteEdge = new JButton();
        deleteEdge.setText("Удалить ребро");
        buttonsPanel.add(deleteEdge);

        helpButton = new JButton();
        helpButton.setText("Помощь");
        buttonsPanel.add(helpButton);

        initialGraphButton = new JButton();
        initialGraphButton.setText("Показать исходный граф");
        buttonsPanel.add(initialGraphButton);

        resetAlgorithmButton = new JButton();
        resetAlgorithmButton.setText("Сбросить выполнение алгоритма");
        buttonsPanel.add(resetAlgorithmButton);


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

            if (!n.consoleReader(input)){
                JOptionPane.showMessageDialog(Inter.this, "Неверная запись графа", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            startMatrix.remove(graphMatrix);
            resultMatrix.remove(endMatrix);
            startMatrix.updateUI();
            resultMatrix.updateUI();

            graphMatrix = n.drawMatrix();
            startMatrix.add(graphMatrix);

            lgraph.setText("Граф введен из файла " + file + ":\n");
            lgraph.append(input);
            graph.add(lgraph);
        }
    }

    private void onRandom() {
        input = JOptionPane.showInputDialog(this, new String[] {"", "Введите количество вершин случайного графа: "}, "Генерация случайного графа", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return;
        if (!n.randomGraph(input)){
            JOptionPane.showMessageDialog(Inter.this, "Неверное количество вершин", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        startMatrix.remove(graphMatrix);
        resultMatrix.remove(endMatrix);
        startMatrix.updateUI();
        resultMatrix.updateUI();

        graphMatrix = n.drawMatrix();
        startMatrix.add(graphMatrix);

        lgraph.setText("Случайная генерация графа с " + input + " вершинами:\n");
        lgraph.append(n.getCurrentState());
        graph.add(lgraph);
    }

    private void onDraw () {
        if (!n.drawGraph()){
            JOptionPane.showMessageDialog(Inter.this, "Пустой граф", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void onSave() throws IOException {
        JFileChooser saveFile = new JFileChooser();
        saveFile.setDialogTitle("Сохранить в файл");
        saveFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = saveFile.showSaveDialog(Inter.this);
        if (res == JFileChooser.APPROVE_OPTION) {
            File file = saveFile.getSelectedFile();
            FileWriter writer = new FileWriter(file);
            if (n.saveRes(writer)) {
                JOptionPane.showMessageDialog(Inter.this, "Сохранено в файл" + file, "Сохранено", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(Inter.this, "Ошибка сохранения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            writer.close();
        }
    }

    private void onStep (){
        resultMatrix.remove(endMatrix);
        String in = n.doStep();
        if (in == null){
            JOptionPane.showMessageDialog(Inter.this, "Пустой граф", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        endMatrix = n.drawMatrix();
        endMatrix.setEnabled(false);
        resultMatrix.add(endMatrix);
        lgraph.setText(in);
        graph.add(lgraph);
        this.revalidate();

    }

    private void onRun (){
        if (!n.doAll()){
            JOptionPane.showMessageDialog(Inter.this, "Пустой граф", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        resultMatrix.remove(endMatrix);
        endMatrix = n.drawMatrix();
        endMatrix.setEnabled(false);
        resultMatrix.add(endMatrix);
        lgraph.setText("Алгоритм завершен.\nТекущее состояние графа:\n");
        lgraph.append(n.getCurrentState());
        graph.add(lgraph);
        this.revalidate();

    }

    private void onConsole(){
        inputWin.setVisible(true);
        input = inputWin.getInputText();

        if (!n.consoleReader(input)){
            JOptionPane.showMessageDialog(Inter.this, "Неверная запись графа", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        startMatrix.remove(graphMatrix);
        resultMatrix.remove(endMatrix);
        startMatrix.updateUI();
        resultMatrix.updateUI();

        graphMatrix = n.drawMatrix();
        startMatrix.add(graphMatrix);

        lgraph.setText("Граф введен с клавиатуры:\n");
        lgraph.append(input);
        graph.add(lgraph);
    }

    private void onAddEdge(){
        input = JOptionPane.showInputDialog(this, new String[] {"", "Введите новое ребро: "}, "Создание нового ребра", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return;
        if (!n.addEdge(input)){
            JOptionPane.showMessageDialog(Inter.this, "Неверное ребро", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        startMatrix.remove(graphMatrix);
        resultMatrix.remove(endMatrix);
        resultMatrix.updateUI();
        graphMatrix = n.drawMatrix();
        startMatrix.add(graphMatrix);
        lgraph.setText("Добавлено ребро \"" + input + "\".\nПерезапустите алгоритм.");
        graph.add(lgraph);
        this.revalidate();

    }

    private void onDeleteEdge(){
        input = JOptionPane.showInputDialog(this, new String[] {"", "Введите ребро для удаления: "}, "Удаление ребра", JOptionPane.PLAIN_MESSAGE);
        if (input == null) return;
        if (!n.deleteEdge(input)){
            JOptionPane.showMessageDialog(Inter.this, "Неверное ребро", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        startMatrix.remove(graphMatrix);
        resultMatrix.remove(endMatrix);
        resultMatrix.updateUI();
        graphMatrix = n.drawMatrix();
        startMatrix.add(graphMatrix);
        lgraph.setText("Удалено ребро \"" + input + "\".\nПерезапустите алгоритм.");
        graph.add(lgraph);
        this.revalidate();
    }

    private void onHelp(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(250,250, 300, 300);
    }

    private void onInitialGraph(){
        if (!n.drawInitialGraph()){
            JOptionPane.showMessageDialog(Inter.this, "Пустой граф", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onResetAlgorithm(){
        if (!n.reset()){
            JOptionPane.showMessageDialog(Inter.this, "Пустой граф", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        resultMatrix.remove(endMatrix);
        resultMatrix.updateUI();
        lgraph.setText("Результат работы алгоритма сброшен.\nТекущее состояние графа:\n");
        lgraph.append(n.getCurrentState());
        graph.add(lgraph);
    }
}
