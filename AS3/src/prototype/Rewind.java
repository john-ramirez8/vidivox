package prototype;

import javax.swing.SwingWorker;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class Rewind extends SwingWorker<Void, Void> {

	private float rate;
	private EmbeddedMediaPlayer video;
	Rewind(EmbeddedMediaPlayer video, float rate) {
		this.video = video;
		this.rate = rate;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		while (!isCancelled()) {
			video.skip(-20);
			Thread.sleep(1);
		}
		return null;
	}

}
