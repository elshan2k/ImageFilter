import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
public class Filter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	File f = new File("old.jpg");
	BufferedImage image = ImageIO.read(f);
	int count =1;
	JLabel lblNewLabel;
	
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
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			f = new File(selectedFile.getAbsolutePath());
			image = ImageIO.read(f);
		}
		
		lblNewLabel = new JLabel(new ImageIcon(image));
		lblNewLabel.setBounds(50, 50, 500, 500);
		contentPane.add(lblNewLabel);
		
		
//////////////////////////////------The Brightest Part------///////////////////////////////////////
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
				lblNewLabel.setIcon(new ImageIcon(image));

			}
		});

//////////////////////////////------Different Part------///////////////////////////////////////
		JButton btnOne = new JButton("Different");
		btnOne.setBounds(580, 140, 154, 25);
		contentPane.add(btnOne);
		btnOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int z = 5;
				for (int xx = 0; xx < image.getWidth() - 2; xx += 2) {
					for (int yy = 0; yy < image.getHeight() - 2; yy += 2) {
						int pixel = image.getRGB(xx, yy);

						int red = (pixel >> 16) & 0x0ff;
						int green = (pixel >> 8) & 0x0ff;
						int blue = (pixel) & 0x0ff;
						
						
						int nred = (red - red/z) <= 0 ? 0 : (red - red/z);
						int ngreen = (green + green/z) > 255 ? 255 : (green + green/z);
						int nblue = (blue - blue/z) <= 0 ? 0 : (blue - blue/z);
						image.setRGB(xx, yy, new Color(nred, ngreen, nblue).getRGB());
					}

				}
				lblNewLabel.setIcon(new ImageIcon(image));
			}
		});
		
		
//////////////////////////////------Cold Part------///////////////////////////////////////
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
							image.setRGB(xx, yy, new Color(red-20<0?0:red-20, green, blue+40>255?255:blue+40).getRGB());

						}

					}

				}
				lblNewLabel.setIcon(new ImageIcon(image));

			}
		});

	

		

//////////////////////////////------Coolest Part------///////////////////////////////////////

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
						
						int nred = (red >= 130 && red<245) ? red+10 : red;
						int ngreen = (green<20) ? 0 : (green-20);
						int nblue = (blue >= 120 && blue<245)? blue+10 : blue;

						image.setRGB(xx, yy, new Color(nred, ngreen, nblue).getRGB());

					}

				}
				lblNewLabel.setIcon(new ImageIcon(image));

			}
		});

		
//////////////////////////////------Red Part------///////////////////////////////////////
		JButton redbt = new JButton("Hot");
		redbt.setBounds(580, 66, 156, 25);
		contentPane.add(redbt);
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

		
//////////////////////////////------Monochrome Part------///////////////////////////////////////

		JToggleButton tglbtnMonochrome = new JToggleButton("Monochrome");
		tglbtnMonochrome.setBounds(580, 209, 162, 25);
		contentPane.add(tglbtnMonochrome);
		tglbtnMonochrome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JToggleButton tBtn = (JToggleButton) e.getSource();
				BufferedImage imagez = new BufferedImage(image.getWidth(), image.getHeight(),
						BufferedImage.TYPE_INT_RGB);
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
		
		
		
//////////////////////////////------Reset Part------///////////////////////////////////////

		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(622, 418, 114, 25);
		contentPane.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					image = ImageIO.read(f);
				} catch (IOException e1) {
					e1.printStackTrace();
				};
				lblNewLabel.setIcon(new ImageIcon(image));
			}
		});
		
		
		
		
		
//////////////////////////////------New Image Part------///////////////////////////////////////

		JButton btnNewImage = new JButton("New Image");
		btnNewImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					f = new File(selectedFile.getAbsolutePath());
					try {
						image = ImageIO.read(f);
						JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(image)));
						lblNewLabel.setIcon(new ImageIcon(image));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});

		btnNewImage.setBounds(622, 484, 114, 25);
		contentPane.add(btnNewImage);
		
		//////////////////////////////------Save Part------///////////////////////////////////////
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(624, 453, 114, 25);
		contentPane.add(btnSave);
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedImage new_image = new BufferedImage(lblNewLabel.getWidth(),
				lblNewLabel.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = new_image.createGraphics();
				lblNewLabel.printAll(g2d);
				g2d.dispose();
				try {
					ImageIO.write(image, "png", new File("new_image"+count+".png"));
					count++;
					JOptionPane.showMessageDialog(null, "Your image has been saved succefully."
							+ " You can reset and save new image as well");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
	}
}
