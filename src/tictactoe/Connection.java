/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Mohamed Adel
 */
public class Connection{

    private static Connection instance = null;
    private Socket server = null;
    private DataInputStream dis;
    private PrintStream ps;
    private BufferedReader br;
    
    public String ip;

    private Connection() {
        
    }
    
    

    public static synchronized Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public void setIp(String ip){
        this.ip = ip;
    }
    public boolean startConnection() throws IOException{
        try {
            server = new Socket(ip, 5005);
            dis = new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream());
            br = new BufferedReader(new InputStreamReader(dis));
            return true;
        } catch (SocketException ex) {
            new MyAlert(Alert.AlertType.ERROR, "please enter valid IP").show();
            return false;
        } catch (IOException ex) {
            new MyAlert(Alert.AlertType.ERROR, "please enter valid IP").show();
            return false;
        }

    }

    public void closeConnection() {
        try {
            if (server != null) {
                server.close();
            }
        } catch (IOException ex) {
            System.out.println("Connection class in line 74");
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close() throws IOException{
        server.close();
    }

    public boolean isConnected() {
        if(server == null){
            return false;
        }else{
            return true;
        }
    }

    public PrintStream getPrintStream() {
        return ps;
    }
    
    public DataInputStream getDataInputStream() {
        return dis;
    }
    
    public BufferedReader getBufferReader(){
        return br;
    } 

}
