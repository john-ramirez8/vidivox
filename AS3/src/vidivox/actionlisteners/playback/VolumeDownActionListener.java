package vidivox.actionlisteners.playback;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * This class is used to decrease the volume of the video.
 * @author John Ramirez (jram948)
 *
 */
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
			//Decrements volume by 10
			volSlider.setValueIsAdjusting(true);
			volSlider.setValue(video.getVolume() - 10);
		}
	}

}
