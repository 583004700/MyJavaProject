package command;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class BeSocketClient {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;


    public void connect(){
        while(socket == null) {
            try {
                Thread.sleep(1000);
                connectServer();
            } catch (ConnectException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void connectServer() throws Exception{
        socket = new Socket("127.0.0.1",8867);
        System.out.println("socket = new Socket(\"127.0.0.1\",8867);");
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        sendMessage("be");
        System.out.println("连接成功");
    }

    public void start(){
        while(true){
            String command = null;
            boolean success = true;
            try {
                System.out.println("command before");
                command = bufferedReader.readLine();
                System.out.print("command after:"+command);
            } catch (IOException e) {
                success = false;
                e.printStackTrace();
            }finally {
                if(command == null || success == false){
                    System.out.println("closed");
                    socket = null;
                    connect();
                    start();
                }else{
                    System.out.println("执行"+command);
                    sendMessage(CmdUtil.execCloseReturnStr(command.split("&")));
                }
            }
        }
    }

    public void sendMessage(String message){
        try {
            System.out.print("message"+message);
            printWriter.println(message);
            printWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
}
