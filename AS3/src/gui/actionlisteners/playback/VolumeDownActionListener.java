package gui.actionlisteners.playback;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class VolumeDownActionListener implements ActionListener {
	
	private EmbeddedMediaPlayer video;
	private JSlider volSlider;
	
	public VolumeDownActionListener(EmbeddedMediaPlayer video, JSlider volSlider) {
		this.video = video;
		this.volSlider = volSlider;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (video.getVolume() > 0) {
			volSlider.setValueIsAdjusting(true);
			volSlider.setValue(video.getVolume() - 10);
		}
	}

}
