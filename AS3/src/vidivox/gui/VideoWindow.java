package vidivox.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import vidivox.Main;
import vidivox.actionlisteners.*;
import vidivox.actionlisteners.playback.*;
import vidivox.eventadapters.PlayingEventAdapter;
import vidivox.helpers.*;
import vidivox.swingworker.*;

import java.awt.event.*;

/**
 * This class is the main JFrame that contains all the GUI of the video player.
 * @author John Ramirez (jram948)
 * 
 */
@SuppressWarnings("serial")
public class VideoWindow extends JFrame {

	//Declaring useful variables
	private String vidPath;
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

		//Setting up the contentPane & JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setTitle("Vidivox");
		setMinimumSize(new Dimension(648, 512));
				
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 10, 0));
		contentPane.setLayout(new BorderLayout());
		
		//Setting up media components
		mpc = new EmbeddedMediaPlayerComponent();
		video = mpc.getMediaPlayer();
		mpc.setPreferredSize(new Dimension(854, 480));

		//JPanel to open
		final AudioPanel audioPanel = new AudioPanel(this);
		audioPanel.setVisible(false);
		
		final FileOpener fo = new FileOpener(".avi", this);

		//Component declarations
		JPanel volumePane = new JPanel();
		JPanel volumeButtons = new JPanel();
		JPanel playbackButtonPane = new JPanel();
		JPanel playbackPane = new JPanel();
		JPanel buttonsPane = new JPanel();
		JPanel progressPane = new JPanel();
		JPanel audioPane = new JPanel();
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
		PlayingEventAdapter mediaListener = new PlayingEventAdapter(vidProgress, length, btnPlay, video, this);
		
		//Setting up the nested panels
		buttonsPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonsPane.setBorder(new EmptyBorder(10, 0, 0, 0));

		playbackButtonPane.setLayout(new BorderLayout(5, 0));

		playbackPane.setLayout(new BorderLayout());
		playbackPane.setBackground(Color.BLACK);

		volumePane.setLayout(new BorderLayout());

		progressPane.setLayout(new BorderLayout(10, 0));
		progressPane.setBorder(new EmptyBorder(10, 10, 0, 10));
		
		audioPane.setLayout(new BorderLayout());

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

		//Setting up the play/pause button
		btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/pause.png")));
		btnPlay.setPreferredSize(new Dimension(80, 80));
		btnPlay.addActionListener(playAL);

		//Setting up the rewind button
		btnRewind.setIcon(new ImageIcon(Main.class.getResource("/images/rw.png")));
		btnRewind.setPreferredSize(new Dimension(80, 80));
		btnRewind.setContentAreaFilled(false);
		btnRewind.addActionListener(rewindAL);

		//Setting up the fast forward button
		btnFastForward.setIcon(new ImageIcon(Main.class.getResource("/images/ff.png")));
		btnFastForward.setPreferredSize(new Dimension(80, 80));
		btnFastForward.setContentAreaFilled(false);
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

		JButton audioOptionsBtn = new JButton("Audio Options");
		audioOptionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (audioPanel.isVisible()) {
					audioPanel.setVisible(false);
				} else {
					audioPanel.setVisible(true);
				}
			}
		});
		
		//Adding all components to panels		
		playbackButtonPane.add(btnRewind, BorderLayout.WEST);
		playbackButtonPane.add(btnPlay, BorderLayout.CENTER);
		playbackButtonPane.add(btnFastForward, BorderLayout.EAST);

		volumePane.add(volSlider, BorderLayout.CENTER);
		volumePane.add(volumeButtons, BorderLayout.SOUTH);
		
		audioPane.add(volumePane, BorderLayout.NORTH);
		audioPane.add(audioOptionsBtn, BorderLayout.SOUTH);

		volumeButtons.add(btnVolDown);
		volumeButtons.add(btnVolUp);
		volumeButtons.add(btnMute);

		buttonsPane.add(playbackButtonPane);
		buttonsPane.add(audioPane);

		progressPane.add(length, BorderLayout.EAST);
		progressPane.add(currentTime, BorderLayout.WEST);
		progressPane.add(vidProgress, BorderLayout.CENTER);

		playbackPane.add(mpc, BorderLayout.CENTER);
		playbackPane.add(progressPane, BorderLayout.SOUTH);

		contentPane.add(buttonsPane, BorderLayout.SOUTH);
		contentPane.add(playbackPane, BorderLayout.CENTER);

		//Adding the main components to the frame
		add(menuBar, BorderLayout.NORTH);
		add(contentPane, BorderLayout.CENTER);
		add(audioPanel, BorderLayout.EAST);

		pack();

		//Playing video specified
		video.setRepeat(true);
		video.startMedia(path);
		video.mute(false);
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
	 * @param b - boolean deciding whether to enable/disable the volume buttons/slider.
	 */
	public void setEnableAudioCont(boolean b) {
		volSlider.setEnabled(b);
		btnMute.setEnabled(b);
		btnVolUp.setEnabled(b);
		btnVolDown.setEnabled(b);
	}
	
	/**
	 * This method enables/disables the various playback buttons.
	 * @param b - boolean deciding whether to enable/disable the buttons.
	 */
	public void setEnableButtons(boolean b) {
		volSlider.setEnabled(b);
		btnMute.setEnabled(b);
		btnVolUp.setEnabled(b);
		btnVolDown.setEnabled(b);
		btnRewind.setEnabled(b);
		btnFastForward.setEnabled(b);
	}

	//Getters for the video and video path
	public EmbeddedMediaPlayer getVideo() { return video; }
	public String getVideoPath() { return vidPath; }
}
