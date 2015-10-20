package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;

import gui.VideoWindow;
import helpers.Main;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class TimerActionListener implements ActionListener {

	private JSlider vidProgress;
	private JLabel currentTime;
	private JSlider volSlider;
	private EmbeddedMediaPlayer video;
	private JButton btnMute;
	private JLabel length;
	private VideoWindow vw;
	
	public TimerActionListener(JSlider vidProgress, JLabel currentTime, JSlider volSlider,
			EmbeddedMediaPlayer video, JButton btnMute, JLabel length, VideoWindow vw) {
		this.vidProgress = vidProgress;
		this.currentTime = currentTime;
		this.volSlider = volSlider;
		this.video = video;
		this.btnMute = btnMute;
		this.length = length;
		this.vw = vw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String time = vw.calculateTime((int) video.getTime());
		String totalTime = length.getText();
		String[] splitTime = time.split(":");
		String[] splitTotalTime = totalTime.split(":");
		
		vidProgress.setValue((int) video.getTime());
		
		int totalCurrentTime = Integer.parseInt(splitTime[0])*60 + Integer.parseInt(splitTime[1]);
		int totalFullTime = Integer.parseInt(splitTotalTime[0])*60 + Integer.parseInt(splitTotalTime[1]);
		
		if (totalCurrentTime > totalFullTime) {
			currentTime.setText(totalTime);
		} else {
			currentTime.setText(time);
		}
		
		volSlider.setValue(video.getVolume());
		if (video.isMute() == true) {
			btnMute.setIcon(new ImageIcon(Main.class.getResource("/images/muted.png")));
		} else {
			btnMute.setIcon(new ImageIcon(Main.class.getResource("/images/unmuted.png")));
		}

	}

}
