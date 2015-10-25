package gui.actionlisteners.playback;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * This class is used to increase the volume of the video.
 * @author John Ramirez (jram948)
 * 
 */
public class VolumeUpActionListener implements ActionListener {

	private EmbeddedMediaPlayer video;
	private JSlider volSlider;
	
	public VolumeUpActionListener(EmbeddedMediaPlayer video, JSlider volSlider) {
		this.video = video;
		this.volSlider = volSlider;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (video.getVolume() < 100) {
			//Increments volume by 10
			volSlider.setValueIsAdjusting(true);
			volSlider.setValue(video.getVolume() + 10);
		}
	}

}
