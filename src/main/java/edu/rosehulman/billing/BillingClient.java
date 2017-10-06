package edu.rosehulman.billing;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class BillingClient {
	static int pathmode = 1;
    public static void main(String[] args) {
    	while(true){
    		System.out.println("Socket:");
    		BufferedReader socketNumberReader = new BufferedReader(new InputStreamReader(System.in));
    		String socketNumberString = null;
			try {
				socketNumberString = socketNumberReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		int socketNumber = Integer.parseInt(socketNumberString);
    		
    		System.out.println("Command:");
    		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String commandline = null;
			try {
				commandline = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            
            Socket socket = null;
            String host = "35.164.184.155";
            try {
				socket = new Socket(host, socketNumber);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            byte[] bytes = new byte[160 * 1024];
            OutputStream out = null;
			try {
				out = socket.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

                InputStream cmdsendin = new ByteArrayInputStream(commandline.getBytes(StandardCharsets.UTF_8));
                int count = 0;
                
                byte[] filenamebuffer = new byte[512];
                try {
					while((count = cmdsendin.read(filenamebuffer)) > 0){
						out.write(filenamebuffer, 0, count);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //out.close();
                
                try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                InputStream in = null;
                try {
                    in = socket.getInputStream();
                } catch (IOException ex) {
                	System.err.println("Can't get socket input stream. ");
                    try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    try {
						in.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                    continue;
                    
                }
                try{
                	count = in.read(bytes);
                } catch(SocketException s){
                	System.err.println("Socket Exception");
                    try {
						out.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
                    try {
						in.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	continue;
                } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                if(count <= 0){
                	System.err.println("Not a valid responde");
                    try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    try {
						in.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                	continue;
                }
                String str = new String(bytes);
                System.out.println(str);
                OutputStream fileout = new ByteArrayOutputStream();
                
                while (count > 0) {
                	try {
						fileout.write(bytes, 0, count);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	try {
						count = in.read(bytes);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                System.out.println(fileout.toString());
                try {
					fileout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            

    	}
        
//    }
}
