package me.lceckie.juggling.box.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * @author Leckie
 * @version NioCopy.java, v0.1 2019-07-23 11:51
 */
public class NioCopy {

  public static void copy(String src, String dest) throws IOException {
    FileInputStream fis = new FileInputStream(src);
    FileChannel fcin = fis.getChannel();
    FileOutputStream fos = new FileOutputStream(dest);
    FileChannel fcout = fos.getChannel();
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    while (fcin.read(buffer) != -1) {
      buffer.flip();
      fcout.write(buffer);
      buffer.clear();
    }
  }

  public static void main(String[] args) throws IOException {
    copy(Paths.get("/Users", "leckie", "git", "juggling", "pom.xml").toString(),
        Paths.get("/Users", "leckie", "git", "juggling", "pom.xml2").toString());
    // Files.delete(Paths.get("/Users", "leckie", "git", "juggling", "pom.xml2"));
  }

}
