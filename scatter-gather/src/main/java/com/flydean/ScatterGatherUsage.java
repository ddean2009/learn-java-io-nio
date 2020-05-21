package com.flydean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * @author wayne
 * @version ScatterGatherUsage,  2020/5/21 8:54 下午
 */
@Slf4j
public class ScatterGatherUsage {

    @Test
    public void useScatterAndGather() throws IOException {

        gatherBytes();
        scatterBytes();
    }

    public static void gatherBytes() throws IOException {
        String data = "www.flydean.com is very useful";
        //First Buffer holds a random number
        ByteBuffer bufferOne = ByteBuffer.allocate(4);

        //Second Buffer holds data we want to write
        ByteBuffer buffer2 = ByteBuffer.allocate(200);

        //Writing Data sets to Buffer
        bufferOne.asIntBuffer().put(13);
        buffer2.asCharBuffer().put(data);

        //Calls FileOutputStream(file).getChannel()
        GatheringByteChannel gatherer = createChannelInstance("src/main/resources/www.flydean.com", true);

        //Write data to file
        gatherer.write(new ByteBuffer[] { bufferOne, buffer2 });
    }

    /*
     * scatterBytes() read bytes from a file channel into a set of buffers. Note that
     * it uses a single read for both the buffers.
     */
    public static void scatterBytes() throws IOException {
        //First Buffer holds a random number
        ByteBuffer bufferOne = ByteBuffer.allocate(4);

        //Second Buffer holds data we want to write
        ByteBuffer bufferTwo = ByteBuffer.allocate(200);

        //Calls FileInputStream(file).getChannel()
        ScatteringByteChannel scatterer = createChannelInstance("src/main/resources/www.flydean.com", false);

        //Reading from the channel
        scatterer.read(new ByteBuffer[] { bufferOne, bufferTwo });

        //Read the buffers seperately
        bufferOne.rewind();
        bufferTwo.rewind();

        int bufferOneContent = bufferOne.asIntBuffer().get();
        String bufferTwoContent = bufferTwo.asCharBuffer().toString();

        //Verify the content
        log.info("{}",bufferOneContent);
        log.info("{}",bufferTwoContent);
    }


    public static FileChannel createChannelInstance(String file, boolean isOutput) throws FileNotFoundException {
        FileChannel fc = null;
            if (isOutput) {
                fc = new FileOutputStream(file).getChannel();
            } else {
                fc = new FileInputStream(file).getChannel();
            }
        return fc;
    }
}
