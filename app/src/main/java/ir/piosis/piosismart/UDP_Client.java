package ir.piosis.piosismart;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by PIOSISLP on 6/2/2017.
 */

public class UDP_Client {

    private String IP="255.255.255.255";
    private int PORTSocket=3965;
    public String Message;
    private AsyncTask<Void, Void, Void> async_cient;

    @SuppressLint("NewApi")
    public void UDP_Send_Message() {
        async_cient = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                DatagramSocket ds = null;

                try {
                    ds = new DatagramSocket();
                    DatagramPacket dp;
                    dp = new DatagramPacket(Message.getBytes(), Message.length(), InetAddress.getByName(IP), PORTSocket);
                    ds.setBroadcast(true);
                    ds.send(dp);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (ds != null) {
                        ds.close();
                    }
                }
                return null;
            }

            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        };

        if (Build.VERSION.SDK_INT >= 11)
            async_cient.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else async_cient.execute();
    }

    static boolean send(UDP_Client udp_client, String s){

        if (udp_client == null) {
            udp_client = new UDP_Client();
        }

        if (udp_client != null) {

            udp_client = new UDP_Client();
            udp_client.Message = s + "\r\n";
            udp_client.UDP_Send_Message();
        }
        return true;
    }



}
