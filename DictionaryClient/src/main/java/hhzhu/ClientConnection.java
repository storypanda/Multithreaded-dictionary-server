package hhzhu;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;


/**
 * Author: Haohong Zhu
 * Student ID: 1305370
 */
public class ClientConnection {
	public String charset = "UTF-8";
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private byte[] inputBuffer = new byte[4000];

	public ClientConnection(){

	}

	public ClientConnection(Socket socket) throws Exception {
		this.socket = socket;
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
	}

	//Connect To Server
	public void connect(String ip, int port) throws Exception
	{
		try {
			socket = new Socket();
			socket.connect( new InetSocketAddress(ip,port));
			inputStream= socket.getInputStream();
			outputStream = socket.getOutputStream();
		}catch (ConnectException e) {
			System.out.println("Cannot Connect to Server");
		}
	}

	public void close() {
		try {
			socket.close();
			socket = null;
		} catch (Exception e) {
		}
	}

	//Keep running until the N bytes are all received
	public int receiveCorrectBytes(byte[] data, int off, int N) throws Exception {
		int count = 0; //count is the number of bytes that have been received
		while (count < N) {
			int remain = N - count;
			int numBytes = inputStream.read(data, off + count, remain);
			if (numBytes < 0) {
				return -1;
			}
			count = count + numBytes;
		}
		return N;
	}


	//Send!!!
	public void sendString(String msg, short action) throws Exception {
		byte[] data = msg.getBytes(this.charset);

		//Send the length of the content first
		ByteBuffer byteBufferHead = ByteBuffer.allocate(6);
		byteBufferHead.putInt(data.length);
		byteBufferHead.putShort(action);
		outputStream.write(byteBufferHead.array(), 0, 6);

		//Then send the content data.
		outputStream.write(data);
	}

	//Receive!!
	public String receiveString() throws Exception {
		ByteBuffer byteBufferHead = ByteBuffer.allocate(6);
		receiveCorrectBytes(byteBufferHead.array(), 0, 6);
		int N = byteBufferHead.getInt();
		short action = byteBufferHead.getShort();
		receiveCorrectBytes(inputBuffer, 0, N);
		return new String(inputBuffer, 0, N);
	}

}
