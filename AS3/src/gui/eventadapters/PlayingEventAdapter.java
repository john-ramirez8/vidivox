package gui.eventadapters;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import gui.VideoWindow;
import helpers.Main;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class PlayingEventAdapter extends MediaPlayerEventAdapter {
	
	private JSlider vidProgress;
	private JLabel length;
	private JButton btnPlay;
	private EmbeddedMediaPlayer video;
	private VideoWindow vw;
	private EmbeddedMediaPlayerComponent mpc;
	private JPanel contentPane;
	
	public PlayingEventAdapter(JSlider vidProgress, JLabel length, JButton btnPlay, EmbeddedMediaPlayer video, VideoWindow vw, EmbeddedMediaPlayerComponent mpc, JPanel contentPane) {
		this.vidProgress = vidProgress;
		this.length = length;
		this.btnPlay = btnPlay;
		this.video = video;
		this.vw = vw;
		this.mpc = mpc;
		this.contentPane = contentPane;
	}

	@Override
	public void playing(MediaPlayer mediaPlayer) {
		//Initializing progress bar and setting time stamps
		vidProgress.setMaximum((int) video.getLength());
		//Invokes calculateTime() function to get time in 00:00 format
		length.setText(vw.calculateTime((int) video.getLength()));
		btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/pause.png")));
	}

	@Override
	public void finished(MediaPlayer mediaPlayer) {
		mpc.setVisible(false);
		btnPlay.setIcon(new ImageIcon(Main.class.getResource("/images/play.png")));
		JOptionPane.showMessageDialog(contentPane, "Video has finished playing");
	}

}
