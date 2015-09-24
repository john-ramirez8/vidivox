package swingworker;

import javax.swing.SwingWorker;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class Rewind extends SwingWorker<Void, Void> {

	private EmbeddedMediaPlayer video;
	
	public Rewind(EmbeddedMediaPlayer video) {
		this.video = video;
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
