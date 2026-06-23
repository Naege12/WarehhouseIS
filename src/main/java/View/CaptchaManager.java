package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class CaptchaManager {
    private JPanel gridPanel;
    private JLabel statusLabel;
    private JButton enterButton;
    private List<JLabel> pieces;
    private int gridSize = 2; // 2x2
    private int pieceSize;
    private boolean captchaPassed = false;
    private int[][] correctPositions;
    private JLabel selectedPiece = null;
    private Random random = new Random();

    public CaptchaManager(JPanel gridPanel, JLabel statusLabel, JButton enterButton) {
        this.gridPanel = gridPanel;
        this.statusLabel = statusLabel;
        this.enterButton = enterButton;
        initializeCaptcha();
    }

    private void initializeCaptcha() {
        gridPanel.removeAll();
        gridPanel.setLayout(null);
        gridPanel.setBackground(new Color(60, 60, 60));

        // Размеры
        Dimension panelSize = gridPanel.getSize();
        if (panelSize.width == 0 || panelSize.height == 0) {
            panelSize = new Dimension(350, 280);
        }

        int panelWidth = panelSize.width - 20;
        int panelHeight = panelSize.height - 20;
        pieceSize = Math.min(panelWidth, panelHeight) / gridSize;
        if (pieceSize < 50) pieceSize = 120;

        // Загружаем фрагменты
        BufferedImage[] pieceImages = loadPiecesFromFolder();
        if (pieceImages == null) {
            pieceImages = createTestPieces();
        }

        createPuzzle(pieceImages);

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private BufferedImage[] loadPiecesFromFolder() {
        BufferedImage[] pieces = new BufferedImage[4];

        try {
            String[] possiblePaths = {
                    "captcha/",
                    "src/captcha/",
                    "src/resources/captcha/",
                    "resources/captcha/",
                    "./captcha/"
            };

            boolean found = false;

            for (String basePath : possiblePaths) {
                File folder = new File(basePath);
                if (folder.exists() && folder.isDirectory()) {
                    found = true;
                    for (int i = 0; i < 4; i++) {
                        String fileName = basePath + "piece_" + i + ".png";
                        File imageFile = new File(fileName);
                        if (imageFile.exists()) {
                            pieces[i] = ImageIO.read(imageFile);
                            System.out.println("Загружен: " + fileName);
                        }
                    }
                    break;
                }
            }

            if (!found) {
                for (int i = 0; i < 4; i++) {
                    java.net.URL imageUrl = getClass().getResource("/captcha/piece_" + i + ".png");
                    if (imageUrl != null) {
                        pieces[i] = ImageIO.read(imageUrl);
                        System.out.println("Загружен через ClassLoader: piece_" + i + ".png");
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                if (pieces[i] == null) {
                    System.out.println("Фрагмент " + i + " не загружен!");
                    return null;
                }
            }
            return pieces;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private BufferedImage[] createTestPieces() {
        BufferedImage[] pieces = new BufferedImage[4];
        int size = pieceSize;

        for (int i = 0; i < 4; i++) {
            pieces[i] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = pieces[i].createGraphics();

            Color[] colors = {
                    new Color(231, 76, 60),
                    new Color(46, 204, 113),
                    new Color(52, 152, 219),
                    new Color(241, 196, 15)
            };

            g.setColor(colors[i]);
            g.fillRect(0, 0, size, size);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString(String.valueOf(i + 1), size/2 - 15, size/2 + 15);
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(2));
            g.drawRect(0, 0, size - 1, size - 1);
            g.dispose();
        }
        return pieces;
    }

    private void createPuzzle(BufferedImage[] pieceImages) {
        pieces = new ArrayList<>();

        // Создаем фрагменты
        for (int i = 0; i < pieceImages.length; i++) {
            Image scaledImage = pieceImages[i].getScaledInstance(
                    pieceSize - 2, pieceSize - 2, Image.SCALE_SMOOTH
            );
            JLabel piece = new JLabel(new ImageIcon(scaledImage));
            piece.setBorder(new LineBorder(Color.WHITE, 2));
            piece.setCursor(new Cursor(Cursor.HAND_CURSOR));
            piece.putClientProperty("originalIndex", i);
            piece.setOpaque(true);
            piece.setBackground(new Color(60, 60, 60));
            pieces.add(piece);
        }

        // Правильные позиции
        correctPositions = new int[4][2];
        for (int i = 0; i < 4; i++) {
            int row = i / gridSize;
            int col = i % gridSize;
            correctPositions[i][0] = col * pieceSize + 5 + (col > 0 ? 5 : 0);
            correctPositions[i][1] = row * pieceSize + 5 + (row > 0 ? 5 : 0);
        }

        // ===== ПОЛНАЯ РАНДОМИЗАЦИЯ =====
        // 1. Перемешиваем список фрагментов
        Collections.shuffle(pieces, random);

        // 2. Дополнительно рандомно меняем местами еще несколько раз для надежности
        for (int i = 0; i < 5; i++) {
            int idx1 = random.nextInt(4);
            int idx2 = random.nextInt(4);
            if (idx1 != idx2) {
                Collections.swap(pieces, idx1, idx2);
            }
        }

        // 3. Проверяем, не собрался ли пазл случайно (если да - перемешиваем еще раз)
        boolean isSolved = checkIfSolved();
        if (isSolved) {
            System.out.println("Случайно собрался пазл - перемешиваем еще раз!");
            Collections.shuffle(pieces, random);
            // Проверяем еще раз
            isSolved = checkIfSolved();
            if (isSolved) {
                // Если опять собрался - просто меняем первые два местами
                Collections.swap(pieces, 0, 1);
            }
        }

        // Размещаем на сетке в рандомном порядке
        for (int i = 0; i < pieces.size(); i++) {
            JLabel piece = pieces.get(i);

            // Вычисляем позицию на основе порядка в списке (который уже рандомный)
            int row = i / gridSize;
            int col = i % gridSize;

            int x = col * pieceSize + 5 + (col > 0 ? 5 : 0);
            int y = row * pieceSize + 5 + (row > 0 ? 5 : 0);

            piece.setBounds(x, y, pieceSize - 2, pieceSize - 2);
            piece.addMouseListener(new PieceClickListener());
            gridPanel.add(piece);
        }

        // Показываем пользователю текущее состояние (для отладки)
        System.out.println("=== Текущее расположение фрагментов ===");
        for (int i = 0; i < pieces.size(); i++) {
            JLabel piece = pieces.get(i);
            int originalIndex = (int) piece.getClientProperty("originalIndex");
            System.out.println("Позиция " + i + " -> Фрагмент " + originalIndex);
        }
        System.out.println("========================================");

        statusLabel.setText("Нажмите на два фрагмента, чтобы поменять их местами");
        statusLabel.setForeground(Color.WHITE);
        captchaPassed = false;
        enterButton.setEnabled(false);
    }

    // Метод для проверки, собран ли пазл
    private boolean checkIfSolved() {
        for (int i = 0; i < pieces.size(); i++) {
            JLabel piece = pieces.get(i);
            int originalIndex = (int) piece.getClientProperty("originalIndex");

            // Если фрагмент не на своей позиции
            if (i != originalIndex) {
                return false;
            }
        }
        return true;
    }

    // Класс для обработки кликов
    private class PieceClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (captchaPassed) return;

            JLabel clicked = (JLabel) e.getSource();

            if (selectedPiece == null) {
                // Выбираем первый фрагмент
                selectedPiece = clicked;
                selectedPiece.setBorder(new LineBorder(Color.YELLOW, 3));
                selectedPiece.setCursor(new Cursor(Cursor.HAND_CURSOR));
                statusLabel.setText("Выберите второй фрагмент для обмена");
                statusLabel.setForeground(Color.CYAN);

                // Поднимаем наверх
                selectedPiece.getParent().setComponentZOrder(selectedPiece, 0);
                gridPanel.repaint();

            } else if (selectedPiece == clicked) {
                // Отменяем выбор
                selectedPiece.setBorder(new LineBorder(Color.WHITE, 2));
                selectedPiece.setCursor(new Cursor(Cursor.HAND_CURSOR));
                selectedPiece = null;
                statusLabel.setText("Нажмите на два фрагмента, чтобы поменять их местами");
                statusLabel.setForeground(Color.WHITE);

            } else {
                // Меняем местами два фрагмента
                swapPieces(selectedPiece, clicked);

                // Сбрасываем выделение
                selectedPiece.setBorder(new LineBorder(Color.WHITE, 2));
                selectedPiece.setCursor(new Cursor(Cursor.HAND_CURSOR));
                selectedPiece = null;

                statusLabel.setText("Нажмите на два фрагмента, чтобы поменять их местами");
                statusLabel.setForeground(Color.WHITE);

                // Проверяем сборку
                checkPuzzle();
            }
        }
    }

    // Метод для обмена фрагментов местами
    private void swapPieces(JLabel piece1, JLabel piece2) {
        // Сохраняем позиции
        Point pos1 = piece1.getLocation();
        Point pos2 = piece2.getLocation();

        // Меняем местами
        piece1.setLocation(pos2);
        piece2.setLocation(pos1);

        // Анимация подсветки
        piece1.setBorder(new LineBorder(Color.ORANGE, 3));
        piece2.setBorder(new LineBorder(Color.ORANGE, 3));

        // Поднимаем оба наверх
        piece1.getParent().setComponentZOrder(piece1, 0);
        piece2.getParent().setComponentZOrder(piece2, 0);

        // Возвращаем нормальную рамку через 300мс
        Timer timer = new Timer(300, evt -> {
            if (!captchaPassed) {
                piece1.setBorder(new LineBorder(Color.WHITE, 2));
                piece2.setBorder(new LineBorder(Color.WHITE, 2));
            }
            gridPanel.repaint();
        });
        timer.setRepeats(false);
        timer.start();

        gridPanel.repaint();
    }

    private void checkPuzzle() {
        Component[] components = gridPanel.getComponents();
        if (components.length != 4) return;

        boolean allCorrect = true;
        int tolerance = 15;

        for (Component comp : components) {
            JLabel piece = (JLabel) comp;
            int originalIndex = (int) piece.getClientProperty("originalIndex");

            int targetX = correctPositions[originalIndex][0];
            int targetY = correctPositions[originalIndex][1];

            int currentX = piece.getX();
            int currentY = piece.getY();

            if (Math.abs(currentX - targetX) > tolerance ||
                    Math.abs(currentY - targetY) > tolerance) {
                allCorrect = false;
                break;
            }
        }

        if (allCorrect) {
            // Пазл собран!
            captchaPassed = true;
            statusLabel.setText("✓ Пазл собран правильно! Нажмите 'Войти'");
            statusLabel.setForeground(Color.GREEN);
            enterButton.setEnabled(true);
            enterButton.setBackground(new Color(46, 204, 113));

            // Фиксируем фрагменты
            for (Component comp : gridPanel.getComponents()) {
                JLabel piece = (JLabel) comp;
                int originalIndex = (int) piece.getClientProperty("originalIndex");
                int x = correctPositions[originalIndex][0];
                int y = correctPositions[originalIndex][1];
                piece.setLocation(x, y);
                piece.setBorder(new LineBorder(Color.GREEN, 3));

                // Удаляем слушатели
                for (MouseListener ml : piece.getMouseListeners()) {
                    piece.removeMouseListener(ml);
                }
                piece.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            gridPanel.setBackground(new Color(0, 100, 0, 40));
            gridPanel.repaint();

            System.out.println("✓ ПАЗЛ СОБРАН ПРАВИЛЬНО!");
        }
    }

    public boolean isCaptchaPassed() {
        return captchaPassed;
    }

    public void resetCaptcha() {
        captchaPassed = false;
        selectedPiece = null;
        enterButton.setEnabled(false);
        enterButton.setBackground(new Color(52, 152, 219));
        gridPanel.setBackground(new Color(60, 60, 60));
        initializeCaptcha();

        System.out.println("=== КАПЧА СБРОШЕНА, НОВАЯ РАНДОМИЗАЦИЯ ===");
    }
}