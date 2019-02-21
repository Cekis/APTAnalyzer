package swg;

import java.io.*;
import java.nio.ByteBuffer;

public class LODFile {
    private File inputFile;
    private File outputFile;

    private byte[] dtlaVersion;
    private byte[] apprBytes;

    public LODFile(String iFile){
        inputFile = new File(iFile);
        outputFile = new File("out/appearance/lod/" + inputFile.getName());
    }

    public boolean read() {
        try{
            return parse();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void write() throws IOException {
        /*
        FileOutputStream fos = new FileOutputStream(outputFile.getName());
        try{
            // use as an example later.

            int formLength = name.length + 24;
            fos.write("FORM".getBytes());
            fos.write(ByteBuffer.allocate(4).putInt(formLength).array());
            fos.write("APT ".getBytes());
            fos.write("FORM".getBytes());
            fos.write(ByteBuffer.allocate(4).putInt(name.length + 12).array());
            fos.write(version);
            fos.write("NAME".getBytes());
            fos.write(ByteBuffer.allocate(4).putInt(name.length).array());
            fos.write(name);
        }
        catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
        finally {
            fos.close();
        }
        */
    }

    public boolean parse() throws IOException {
        FileInputStream fis = null;
        BufferedInputStream bi = null;
        try {
            byte[] MAINFORM = new byte[4];
            byte[] formSize = new byte[4];

            byte[] DTLAFORM = new byte[8];
            byte[] dtlaFormSize = new byte[4];

            fis = new FileInputStream(inputFile);
            bi = new BufferedInputStream(fis);
            // work our way to the data.
            bi.read(MAINFORM);
            bi.read(formSize);  // length of form
            bi.read(DTLAFORM);
            bi.read(dtlaFormSize);
            bi.read(dtlaVersion);
            bi.read(apprBytes);
            return true;
        }
        catch(Exception e){
            return false;
        }
        finally{
            if(bi != null){
                bi.close();
            }
            if(fis != null){
                fis.close();
            }
        }
    }

    public byte[] getApprBytes() {
        return apprBytes;
    }
}
