import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class Filter extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Filter frame = new Filter();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public Filter() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 750, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		File f = new File("old.jpg");
		BufferedImage image = ImageIO.read(f);

		JLabel lblNewLabel = new JLabel(new ImageIcon(image));
		// lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(12, 12, 500, 500);
		contentPane.add(lblNewLabel);

		JButton btnRed = new JButton("Bright");
		btnRed.setBounds(580, 103, 156, 25);
		contentPane.add(btnRed);
		btnRed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int xx = 0; xx < image.getWidth(); xx++) {
					for (int yy = 0; yy < image.getHeight(); yy++) {
						int pixel = image.getRGB(xx, yy);

						int red = (pixel >> 16) & 0x0ff;
						int green = (pixel >> 8) & 0x0ff;
						int blue = (pixel) & 0x0ff;
						// System.out.println(red+" "+ green+" "+blue);

						int nred = (red + 50) > 255 ? 255 : (red + 50);
						int ngreen = (green + 50) > 255 ? 255 : (green + 50);
						int nblue = (blue + 50) > 255 ? 255 : (blue + 50);
						image.setRGB(xx, yy, new Color(nred, ngreen, nblue).getRGB());

					}
				}
				System.out.println(new Color(10, 20, 50).getRGB());
				// BufferedImage imagez = new BufferedImage(imagex.getWidth(),
				// imagex.getHeight(), BufferedImage.TYPE_INT_RGB);
				// lblNewLabel.paint(imagex.getGraphics());
				lblNewLabel.setIcon(new ImageIcon(image));
				// lblNewLabel.setBounds(12, 12, 500, 500);
				// JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(imagex)));
//				try {
//					ImageIO.write(image, "png", new File("imagex.png"));
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}

			}
		});

		JButton btnOne = new JButton("Black and White");
		btnOne.setBounds(580, 140, 154, 25);
		contentPane.add(btnOne);
		btnOne.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int xx = 0; xx < image.getWidth() - 2; xx += 2) {
					for (int yy = 0; yy < image.getHeight() - 2; yy += 2) {
						int pixel = image.getRGB(xx, yy);

						int red = (pixel >> 16) & 0x0ff;
						int green = (pixel >> 8) & 0x0ff;
						int blue = (pixel) & 0x0ff;
						// System.out.println(red+" "+ green+" "+blue);
						image.setRGB(xx + 1, yy, new Color(red, green, blue).getRGB());
						image.setRGB(xx, yy + 1, new Color(red, green, blue).getRGB());
						image.setRGB(xx + 1, yy + 1, new Color(red, green, blue).getRGB());
					}

				}
				lblNewLabel.setIcon(new ImageIcon(image));
			}
		});

		JButton btnTwo = new JButton("Cold");
		btnTwo.setBounds(580, 177, 158, 25);
		contentPane.add(btnTwo);

		btnTwo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int xx = 0; xx < image.getWidth(); xx++) {
					for (int yy = 0; yy < image.getHeight(); yy++) {
						int pixel = image.getRGB(xx, yy);

						int red = (pixel >> 16) & 0x0ff;
						int green = (pixel >> 8) & 0x0ff;
						int blue = (pixel) & 0x0ff;

						if (blue < 250) {
							image.setRGB(xx, yy, new Color(red, green, blue+50>255?255:blue+50).getRGB());

						}

					}

				}
				lblNewLabel.setIcon(new ImageIcon(image));

			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(624, 453, 114, 25);
		contentPane.add(btnSave);

		JToggleButton tglbtnMonochrome = new JToggleButton("Monochrome");
		tglbtnMonochrome.setBounds(580, 209, 162, 25);
		contentPane.add(tglbtnMonochrome);

		JButton redbt = new JButton("Hot");
		redbt.setBounds(580, 66, 156, 25);
		contentPane.add(redbt);

		JButton outbtn = new JButton("Cool");
		outbtn.setBounds(576, 242, 162, 25);
		contentPane.add(outbtn);

		outbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int xx = 1; xx < image.getWidth() - 5; xx += 5) {
					for (int yy = 1; yy < image.getHeight() - 5; yy += 5) {
						int pixel = image.getRGB(xx - 1, yy - 1);

						int red = (pixel >> 16) & 0x0ff;
						int green = (pixel >> 8) & 0x0ff;
						int blue = (pixel) & 0x0ff;

						image.setRGB(xx, yy, new Color(green, blue, red).getRGB());
						image.setRGB(xx + 1, yy + 1, new Color(green, blue, red).getRGB());
						image.setRGB(xx + 2, yy + 2, new Color(green, blue, red).getRGB());
						image.setRGB(xx + 3, yy + 3, new Color(green, blue, red).getRGB());

					}

				}
				lblNewLabel.setIcon(new ImageIcon(image));

			}
		});

		redbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int xx = 0; xx < image.getWidth(); xx++) {
					for (int yy = 0; yy < image.getHeight(); yy++) {
						int pixel = image.getRGB(xx, yy);

						int red = (pixel >> 16) & 0x0ff;
						int green = (pixel >> 8) & 0x0ff;
						int blue = (pixel) & 0x0ff;

						image.setRGB(xx, yy, new Color((red + 30 > 255 ? 255 : red + 30), green, blue).getRGB());

					}

				}
				lblNewLabel.setIcon(new ImageIcon(image));

			}
		});

		tglbtnMonochrome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JToggleButton tBtn = (JToggleButton) e.getSource();
				BufferedImage imagez = new BufferedImage(image.getWidth(), image.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				// JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(imagez)));
				// lblNewLabel.paint(imagez.getGraphics());
				if (tBtn.isSelected()) {
					for (int xx = 0; xx < image.getWidth(); xx++) {
						for (int yy = 0; yy < image.getHeight(); yy++) {
							int pixel = image.getRGB(xx, yy);

							int red = (pixel >> 16) & 0x0ff;
							int green = (pixel >> 8) & 0x0ff;
							int blue = (pixel) & 0x0ff;

							if ((red * green * blue) / 2 > 1953125) {

								imagez.setRGB(xx, yy, new Color(255, 255, 255).getRGB());
							} else {
								imagez.setRGB(xx, yy, new Color(0, 0, 0).getRGB());
							}
						}

					}
					lblNewLabel.setIcon(new ImageIcon(imagez));
				} else {
					lblNewLabel.setIcon(new ImageIcon(image));
				}
			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedImage new_image = new BufferedImage(lblNewLabel.getIcon().getIconWidth(),
						lblNewLabel.getIcon().getIconHeight(), BufferedImage.TYPE_INT_RGB);
				try {
					ImageIO.write(new_image, "png", new File("new_image.png"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
	}
}
