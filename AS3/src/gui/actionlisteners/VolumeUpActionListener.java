package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/*
 * This class is made for the ActionListeners of the volume up button
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
			volSlider.setValueIsAdjusting(true);
			volSlider.setValue(video.getVolume() + 10);
		}
	}

}
