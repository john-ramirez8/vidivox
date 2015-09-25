package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import helpers.FileOpener;
import helpers.Main;
import helpers.WindowManager;
import swingworker.FastForward;
import swingworker.Rewind;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class VideoWindow extends JFrame {

	// Declaring useful variables
	private String vidPath;
	private WindowManager manager;
	private JPanel contentPane;
	private FastForward ffTask;
	private Rewind rwTask;
	private String playbackStatus = "normal";
	private String volumeStatus = "unmuted";

	// Declared audio control components below because a method needed to use
	// them
	private final JButton btnVolUp = new JButton();
	private final JButton btnVolDown = new JButton();
	private final JButton btnMute = new JButton();
	private final JSlider volSlider = new JSlider();

	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private final EmbeddedMediaPlayer video;

	public VideoWindow(String path) throws Exception {

		vidPath = path;

		// JFrames to open
		final AddAudio aA = new AddAudio(vidPath);
		final AddVoice aV = new AddVoice();

		// Setting up the contentPane & JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setTitle("VidiVox Prototype");
		setMinimumSize(new Dimension(550, 512));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 10, 0));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// Setting up media components
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		video = mediaPlayerComponent.getMediaPlayer();
		mediaPlayerComponent.setPreferredSize(new Dimension(854, 480));

		final FileOpener fo = new FileOpener(".avi", this);

		// Setting up WindowMananger
		manager = new WindowManager(contentPane);

		// Component declarations
		JPanel addPane = new JPanel();
		JPanel volumePane = new JPanel();
		JPanel volumeButtons = new JPanel();
		JPanel playbackButtonPane = new JPanel();
		JPanel playbackPane = new JPanel();
		JPanel buttonsPane = new JPanel();
		JPanel progressPane = new JPanel();
		final JButton btnAddAudio = new JButton("Add Audio MP3");
		final JButton btnAddVoice = new JButton("Create Voice MP3");
		final JButton btnPlay = new JButton();
		final JButton btnRewind = new JButton();
		final JButton btnFastForward = new JButton();
		final JSlider vidProgress = new JSlider();
		final JLabel length = new JLabel();
		final JLabel currentTime = new JLabel();

		// Setting up the nested panels
		buttonsPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonsPane.setBorder(new EmptyBorder(10, 0, 0, 0));

		addPane.setLayout(new GridLayout(2, 1, 0, 10));

		playbackButtonPane.setLayout(new BorderLayout(5, 0));

		playbackPane.setLayout(new BorderLayout());
		playbackPane.setBackground(Color.BLACK);

		volumePane.setLayout(new BorderLayout());

		progressPane.setLayout(new BorderLayout(10, 0));
		progressPane.setBorder(new EmptyBorder(10, 10, 0, 10));

		// Setting up video progress timer
		Timer timer = new Timer(200, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vidProgress.setValue((int) video.getTime());
				currentTime.setText(calculateTime((int) video.getTime()));
				volSlider.setValue(video.getVolume());
				if (video.isMute() == true) {
					btnMute.setIcon(new ImageIcon(Main.class.getResource("/images/muted.png")));
				} else {
					btnMute.setIcon(new ImageIcon(Main.class.getResource("/images/unmuted.png")));
				}

			}
		});
		timer.start();

		// Setting up the video progress slider
		vidProgress.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (vidProgress.getValueIsAdjusting()) {
					video.setTime(vidProgress.getValue());
				}
			}
		});

		// Setting up a menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem openFile = new JMenuItem("Open file...");
		JMenuItem close = new JMenuItem("Exit program");
		menu.add(openFile);
		menu.add(close);
		menuBar.add(menu);

		// Setting up openFile option
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File vid = fo.openFile();
				if (vid != null) {
					mediaPlayerComponent.setVisible(true);
					vidPath = vid.getAbsolutePath();
					video.playMedia(vidPath);
					if (playbackStatus.equals("ff") == true) {
						ffTask.cancel(true);
						playbackStatus = "normal";
					} else if (playbackStatus.equals("rw") == true) {
						rwTask.cancel(true);
						playbackStatus = "normal";
					}
				}
			}
		});

		// Setting up close option
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Setting up the add audio button
		btnAddAudio.setPreferredSize(new Dimension(140, 29));
		btnAddAudio.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aA);
			}
		});

		// Setting up the add voice button
		btnAddVoice.setPreferredSize(new Dimension(140, 29));
		btnAddVoice.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aV);
			}
		});

		// Setting up the play/pause button
		btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/pause.png")));
		btnPlay.setPreferredSize(new Dimension(80, 80));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (video.isPlaying() == true) {
					btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/play.png")));
				} else {
					btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/pause.png")));
					if (playbackStatus.equals("ff")) {
						ffTask.cancel(true);
						if (volumeStatus.equals("muted") == false) {
							video.mute(false);
						}
						playbackStatus = "normal";
						setEnableAudioCont(true);
					} else if (playbackStatus.equals("rw")) {
						rwTask.cancel(true);
						if (volumeStatus.equals("muted") == false) {
							video.mute(false);
						}
						playbackStatus = "normal";
						setEnableAudioCont(true);
					}
				}
				video.pause();
			}
		});

		// Setting up the rewind button
		btnRewind.setIcon(new ImageIcon(Main.class.getResource("/images/rw.png")));
		btnRewind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnableAudioCont(false);
				if (playbackStatus.equals("normal") == true) {
					playbackStatus = "rw";
					video.mute(true);
					rwTask = new Rewind(video);
					rwTask.execute();
					video.pause();
					btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/play.png")));
				} else if (playbackStatus.equals("ff") == true) {
					playbackStatus = "rw";
					video.mute(true);
					rwTask = new Rewind(video);
					ffTask.cancel(true);
					rwTask.execute();
				}
			}
		});

		// Setting up the fast forward button
		btnFastForward.setIcon(new ImageIcon(Main.class.getResource("/images/ff.png")));
		btnFastForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnableAudioCont(false);
				if (playbackStatus.equals("normal") == true) {
					playbackStatus = "ff";
					video.mute(true);
					ffTask = new FastForward(video);
					ffTask.execute();
					video.pause();
					btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/play.png")));
				} else if (playbackStatus.equals("rw") == true) {
					playbackStatus = "ff";
					video.mute(true);
					ffTask = new FastForward(video);
					rwTask.cancel(true);
					ffTask.execute();
				}
			}
		});

		// Setting up the mute button
		btnMute.setPreferredSize(new Dimension(35, 35));
		btnMute.setIcon(new ImageIcon(Main.class.getResource("/images/unmuted.png")));
		btnMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (volumeStatus.equals("unmuted") == true) {
					video.mute(true);
					volumeStatus = "muted";
				} else {
					video.mute(false);
					volumeStatus = "unmuted";
				}
			}
		});

		// Setting up the volume up button
		btnVolUp.setIcon(new ImageIcon(Main.class.getResource("/images/volUp.png")));
		btnVolUp.setPreferredSize(new Dimension(35, 35));
		btnVolUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (video.getVolume() < 100) {
					volSlider.setValueIsAdjusting(true);
					volSlider.setValue(video.getVolume() + 10);
				}
			}
		});

		// Setting up the volume down button
		btnVolDown.setIcon(new ImageIcon(Main.class.getResource("/images/volDown.png")));
		btnVolDown.setPreferredSize(new Dimension(35, 35));
		btnVolDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (video.getVolume() > 0) {
					volSlider.setValueIsAdjusting(true);
					volSlider.setValue(video.getVolume() - 10);
				}
			}
		});

		// Setting up the volume slider
		volSlider.setValue(100);
		volSlider.setMaximum(100);
		volSlider.setMinimum(0);
		volSlider.setMinorTickSpacing(10);
		volSlider.setPaintTicks(true);
		volSlider.setSnapToTicks(true);
		volSlider.setPreferredSize(new Dimension(100, 50));
		volSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (volSlider.getValueIsAdjusting()) {
					video.setVolume(volSlider.getValue());
					video.mute(false);
				}
			}
		});

		// Adding a video playing event listener
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			@Override
			public void playing(MediaPlayer mediaPlayer) {
				// Initializing progress bar and setting time stamps
				vidProgress.setMaximum((int) video.getLength());
				// invokes calculateTime() function to get time in 00:00 format
				length.setText(calculateTime((int) video.getLength()));
				btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/pause.png")));

			}
		});

		// Adding a video finished event listener
		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			@Override
			public void finished(MediaPlayer mediaPlayer) {
				mediaPlayerComponent.setVisible(false);
				btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/play.png")));
				JOptionPane.showMessageDialog(contentPane, "Video has finished playing");
			}
		});

		// Adding all components to the panel
		addPane.add(btnAddVoice);
		addPane.add(btnAddAudio);

		playbackButtonPane.add(btnRewind, BorderLayout.WEST);
		playbackButtonPane.add(btnPlay, BorderLayout.CENTER);
		playbackButtonPane.add(btnFastForward, BorderLayout.EAST);

		volumePane.add(volSlider, BorderLayout.NORTH);
		volumePane.add(volumeButtons, BorderLayout.CENTER);

		volumeButtons.add(btnVolUp);
		volumeButtons.add(btnVolDown);
		volumeButtons.add(btnMute);

		buttonsPane.add(addPane);
		buttonsPane.add(playbackButtonPane);
		buttonsPane.add(volumePane);

		progressPane.add(length, BorderLayout.EAST);
		progressPane.add(currentTime, BorderLayout.WEST);
		progressPane.add(vidProgress, BorderLayout.CENTER);

		playbackPane.add(mediaPlayerComponent, BorderLayout.CENTER);
		playbackPane.add(progressPane, BorderLayout.SOUTH);

		contentPane.add(buttonsPane, BorderLayout.SOUTH);
		contentPane.add(playbackPane, BorderLayout.CENTER);
		contentPane.add(menuBar, BorderLayout.NORTH);

		pack();

		// Playing video specified
		video.startMedia(path);
	}

	// Calculates the time stamp for the video converts int milliseconds into
	// 00:00 string format
	private String calculateTime(int time) {
		int lengthS = time / 1000;
		int lengthM = lengthS / 60;
		lengthS = lengthS - lengthM * 60;
		if (lengthS < 10) {
			return lengthM + ":" + "0" + lengthS;
		} else {
			return lengthM + ":" + lengthS;
		}
	}

	private void setEnableAudioCont(boolean b) {
		volSlider.setEnabled(b);
		btnMute.setEnabled(b);
		btnVolUp.setEnabled(b);
		btnVolDown.setEnabled(b);

	}
}
