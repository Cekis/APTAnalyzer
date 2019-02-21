package swg;

import java.io.*;
import java.nio.ByteBuffer;

public class SSAFile {
    private File inputFile;
    private File outputFile;

    private byte[] apprBytes;

    public SSAFile(String iFile){
        inputFile = new File(iFile);
        outputFile = new File("out/appearance/" + inputFile.getName());
    }

    public void read() {
        try{
            parse();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void write() throws IOException {
        FileOutputStream fos = new FileOutputStream(outputFile.getName());
        try{
            fos.write(apprBytes);
        }
        catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
        finally {
            fos.close();
        }
    }

    public void parse() {
        /*
        FileInputStream fis = null;
        BufferedInputStream bi = null;
        try {
            byte[] FORM = new byte[4];
            byte[] APTFORM = new byte[8];

            byte[] NAMETag = new byte[4];
            byte[] nameByteSize = new byte[4];

            fis = new FileInputStream(inputFile);
            bi = new BufferedInputStream(fis);
            // work our way to the data.
            bi.read(FORM);
            bi.read(new byte[4]);  // length of form
            bi.read(APTFORM);
            bi.read(new byte[4]);
            bi.read(version);
            bi.read(NAMETag);
            bi.read(nameByteSize);

            // get the length of the path to the LOD or SSA
            int nameLength = ByteBuffer.wrap(nameByteSize).getInt();

            // make a buffer to hold the info
            name = new byte[nameLength];

            // read in the number of bytes that the path occupies
            bi.read(name);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(bi != null){
                bi.close();
            }
            if(fis != null){
                fis.close();
            }
        }
        */
    }

    public void setApprBytes(byte[] val){
        apprBytes = val;
    }
}
