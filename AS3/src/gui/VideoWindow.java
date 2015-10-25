package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.actionlisteners.*;
import gui.actionlisteners.audio.AddAudioActionListener;
import gui.actionlisteners.playback.FastForwardActionListener;
import gui.actionlisteners.playback.MuteActionListener;
import gui.actionlisteners.playback.PlayActionListener;
import gui.actionlisteners.playback.RewindActionListener;
import gui.actionlisteners.playback.VolumeDownActionListener;
import gui.actionlisteners.playback.VolumeUpActionListener;
import gui.eventadapters.PlayingEventAdapter;
import helpers.FileOpener;
import helpers.Main;
import helpers.WindowManager;
import swingworker.FastForward;
import swingworker.Rewind;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VideoWindow extends JFrame {

	//Declaring useful variables
	private String vidPath;
	private WindowManager manager;
	private JPanel contentPane;
	public FastForward ffTask;
	public Rewind rwTask;
	public String playbackStatus = "normal";
	public String volumeStatus = "unmuted";

	//Declares the audio control buttons
	private final JButton btnVolUp = new JButton();
	private final JButton btnVolDown = new JButton();
	private final JButton btnMute = new JButton();
	private final JButton btnRewind = new JButton();
	private final JButton btnFastForward = new JButton();
	private final JSlider volSlider = new JSlider(0, 100, 100);

	private final EmbeddedMediaPlayerComponent mpc;
	private EmbeddedMediaPlayer video;

	public VideoWindow(String path) throws Exception {

		vidPath = path;

		//JFrames to open
		final AddVoice aV = new AddVoice();

		//Setting up the contentPane & JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setTitle("VidiVox Prototype");
		setMinimumSize(new Dimension(550, 512));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 10, 0));
		contentPane.setLayout(new BorderLayout());
		
		//Trying this out
		add(contentPane, BorderLayout.CENTER);

		//Setting up media components
		mpc = new EmbeddedMediaPlayerComponent();
		video = mpc.getMediaPlayer();
		mpc.setPreferredSize(new Dimension(854, 480));

		//JPanel to open
		final AudioPanel audioPanel = new AudioPanel(this);
		audioPanel.setVisible(false);
		add(audioPanel, BorderLayout.EAST);
		
		final FileOpener fo = new FileOpener(".avi", this);

		//Setting up WindowMananger
		manager = new WindowManager(contentPane);

		//Component declarations
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
		final JSlider vidProgress = new JSlider();
		final JLabel length = new JLabel();
		final JLabel currentTime = new JLabel();

		//Setting up a menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem openFile = new JMenuItem("Open file...");
		JMenuItem close = new JMenuItem("Exit program");
		menu.add(openFile);
		menu.add(close);
		menuBar.add(menu);	
		
		//Creating ActionListeners/EventListeners for various buttons
		ActionListener playAL = new PlayActionListener(video, btnPlay, this);
		ActionListener rewindAL = new RewindActionListener(btnPlay, video, this);
		ActionListener fastForwardAL = new FastForwardActionListener(video, btnPlay, this);
		ActionListener volUpAL = new VolumeUpActionListener(video, volSlider);
		ActionListener volDownAL = new VolumeDownActionListener(video, volSlider);
		ActionListener muteAL = new MuteActionListener(video, this);
		ActionListener timerAL = new TimerActionListener(vidProgress, currentTime, volSlider, video, btnMute, length, this);
		ActionListener fileAL = new FileOpenerActionListener(fo, mpc, video, this);
		ActionListener audioAL = new AddAudioActionListener(contentPane, vidPath, this, video);
		PlayingEventAdapter mediaListener = new PlayingEventAdapter(vidProgress, length, btnPlay, video, this);
		
		//Setting up the nested panels
		buttonsPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonsPane.setBorder(new EmptyBorder(10, 0, 0, 0));

		addPane.setLayout(new GridLayout(2, 1, 0, 10));

		playbackButtonPane.setLayout(new BorderLayout(5, 0));

		playbackPane.setLayout(new BorderLayout());
		playbackPane.setBackground(Color.BLACK);

		volumePane.setLayout(new BorderLayout());

		progressPane.setLayout(new BorderLayout(10, 0));
		progressPane.setBorder(new EmptyBorder(10, 10, 0, 10));

		//Setting up video progress timer
		Timer timer = new Timer(200, timerAL);
		timer.start();

		//Setting up the video progress slider
		vidProgress.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (vidProgress.getValueIsAdjusting()) {
					video.setTime(vidProgress.getValue());
				}
			}
		});

		//Setting up openFile option
		openFile.addActionListener(fileAL);

		//Setting up close option
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		//Setting up the add audio button
		btnAddAudio.setPreferredSize(new Dimension(140, 29));
		btnAddAudio.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddAudio.addActionListener(audioAL);

		//Setting up the add voice button
		btnAddVoice.setPreferredSize(new Dimension(140, 29));
		btnAddVoice.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aV);
			}
		});

		//Setting up the play/pause button
		btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/pause.png")));
		btnPlay.setPreferredSize(new Dimension(80, 80));
		btnPlay.addActionListener(playAL);

		//Setting up the rewind button
		btnRewind.setIcon(new ImageIcon(Main.class.getResource("/images/rw.png")));
		btnRewind.addActionListener(rewindAL);

		//Setting up the fast forward button
		btnFastForward.setIcon(new ImageIcon(Main.class.getResource("/images/ff.png")));
		btnFastForward.addActionListener(fastForwardAL);
		

		//Setting up the mute button
		btnMute.setPreferredSize(new Dimension(35, 35));
		btnMute.setIcon(new ImageIcon(Main.class.getResource("/images/unmuted.png")));
		btnMute.addActionListener(muteAL);

		//Setting up the volume up button
		btnVolUp.setIcon(new ImageIcon(Main.class.getResource("/images/volUp.png")));
		btnVolUp.setPreferredSize(new Dimension(35, 35));
		btnVolUp.addActionListener(volUpAL);

		//Setting up the volume down button
		btnVolDown.setIcon(new ImageIcon(Main.class.getResource("/images/volDown.png")));
		btnVolDown.setPreferredSize(new Dimension(35, 35));
		btnVolDown.addActionListener(volDownAL);

		//Setting up the volume slider
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

		//Adding a video event listener
		video.addMediaPlayerEventListener(mediaListener);

		//Adding all components to the panel
		addPane.add(btnAddVoice);
		addPane.add(btnAddAudio);
		
		playbackButtonPane.add(btnRewind, BorderLayout.WEST);
		playbackButtonPane.add(btnPlay, BorderLayout.CENTER);
		playbackButtonPane.add(btnFastForward, BorderLayout.EAST);

		volumePane.add(volSlider, BorderLayout.NORTH);
		volumePane.add(volumeButtons, BorderLayout.CENTER);

		volumeButtons.add(btnVolDown);
		volumeButtons.add(btnVolUp);
		volumeButtons.add(btnMute);

		buttonsPane.add(addPane);
		buttonsPane.add(playbackButtonPane);
		buttonsPane.add(volumePane);

		progressPane.add(length, BorderLayout.EAST);
		progressPane.add(currentTime, BorderLayout.WEST);
		progressPane.add(vidProgress, BorderLayout.CENTER);

		playbackPane.add(mpc, BorderLayout.CENTER);
		playbackPane.add(progressPane, BorderLayout.SOUTH);

		contentPane.add(buttonsPane, BorderLayout.SOUTH);
		contentPane.add(playbackPane, BorderLayout.CENTER);

		this.add(menuBar, BorderLayout.NORTH);
		
		JButton test = new JButton("Test");
		buttonsPane.add(test);
		test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (audioPanel.isVisible()) {
					audioPanel.setVisible(false);
				} else {
					audioPanel.setVisible(true);
				}
			}
		});
		
		pack();

		//Playing video specified
		video.setRepeat(true);
		video.startMedia(path);
	}


	/**
	 * This method calculates the time stamp for the video and converts
	 * the int milliseconds into 00:00 string format.
	 * @param time - the time to convert into 00:00 format
	 * @return a string of the time in 00:00 format
	 */
	public String calculateTime(int time) {
		int lengthS = time / 1000;
		int lengthM = lengthS / 60;
		lengthS = lengthS - lengthM * 60;
		if (lengthS < 10) {
			return lengthM + ":" + "0" + lengthS;
		} else {
			return lengthM + ":" + lengthS;
		}
	}
	
	/**
	 * This method enables/disables the multiple volume buttons/slider.
	 * @param b - boolean deciding whether to enable/disable the volume buttons/slider
	 */
	public void setEnableAudioCont(boolean b) {
		volSlider.setEnabled(b);
		btnMute.setEnabled(b);
		btnVolUp.setEnabled(b);
		btnVolDown.setEnabled(b);
	}
	
	public void setEnableButtons(boolean b) {
		volSlider.setEnabled(b);
		btnMute.setEnabled(b);
		btnVolUp.setEnabled(b);
		btnVolDown.setEnabled(b);
		btnRewind.setEnabled(b);
		btnFastForward.setEnabled(b);
	}
	
	public EmbeddedMediaPlayer getVideo() { return video; }
	public String getVideoPath() { return vidPath; }
}
