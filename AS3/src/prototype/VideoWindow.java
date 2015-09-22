package prototype;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class VideoWindow extends JFrame {

	private String vidPath;
	private WindowManager manager;
	private JPanel contentPane;
	private FastForward ffTask;
	private Rewind rwTask;
	
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private final EmbeddedMediaPlayer video;

	public VideoWindow(String path) {
		// JFrames to open
		final AddAudio aA = new AddAudio();
		final AddVoice aV = new AddVoice();

		// Setting up the contentPane & JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setTitle("VidiVox Prototype");
		setMinimumSize(new Dimension (500,421));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 10, 0));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// Setting up media components
		vidPath = path;
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
		final JButton btnAddAudio = new JButton("Add Audio");
		final JButton btnAddVoice = new JButton("Add Voice");
		final JButton btnPlay = new JButton();
		final JButton btnRewind = new JButton("<<<");
		final JButton btnFastForward = new JButton(">>>");
		final JButton btnVolUp = new JButton();
		final JButton btnVolDown = new JButton();
		final JButton btnMute = new JButton();
		final JSlider volSlider = new JSlider();
        final JProgressBar vidProgress = new JProgressBar(0, 0);
        final JLabel length = new JLabel();
        final JLabel currentTime = new JLabel();
        
		// Setting up the nested panels
		buttonsPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		buttonsPane.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		addPane.setLayout(new GridLayout(2, 1, 0, 10));
		
		playbackButtonPane.setLayout(new BorderLayout(5, 0));
		
		playbackPane.setLayout(new BorderLayout());
		
		volumePane.setLayout(new BorderLayout());
		
		progressPane.setLayout(new BorderLayout(10,0));
        progressPane.setBorder(new EmptyBorder(10, 10, 0, 10));
		
		//Setting up video progress timer
        Timer timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vidProgress.setMaximum((int) video.getLength());
				int lengthS = (int) (video.getLength()/1000);
				int lengthM = lengthS/60;
				lengthS = lengthS - lengthM * 60;
				if (lengthS < 10){
					length.setText(lengthM + ":" + "0" + lengthS);
				} else {
					length.setText(lengthM + ":" + lengthS);
				}
				int currentS = (int) video.getTime()/1000;
				int currentM = currentS/60;
				currentS = currentS - currentM*60;
				if (currentS < 10){
					currentTime.setText(currentM + ":" + "0" + currentS);
				} else {
					currentTime.setText(currentM + ":" + currentS);
				}
				vidProgress.setValue((int) video.getTime());
			}
		}); 
        timer.start();
		
		//Setting up a menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem openFile = new JMenuItem("Open file...");
		JMenuItem saveFile = new JMenuItem("Save file as ...");
		JMenuItem close = new JMenuItem("Exit program");
		menu.add(openFile);
		menu.addSeparator();
		menu.add(saveFile);
		menu.addSeparator();
		menu.add(close);
		menuBar.add(menu);
		
		//Setting up openFile option
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File vid  = fo.openFile();
				if (vid != null){
					vidPath = vid.getAbsolutePath();
					video.playMedia(vidPath);
				}
			}
		});
		
		//Setting up savefile option
		saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Works");
			}
		});
		
		//Setting up close option
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Setting up the add audio button
		btnAddAudio.setPreferredSize(new Dimension (140,29));
		btnAddAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aA);
			}
		});

		// Setting up the add voice button
		btnAddVoice.setPreferredSize(new Dimension (140,29));
		btnAddVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.openWindow(aV);
			}
		});

		// Setting up the play/pause button
		btnPlay.setIcon(new ImageIcon("Images/pause.png"));
		btnPlay.setPreferredSize(new Dimension(80, 80));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (video.isPlaying() == true){
					btnPlay.setIcon(new ImageIcon("Images/play.png"));
				} else {
					btnPlay.setIcon(new ImageIcon("Images/pause.png"));
				}
				video.pause();
			}
		});

		// Setting up the rewind button
		btnRewind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnRewind.getText().equals("<<<")) {
					rwTask = new Rewind(video, video.getRate());
					rwTask.execute();
					btnRewind.setText("Play");
				} else {
					rwTask.cancel(true);
					btnRewind.setText("<<<");
				}
			}
		});

		// Setting up the fast forward button
		btnFastForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnFastForward.getText().equals(">>>")) {
					ffTask = new FastForward(video, video.getRate());
					ffTask.execute();
					btnFastForward.setText("Play");
				} else {
					ffTask.cancel(true);
					btnFastForward.setText(">>>");
				}
			}
		});
		
		// Setting up the mute button
		btnMute.setPreferredSize(new Dimension(35, 35));
		btnMute.setIcon(new ImageIcon("Images/unmuted.png"));
		btnMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(video.isMute() == true){
					btnMute.setIcon(new ImageIcon("Images/unmuted.png"));
					volSlider.setEnabled(true);
				} else {
					btnMute.setIcon(new ImageIcon("Images/muted.png"));
					volSlider.setEnabled(false);
				}
				video.mute();
			}
		});
		
		// Setting up the volume up button
		btnVolUp.setIcon(new ImageIcon("Images/volUp.png"));
		btnVolUp.setPreferredSize(new Dimension(35, 35));
		btnVolUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (video.getVolume() < 100){
					volSlider.setValue(video.getVolume() + 10);
				}
			}
		});
		
		// Setting up the volume down button
		btnVolDown.setIcon(new ImageIcon("Images/volDown.png"));
		btnVolDown.setPreferredSize(new Dimension(35, 35));
		btnVolDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (video.getVolume() > 0){
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
		volSlider.setPreferredSize(new Dimension(100,50));
		volSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				video.setVolume(volSlider.getValue());
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
		video.playMedia(vidPath);
	}
}
