//SimpleHttpServer.java not used anymore.

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;

// The tutorial can be found just here on the SSaurel's Blog :
// https://www.ssaurel.com/blog/create-a-simple-http-web-server-in-java
// Each Client Connection will be managed in a dedicated Thread

public class JavaHTTPServer implements Runnable{

	static final File WEB_ROOT = new File(".");
	static final String DEFAULT_FILE = "public_html/index.html";
	static final String FILE_NOT_FOUND = "public_html/404.html";
	static final String METHOD_NOT_SUPPORTED = "public_html/not_supported.html";
	static final String PASSWORD_PAGE = "public_html/password.html";
	static final String COORDINATES_RECEIVED = "public_html/coordinates_received.html";
	// port to listen connection
	static final int PORT = 443;
	// verbose mode
	static final boolean verbose = false;
	// Client Connection via Socket Class
	private Socket connect;
	public JavaHTTPServer(Socket c) {
		connect = c;
	}
	public static void main(String[] args) {
		try {
			ServerSocket serverConnect = new ServerSocket(PORT);
			System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
			// we listen until user halts server execution
			while (true) {
				JavaHTTPServer myServer = new JavaHTTPServer(serverConnect.accept());
				if (verbose) {
					System.out.println("Connection opened. (" + new Date() + ")");
				}
				// create dedicated thread to manage the client connection
				Thread thread = new Thread(myServer);
				thread.start();
			}
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		}
	}

	@Override
	public void run() {
		// we manage our particular client connection
		BufferedReader in = null; PrintWriter out = null; BufferedOutputStream dataOut = null;
		String fileRequested = null;

		try {
		    String PASSWORD = new String ("12345678");
			// we read characters from the client via input stream on the socket
			in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			// we get character output stream to client (for headers)
			out = new PrintWriter(connect.getOutputStream());
			// get binary output stream to client (for requested data)
			dataOut = new BufferedOutputStream(connect.getOutputStream());

			// get first line of the request from the client
			String input = in.readLine();
			// we parse the request with a string tokenizer
			StringTokenizer parse = new StringTokenizer(input);
			String method = parse.nextToken().toUpperCase(); // we get the HTTP method of the client
			// we get file requested
			fileRequested = parse.nextToken().toLowerCase();

			// we support only GET and HEAD methods, we check
			if (!method.equals("GET")  &&  !method.equals("HEAD")) {

				// we return the not supported file to the client
				File file = new File(WEB_ROOT, METHOD_NOT_SUPPORTED);
				int fileLength = (int) file.length();
				//read content to return to client
				byte[] fileData = readFileData(file, fileLength);

				// we send HTTP Headers with data to client
				out.println("HTTP/1.1 501 Not Implemented");
				out.println("Date: " + new Date());
				out.println(); // blank line between headers and content, very important !
				out.flush(); // flush character output stream buffer
				// file
				dataOut.write(fileData, 0, fileLength);
				dataOut.flush();
				if (verbose) { System.out.println("501 Not Implemented : " + method + " method."); }

			} else {// GET or HEAD method
                if (fileRequested.endsWith("/")) { // if initial page request
                    if (verbose) {
                        System.out.println(fileRequested);
                        System.out.println("STATUS 1");
                    }
                    fileRequested = ("/" + PASSWORD_PAGE);
                } else {
                    if (fileRequested.contains("/?pwd=")) {
                        if (!fileRequested.equals("/?pwd=" + PASSWORD) && !fileRequested.contains("/?pwd=" + PASSWORD + "&x=")) {  // If password request is incorrect
                            if (verbose) {
                                System.out.println(fileRequested);
                                System.out.println("STATUS 3");
                            }
                            fileRequested = ("/" + PASSWORD_PAGE);
                        }
                        if (fileRequested.equals("/?pwd=" + PASSWORD)) {  // If password request is correct
                            if (verbose) {
                                System.out.println(fileRequested);
                                System.out.println("STATUS 2");
                            }
                            fileRequested = ("/" + DEFAULT_FILE);
                        }
                    } else {
                        if (verbose) {
                            System.out.println(fileRequested);
                            System.out.println("STATUS 5");
                        }
                        fileRequested = ("/" + FILE_NOT_FOUND);
                    }
                }

                File file = new File(WEB_ROOT, fileRequested);
                int fileLength = (int) file.length();
                byte[] fileData = readFileData(file, fileLength);

                // send HTTP Headers
                out.println("HTTP/1.1 200 OK");
                out.println("Date: " + new Date());
                out.println(); // blank line between headers and content, very important !
                out.flush(); // flush character output stream buffer
                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();

                if (verbose) { System.out.println(fileRequested); }
			}
		} catch (FileNotFoundException fnfe) {
		    if (fileRequested.endsWith(PASSWORD_PAGE)) {
                ;
            } else {
                try {
                    fileNotFound(out, dataOut, fileRequested);
                    if (verbose){
                        System.out.println("STATUS 4");
                        System.out.println(fileRequested);
                    }
                } catch (IOException ioe) {
                    System.err.println("Error with file not found exception : " + ioe.getMessage());
                }
			}
		} catch (IOException ioe) {
			System.err.println("Server error : " + ioe);
		} finally {
			try {
				in.close();
				out.close();
				dataOut.close();
				connect.close(); // we close socket connection
			} catch (Exception e) {
				System.err.println("Error closing stream : " + e.getMessage());
			}
			if (verbose) { System.out.println("Connection closed.\n"); }
		}
	}

	private byte[] readFileData(File file, int fileLength) throws IOException {
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];

		try {
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		} finally {
			if (fileIn != null)
				fileIn.close();
		}
		return fileData;
	}

	private void fileNotFound(PrintWriter out, OutputStream dataOut, String fileRequested) throws IOException {
	    Float xFinal = null;
	    Float yFinal = null;
        try{
            String yValue, xValue, finalXY;
            File file = new File(WEB_ROOT, COORDINATES_RECEIVED);
            int fileLength = (int) file.length();
            byte[] fileData = readFileData(file, fileLength);

            // Sends status code back to ajax
            out.println("HTTP/1.1 200 OK");
            out.println("Date: " + new Date());
            out.println(); // blank line between headers and content, very important !
            out.flush(); // flush character output stream buffer
            dataOut.write(fileData, 0, fileLength);
            dataOut.flush();

            yValue = fileRequested.substring(fileRequested.indexOf("y=")+2);
            xValue = fileRequested.substring(fileRequested.indexOf("x=")+2, fileRequested.indexOf("&y="));

            finalXY = xValue +","+ yValue;
            xFinal = Float.parseFloat(xValue);
            yFinal = Float.parseFloat(yValue);
            System.out.println(finalXY);
        } catch (Exception e) {
            if (verbose){
          	    System.err.println("Error : " + e.getMessage());
          	}
        }
        try{
            MotorController.speedAdapter(xFinal, yFinal);
        } catch(Exception ex){
            if (verbose){
                System.out.println("Coordinates to Motor Controller failed.");
            }
        }
	}
}
