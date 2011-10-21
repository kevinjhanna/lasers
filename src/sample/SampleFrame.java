package sample;

import gui.BoardPanel;
import gui.BoardPanelListener;
import gui.ImageUtils;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JFrame;

public class SampleFrame extends JFrame {

	private static final int CELL_SIZE = 30;

	private BoardPanel bp;
	private Image img1, img2;

	public SampleFrame() {
		setLayout(null);
		setSize(10 * CELL_SIZE + 40, 10 * CELL_SIZE + 40);

		try {
			img1 = ImageUtils.loadImage("resources/target.png");
			img2 = ImageUtils.replaceColor(img1, new Color(0, 0, 255), new Color(255, 0, 0));
		} catch (IOException e) {
			System.out.println("Error al cargar imagenes.");
		}
		
		bp = new BoardPanel(10, 10, CELL_SIZE);
		bp.setBackground(Color.WHITE);
		bp.setGridColor(Color.GRAY);
		bp.setListener(new BoardPanelListener() {
			public void cellClicked(int row, int column) {
				System.out.println("Clic en " + row + ", " + column);
				bp.setImage(row, column, img1);
				bp.repaint();
			}
			public void cellDragged(int sourceRow, int sourceColumn,
					int targetRow, int targetColumn) {
				System.out.println("Celda arrastrada desde " + sourceRow + ", "
						+ sourceColumn + " hasta " + targetRow + ", "
						+ targetColumn);
				bp.setImage(sourceRow, sourceColumn, img2);
				bp.setImage(targetRow, targetColumn, img2);
				bp.repaint();
			}
		});

		add(bp);

	}
}
