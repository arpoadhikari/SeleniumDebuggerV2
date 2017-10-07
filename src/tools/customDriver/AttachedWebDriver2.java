package tools.customDriver;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.http.W3CHttpCommandCodec;
import org.openqa.selenium.remote.http.W3CHttpResponseCodec;

/**
 * This class extends RemoteWebDriver class
 * To be used on Selenium version stating from 3.x
 * @author Arpo Adhikari
 *
 */
public class AttachedWebDriver2 extends RemoteWebDriver{
	
	/**
	 * This method is overridden to attach a WebDriver object to an existing session
	 * @param url
	 * @param sessionId
	 * @throws MalformedURLException
	 */
	public AttachedWebDriver2(URL url, String sessionId) {
		super();
		setSessionId(sessionId);
		setCommandExecutor(new HttpCommandExecutor(url) {
			@Override
			public Response execute(Command command) throws IOException {
				Response response = null;
				if(command.getName() == DriverCommand.NEW_SESSION) {
					response = new Response();
					response.setSessionId(sessionId);
					response.setStatus(0);
					response.setValue(Collections.<String, String>emptyMap());

					try {
						Field commandCodec = null;
						commandCodec = this.getClass().getSuperclass().getDeclaredField("commandCodec");
						commandCodec.setAccessible(true);
						commandCodec.set(this, new W3CHttpCommandCodec());

						Field responseCodec = null;
						responseCodec = this.getClass().getSuperclass().getDeclaredField("responseCodec");
						responseCodec.setAccessible(true);
						responseCodec.set(this, new W3CHttpResponseCodec());
					} catch (NoSuchFieldException e) {
						System.out.println(e.getMessage());
					} catch (IllegalAccessException e) {
						System.out.println(e.getMessage());
					}
				}
				else {
					response = super.execute(command);
				}
				return response;
			}
		});
		startSession(new DesiredCapabilities());
	}
}
