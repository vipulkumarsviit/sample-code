package demos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class DifferanceChecker {

	private JFrame frame;
	private final JTextArea textArea = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DifferanceChecker window = new DifferanceChecker();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DifferanceChecker() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("compare tool4");
		frame.setBounds(100, 100, 1144, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 48, 185, 265);
		frame.getContentPane().add(scrollPane);

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(222, 50, 183, 263);
		frame.getContentPane().add(scrollPane_1);

		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(434, 51, 302, 326);
		frame.getContentPane().add(scrollPane_2);

		JLabel level1 = new JLabel("script_1 diff");
		scrollPane_2.setViewportView(level1);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(763, 52, 302, 325);
		frame.getContentPane().add(scrollPane_3);

		JLabel level2 = new JLabel("script_2 diff");
		scrollPane_3.setViewportView(level2);

		JButton btnNewButton = new JButton("Compare");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				char[] str1Arr = textPane.getText().trim().toCharArray();
				char[] str2Arr = textPane_1.getText().trim().toCharArray();
				int count = 0;
				for (int i = 0; i < (str1Arr.length < str2Arr.length ? str1Arr.length : str2Arr.length); i++)
					if (str1Arr[i] == str2Arr[i])
						count++;
				level1.setText("<html>"
						+ textPane.getText().substring(0, count).replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						+ "<font color='red'>"
						+ textPane.getText().substring(count).replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						+ "</font></html>");
				level2.setText("<html>"
						+ textPane_1.getText().substring(0, count).replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						+ "<font color='green'>"
						+ textPane_1.getText().substring(count).replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						+ "</font></html>");
				level1.setToolTipText("Correlate");
				level2.setToolTipText("Correlate");

				try {
					Files.write(Paths.get("my-file.txt"), textPane.getText().getBytes(), StandardOpenOption.APPEND);
					Files.write(Paths.get("my-file1.txt"), textPane_1.getText().getBytes(), StandardOpenOption.APPEND);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

		scrollPane_2.getVerticalScrollBar().setModel(scrollPane_3.getVerticalScrollBar().getModel());
		scrollPane_2.getHorizontalScrollBar().setModel(scrollPane_3.getHorizontalScrollBar().getModel());

		btnNewButton.setBounds(269, 325, 136, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnCorrelateall = new JButton("CorrelateAll");
		btnCorrelateall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {

				String[] s4 = null;
				String[] s5 = null;
				String s6 = null;
				String s7 = null;

				String h = "", h1 = "";

				String leftboundary = "";
				String rightboundary = "";
				String rep = "", rep1 = "";
				textArea.setText(textPane_1.getText());
				String[] text = textArea.getText().split("\n");
				String[] s = textPane.getText().split("\n");
				String[] s1 = textPane_1.getText().split("\n");

				System.out.println("else block");

				List<String> aspx = new ArrayList<String>();
				List<String> aspx1 = new ArrayList<String>();
				String[] i;
				int ii = 0;
				System.out.println("Actual Lines---------");
				for (; ii < s.length; ii++) {
					String n1 = "", n2 = "";
					if (!s[ii].equals(s1[ii])) {
						// aspx.add(s[ii]);
						// aspx1.add(s1[ii]);
						s6 = s[ii];
						s7 = s1[ii];
						s4 = s6.split("");
						s5 = s7.split("");

						for (int i1 = 0; i1 < s4.length; i1++) {
							if (s4[i1].equals(s5[i1])) {
								System.out.println("true");
							} else {
								System.out.println("false");
								n1 += s4[i1];
								n2 += s5[i1];
								// System.out.println("name1==>"+n1+"name2==>"+n2);

							}

						}
						// }

						System.out.println("name1==>" + n1 + "name2==>" + n2);
						if (s1[ii].contains(n2)) {
							String[] boundary = s1[ii].split(n2);
							for (int j = 0; j < boundary.length; j++) {
								leftboundary = boundary[0].trim();
								rightboundary = boundary[1];

							}
						}
						System.out.println("left=>" + leftboundary + "  right=>" + rightboundary);

						try {
							FileInputStream fstream = new FileInputStream("uu.txt");
							DataInputStream in = new DataInputStream(fstream);
							BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
							String line;
							// System.out.println(line);
							while ((line = buffer.readLine()) != null) {
								if (line.contains(leftboundary)) {
									// process the line.
									System.out.println(line);

									Pattern pattern = Pattern.compile(leftboundary.trim());
									// Pattern
									// pattern1=Pattern.compile(rightboundary);
									// Pattern
									// pattern=Pattern.compile("Tarun(.*?)Singh");

									Matcher m = pattern.matcher(line);
									Matcher m1 = pattern.matcher(s1[ii]);

									while (m.find()) {
										rep = "";
										System.out.println(
												"while:->In file left boundary content is there at line number:");
										h = m.group();
										System.out.println("=>" + h + "--" + m.start() + "to" + m.end() + "om=>"
												+ line.charAt(m.end() + 1));

										for (int r = m.end(); r < m.end() + n1.length(); r++) {

											rep += line.charAt(r);

										}
									}

									while (m1.find()) {
										rep1 = "";
										System.out.println(
												"while2:->In second script also left boundary is there so extract and replace the value");
										h1 = m1.group();
										System.out.println("=>" + h1 + "--" + m1.start() + "to" + m1.end() + "om=>"
												+ s1[ii].charAt(m1.end() + 1));

										for (int r1 = m1.end(); r1 < m1.end() + n1.length(); r1++) {

											rep1 += s1[ii].charAt(r1);

										}
									}

									// s1[ii]=s1[ii].replace("{,},$,?","");
									if (!rep.equals(rep1)) {
										s1[ii] = s1[ii].trim().replace(rep1, rep);
										// level2.setText(s1[ii]);
										// if(textPane_1.)

										String g = s1[ii];
										// for(int y=0;y<text.length;y++)
										// {
										System.out.println(text.length + " " + s.length);
										if (text[ii] != s[ii]) {
											// int
											// ik=textArea.getCaretPosition();

											String f = text[ii];
											int start = textArea.getText().indexOf(f);
											if (start >= 0 && f.length() > 0)
												textArea.replaceRange(g, start, start + f.length());
											// textArea.setText("\n");
											// textArea.replaceSelection(s1[ii]);
										}

										// }

										System.out.println("dynamic value---->" + rep + "  script value----->" + rep1
												+ "\n original==>" + g);
									}
									// }
								} else {
									System.out.println("not there");
								}

								// break;
							}
							// n1="";
							// n2="";

						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// ii++;

					}

				}

				// }

			}
		});

		btnCorrelateall.setBounds(269, 354, 136, 23);
		frame.getContentPane().add(btnCorrelateall);

		JButton btnNewButton_1 = new JButton("close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(1071, 179, 76, 23);
		frame.getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(24, 346, 185, 67);
		frame.getContentPane().add(scrollPane_4);
		textArea.setText("finalscripe");
		scrollPane_4.setViewportView(textArea);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setUndecorated(true);
		frame.setVisible(true);

	}
}