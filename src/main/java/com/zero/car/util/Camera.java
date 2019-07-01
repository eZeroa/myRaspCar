package com.zero.car.util;

import java.io.IOException;

/**
 * @author Copy by seth.yang on Github.
 *
 *   mjpg_streamer -o "output_http.so -w /usr/www -p 18080" -i "input_raspicam.so -x 1280 -y 720 -fps 15"
 */
public class Camera {
    private Process process;
    private int port;

    public Camera (int port) {
        this.port = port;
    }

    public boolean isOpened () {
        return process != null;
    }

    public void open () throws IOException {
        if (isOpened ())
            return;

        process = new ProcessBuilder (
                "mjpg_streamer",
                "-o", "output_http.so -w /usr/www -p " + port,
                "-i", "input_raspicam.so  -fps 15"
        ).start ();
    }

    public void close () {
        if (process != null) {
            process.destroy ();
            process = null;
        }
    }
}