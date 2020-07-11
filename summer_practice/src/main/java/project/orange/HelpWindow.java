package project.orange;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpWindow extends JFrame {
    private JButton create;
    private JButton algorithm;
    private JPanel buttonPanel;
    private JButton drawing;
    private JButton save;
    private JButton creators;
    private JTextArea info;
    private JPanel contentPane;
    private JButton changes;

    private final String marker = Character.valueOf('\u2022').toString();

    private final String CREATE = "Как создать граф?\n" + marker +
            " Граф может быть загружен из текстового файла при нажатии на кнопку \"Ввести граф из файла\".\n" +
            "Файл должен содержать строки формата \"v1 v2 w12\", где v1 и v2 - имена вершин (одна буква), w12 - вес ребра (число, не превышающее 100).\n" + marker +
            " Граф может быть введен с клавиатуры при нажатии на кнопку \"Ввести граф с клавиатуры\". " +
            "При нажатии на кнопку открывается новое окно с инструкциями по вводу. После завершения ввода необходимо закрыть окно.\n" + marker +
            " Можно сгенерировать случайный граф нажатием на кнопку \"Сгенерировать случайный граф\". " +
            "При нажатии откроется диалоговое окно для ввода желаемого числа вершин. Число вершин должно не превышать 10.\n" +
            "Введенный граф будет изображен на экране в виде списка ребер и в виде матрицы смежности.";

    private final String ALGORITHM = "Как запустить алгоритм?\n" + "В программе реализуется алгоритм Флойда-Уоршелла, предназначенный для поиска кратчайших путей между всеми вершинами ориентированного графа.\n" + marker +
            " Запустить одновременное выполнение алгоритма можно нажатием на кнопку \"Запустить алгоритм\". При этом результат будет выведен на экран в виде списка и матрицы кратчайших путей.\n" + marker +
            " Для пошагового выполнения алгоритма необходимо нажать на кнопку \"Алгоритм пошагово\". Будет выполнен один шаг алгоритма, результат будет выведен в виде матрицы кратчайших путей. " +
            "Информация о выполненном шаге будет выведена в текстовом поле.\n" + marker +
            " Можно сбросить результат выполнения алгоритма нажатием на кнопку \"Сбросить выполнение алгоритма\". При этом граф вернется к исходному состоянию.";

    private final String DRAWING = "Как посмотреть отрисовку графа?\n" +
            "Существует два варианта отрисовки графа:\n" + marker +
            " При нажатии на кнопку \"Отрисовать граф\" будет открыто окно с изображением обрабатываемого графа в его текущем состоянии. " +
            "При запуске пошагового выполнения алгоритма в этом окне будут разными цветами выделены обрабатываемые вершины: вершина, из которой ищут путь, будет выделена оранжевым, " +
            "вершина, в которую ищут путь, - красным, вершина, через которую проходит новый путь, - зеленым. " +
            "При отрисовке графа после завершения алгоритма можно посмотреть кратчайшие пути из одной вершины в оставшиеся кликом мыши по этой вершине.\n" + marker +
            " При нажатии на кнопку \"Показать исходный граф\" будет открыто окно с изображением исходного графа независимо от его состояния.\n" +
            "В обоих окнах можно перетаскивать вершины.";

    private final String SAVE = "Результат может быть сохранен в файл нажатием на кнопку \"Сохранить результат в файл\". " +
            "Полученный текстовый файл будет содержать набор строк вида \"v1 v2 p12\", где v1 и v2 - имена вершин, p12 - длина кратчайшего пути между вершинами (-1, если пути нет).";

    private final String CREATORS = "Программа была разработана студентками группы 8382 Кузиной А.М., Кулачковой М.К. и Рочевой А.К.";

    private final String CHANGES = "Как изменить граф?\n" + marker +
            " Можно добавить ребро в граф нажатием на кнопку \"Добавить ребро\". При нажатии откроется диалоговое окно, в которое требуется ввести строковое представление ребра в виде " +
            "\"v1 v2 w12\", где v1 и v2 - имена вершин (одна буква), w12 - вес ребра. Добавленное ребро отобразится в матрице смежности.\n" + marker +
            " Можно удалить ребро нажатием на кнопку \"Удалить ребро\". При этом откроется диалоговое окно, в которое требуется ввести строковое представление удаляемого ребра в виде " +
            "\"v1 v2\", где v1 и v2 - имена смежных вершин.\n" + marker +
            " Можно изменить вес уже существующего ребра, введя новое значение в соответствующей ячейке матрицы смежности.\n" +
            "При любом изменении графа алгоритм должен быть перезапущен.";

    public HelpWindow() {
        $$$setupUI$$$();
        add(contentPane);
        setTitle("Помощь");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText(CREATE);
            }
        });
        algorithm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText(ALGORITHM);
            }
        });
        drawing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText(DRAWING);
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText(SAVE);
            }
        });
        creators.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText(CREATORS);
            }
        });
        changes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText(CHANGES);
            }
        });
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, -1));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayoutManager(7, 1, new Insets(1, 0, 0, 0), 0, -1));
        contentPane.add(buttonPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        drawing = new JButton();
        Font drawingFont = this.$$$getFont$$$("Times New Roman", Font.PLAIN, 14, drawing.getFont());
        if (drawingFont != null) drawing.setFont(drawingFont);
        drawing.setHorizontalAlignment(2);
        drawing.setText(" Как посмотреть отрисовку графа");
        buttonPanel.add(drawing, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        create = new JButton();
        Font createFont = this.$$$getFont$$$("Times New Roman", Font.PLAIN, 14, create.getFont());
        if (createFont != null) create.setFont(createFont);
        create.setHorizontalAlignment(2);
        create.setText(" Как создать граф");
        buttonPanel.add(create, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        algorithm = new JButton();
        Font algorithmFont = this.$$$getFont$$$("Times New Roman", Font.PLAIN, 14, algorithm.getFont());
        if (algorithmFont != null) algorithm.setFont(algorithmFont);
        algorithm.setHorizontalAlignment(2);
        algorithm.setText(" Как запустить алгоритм");
        buttonPanel.add(algorithm, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        save = new JButton();
        Font saveFont = this.$$$getFont$$$("Times New Roman", Font.PLAIN, 14, save.getFont());
        if (saveFont != null) save.setFont(saveFont);
        save.setHorizontalAlignment(2);
        save.setText(" Сохранение результата");
        buttonPanel.add(save, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        creators = new JButton();
        Font creatorsFont = this.$$$getFont$$$("Times New Roman", Font.PLAIN, 14, creators.getFont());
        if (creatorsFont != null) creators.setFont(creatorsFont);
        creators.setHorizontalAlignment(2);
        creators.setText(" О создателях");
        buttonPanel.add(creators, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        buttonPanel.add(spacer1, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        changes = new JButton();
        Font changesFont = this.$$$getFont$$$("Times New Roman", Font.PLAIN, 14, changes.getFont());
        if (changesFont != null) changes.setFont(changesFont);
        changes.setHorizontalAlignment(2);
        changes.setText(" Как изменить граф");
        buttonPanel.add(changes, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        info = new JTextArea();
        info.setEditable(false);
        Font infoFont = this.$$$getFont$$$("Times New Roman", Font.PLAIN, 14, info.getFont());
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        if (infoFont != null) info.setFont(infoFont);
        scrollPane1.setViewportView(info);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
